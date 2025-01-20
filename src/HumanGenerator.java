import javax.swing.*;
import java.io.*;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class HumanGenerator {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        boolean loopState = true;
        JFileChooser chooser = new JFileChooser();
        File working_directory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(working_directory.getPath() +"\\src\\PersonTestData.txt");
        chooser.setCurrentDirectory(working_directory);
        ArrayList<String> people= new ArrayList<>();
        String id;
        String firstName;
        String lastName;
        String title;
        int yearOfBirth;
        do{
            id = SafeInput.getNonZeroLenString(input,"What is your id");
            firstName = SafeInput.getNonZeroLenString(input,"What is your first name");
            lastName=SafeInput.getNonZeroLenString(input,"What is your last name");
            title = SafeInput.getNonZeroLenString(input,"What is your title, EX. Mr.;Mrs.");
            yearOfBirth =SafeInput.getRangedInt(input,"What is your year of birth",1000,9999);

            String join = id +","+lastName+","+firstName+","+title+","+yearOfBirth;
            people.add(join);
            loopState = SafeInput.getYNconfirm(input,"Do you want to input more");
        }while(loopState);
        try{
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file,CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for(String write:people){
                writer.write(write,0,write.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("DONE");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}