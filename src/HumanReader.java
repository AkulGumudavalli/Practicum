import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class HumanReader {
    public static void main(String[]args) throws ParserConfigurationException, IOException, SAXException {
        File workingDir = new File(System.getProperty("user.dir"));
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(workingDir);
        File selectedFile = null;
        ArrayList<String> lines = new ArrayList<>();
        String extenstion = null;
        try{
            if(chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){

                selectedFile = chooser.getSelectedFile();
                assert selectedFile != null;
                String fileName = selectedFile.getName();
                int dotIndex = fileName.lastIndexOf('.');
                if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
                    extenstion = fileName.substring(dotIndex + 1).toLowerCase();
                }
                Path file = selectedFile.toPath();
                InputStream in = new BufferedInputStream(Files.newInputStream(file,CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                if (extenstion.equalsIgnoreCase("csv")){
                    while(reader.ready()){
                        lines.add(reader.readLine());
                    }
                    reader.close();
                    System.out.println(lines);}
                else{
                    ;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int i = 0;
        System.out.println(extenstion);

        assert extenstion != null;
        if(extenstion.equalsIgnoreCase("csv")){
            System.out.printf("%-11s%-5s%-6s%-4s%s\n","Id","First Name","Last Name","Title","YOB");
            while(i< lines.size()){
                String curr = lines.get(i);
                String[] splitUp = curr.split(",");
                System.out.printf("%-9s%-10s%-10s%-4s%d", splitUp[0], splitUp[1], splitUp[2], splitUp[3], Integer.parseInt(splitUp[4]));
                System.out.println("\n");
                i++;
            }
            }
            if(extenstion.equalsIgnoreCase("xml")){
                System.out.printf("%-11s%-5s%-6s%-4s%s\n","Id","First Name","Last Name","Title","YOB");
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document =builder.parse(selectedFile);
                document.getDocumentElement().normalize();

                NodeList personList = document.getElementsByTagName("person");
                for (int g = 0;g < personList.getLength(); i++) {
                    Node node = personList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;

                        // Access child elements of each "person"
                        String id = element.getElementsByTagName("Id").item(0).getTextContent();
                        String firstName = element.getElementsByTagName("FirstName").item(0).getTextContent();
                        String lastName = element.getElementsByTagName("LastName").item(0).getTextContent();
                        String title = element.getElementsByTagName("Title").item(0).getTextContent();
                        String yearOfBirth = element.getElementsByTagName("YearOfBirth").item(0).getTextContent();
                        System.out.printf("%-9s%-10s%-10s%-4s%s",id,firstName,lastName,title,yearOfBirth);



                    }
                }
            }
            if(extenstion.equalsIgnoreCase("json")){

                try {
                    // Read the JSON file into a String
                    BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                    StringBuilder jsonBuilder = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        jsonBuilder.append(line);
                    }

                    reader.close();

                    String jsonString = jsonBuilder.toString();
                    System.out.println(jsonString);

                    if (jsonString.startsWith("[") && jsonString.endsWith("]")) {
                        jsonString = jsonString.substring(1, jsonString.length() - 1); // Remove square brackets
                        String[] objects = jsonString.split("\\},\\{"); // Split JSON objects

                        for (String obj : objects) {
                            obj = obj.replace("{", "").replace("}", "").replace("\"", "");
                            String[] fields = obj.split(",");

                            System.out.println("\nParsed Object:");
                            for (String field : fields) {
                                String[] keyValue = field.split(":");
                                String key = keyValue[0].trim();
                                String value = keyValue[1].trim();
                                System.out.println(key + ": " + value);
                            }
                        }
                    }else if(jsonString.startsWith("{") && jsonString.endsWith("}")){
                        jsonString = jsonString.substring(1, jsonString.length() - 1); // Remove square brackets
                        String[] objects = jsonString.split("\\},\\{"); // Split JSON objects

                        for (String obj : objects) {
                            obj = obj.replace("{", "").replace("}", "").replace("\"", "");
                            String[] fields = obj.split(",");

                            System.out.println("\nParsed Object:");
                            for (String field : fields) {
                                String[] keyValue = field.split(":");
                                String key = keyValue[0].trim();
                                String value = keyValue[1].trim();
                                System.out.println(key + ": " + value);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
    }
}
