import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class HumanGenerator {
    public static void main(String[] args) throws IOException {
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

        ArrayList<Human> people = new ArrayList<>();
        String id;
        String firstName;
        String lastName;
        String title;
        int yearOfBirth;
        do {
            id = safeInput.getNonZeroLenString( "What is your id");
            firstName = safeInput.getNonZeroLenString( "What is your first name");
            lastName = safeInput.getNonZeroLenString( "What is your last name");
            title = safeInput.getNonZeroLenString( "What is your title, EX. Mr.;Mrs.");
            yearOfBirth = safeInput.getRangedInt( "What is your year of birth", 1000, 9999);

            Human human = new Human(id, firstName, lastName, title, yearOfBirth);
            people.add(human);
            loopState = safeInput.getYNconfirm( "Do you want to input more");
        } while (loopState);
        assert selectedFile != null;
        String fileName = selectedFile.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
         extenstion = fileName.substring(dotIndex + 1).toLowerCase();
        }
        try {
            FileWriter writer = new FileWriter(selectedFile.getPath());
            for (Human h : people) {
                if(extenstion.equalsIgnoreCase("csv")){
                    List<String> stuffAboutPeople = h.toCSV();
                    writer.append(stuffAboutPeople.getFirst());
                    writer.append(",");
                    writer.append(stuffAboutPeople.get(1));
                    writer.append(",");
                    writer.append(stuffAboutPeople.get(2));
                    writer.append(",");
                    writer.append(stuffAboutPeople.get(3));
                    writer.append(",");
                    writer.append(stuffAboutPeople.get(4));
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