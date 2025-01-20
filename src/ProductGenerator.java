import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductGenerator {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        boolean loopState = true;
        JFileChooser chooser = new JFileChooser();
        File working_directory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(working_directory.getPath() +"\\src\\ItemData.txt");
        chooser.setCurrentDirectory(working_directory);
        ArrayList<String> items= new ArrayList<>();
        String id;
        String name;
        String description;
        double cost;
        do{
            id = SafeInput.getNonZeroLenString(input,"What is your id");
            name = SafeInput.getNonZeroLenString(input,"What is your items name");
            description=SafeInput.getNonZeroLenString(input,"What is your items discription");
            cost =SafeInput.getDouble(input,"What is items cost");

            String join = id +","+name+","+description+","+cost;
            items.add(join);
            loopState = SafeInput.getYNconfirm(input,"Do you want to input more");
        }while(loopState);
        try{
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file,CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for(String write:items){
                writer.write(write,0,write.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("DONE");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
