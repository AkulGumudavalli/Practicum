import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class HumanReader {
    public static void main(String[]args){
        File workingDir = new File(System.getProperty("user.dir"));
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(workingDir);
        File selectedFile;
        ArrayList<String> lines = new ArrayList<>();

        try{
            if(chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                InputStream in = new BufferedInputStream(Files.newInputStream(file,CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                while(reader.ready()){
                    lines.add(reader.readLine());
                }
                reader.close();
                System.out.println(lines);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int i = 0;
        System.out.print("ID#           Firstname     Lastname       Title    YOB\n" +
                         "===================================== \n");
        while(i<lines.size()){
            String curr = lines.get(i);
            String[] splitUp = curr.split(",");
            System.out.printf("%-9s%-10s%-10s%-4s%d",splitUp[0],splitUp[1],splitUp[2],splitUp[3],Integer.parseInt(splitUp[4]));
            System.out.println("\n");
            i ++;

        }
    }
}
