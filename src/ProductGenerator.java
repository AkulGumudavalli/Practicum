import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductGenerator {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        boolean loopState = true;
        SafeInput safeInput = new SafeInput(input);
        JFileChooser chooser = new JFileChooser();
        File working_directory = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(working_directory);
        String extenstion = new String();
        File selectedFile = null;
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            selectedFile = chooser.getSelectedFile();
            Path file = selectedFile.toPath();
        }else{
            String path = safeInput.getNonZeroLenString("What do you want to name your file");
            String type = null;
            do{
                type = safeInput.getNonZeroLenString("What type do you want your file");
            }while(type == null);
            selectedFile =new File(working_directory+"\\src\\"+path+"."+type);
        }
        ArrayList<Product> items= new ArrayList<>();
        String id;
        String name;
        String description;
        double cost;
        do{
            id = safeInput.getNonZeroLenString("What is your id");
            name = safeInput.getNonZeroLenString("What is your items name");
            description=safeInput.getNonZeroLenString("What is your items discription");
            cost =safeInput.getDouble("What is items cost");
            Product product = new Product(id,name,description,cost);
            items.add(product);
            loopState = safeInput.getYNconfirm("Do you want to input more");
        }while(loopState);
        assert selectedFile != null;
        String fileName = selectedFile.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            extenstion = fileName.substring(dotIndex + 1).toLowerCase();
        }
        try {
            FileWriter writer = new FileWriter(selectedFile.getPath());
            for (Product h : items) {
                if(extenstion.equalsIgnoreCase("csv")){
                    List<String> stuffAboutPeople = h.toCSV();
                    writer.append(stuffAboutPeople.getFirst());
                    writer.append(",");
                    writer.append(stuffAboutPeople.get(1));
                    writer.append(",");
                    writer.append(stuffAboutPeople.get(2));
                    writer.append(",");
                    writer.append(stuffAboutPeople.get(3));
                    writer.append("\n");
                }
                else if(extenstion.equalsIgnoreCase("xml")){
                    String hello = h.toXML();
                    writer.write(hello);
                    writer.write("\n");
                }else if(extenstion.equalsIgnoreCase("json")) {
                    String hello = h.toJson();
                    writer.write(hello);
                    writer.write("\n");
                }
            }
            writer.close();
            System.out.println("DONE");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
