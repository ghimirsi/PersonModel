import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonReader
{

    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        ArrayList<String> people = new ArrayList<String>();

        try
        {

            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));


                int line = 0;
                while(reader.ready())
                {
                    rec = reader.readLine();
                    line++;


                    people.add(rec);
                }
                reader.close();
                System.out.println("\n\nData file read!");
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println(" not found!!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        System.out.println(String.format("%10s %15s %15s %5s %10s", "ID#", "Firstname", "Lastname", "Title", "YOB"));
        System.out.println("============================================================");
        for(String person : people)
        {
            ArrayList<String> pAtt = new ArrayList<String>();
            for (String attributes : person.split(", "))
            {

                pAtt.add(attributes);

            }
            System.out.println(String.format("%10s %15s %15s %5s %10s", pAtt.get(0), pAtt.get(1), pAtt.get(2), pAtt.get(3), pAtt.get(4)));
        }

    }
}
