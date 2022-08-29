import java.io.*;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator
{
    String iD = "";
    String firstName = "";
    String lastName = "";
    String title = "";
    Integer yearOfBirth = 0;

    public static void main(String[] args)
    {

        Scanner in = new Scanner(System.in);

        ArrayList<String> recs = new ArrayList<>();

        boolean done = false;
        do
        {
            String iD = SafeInput.getRegExString(in, "Person's ID? ", "^00000\\d$");
            String firstName = SafeInput.getNonZeroLenString(in, "Person's first name? ");
            String lastName = SafeInput.getNonZeroLenString(in, "Person's last name? ");
            String title = SafeInput.getNonZeroLenString(in, "Person's title? ");
            Integer yearOfBirth = SafeInput.getRangedInt(in, "Person's birth year?", 1000, 2020);
            //then add to array list
            done = SafeInput.getYNConfirm(in, "Are you done?");
        }
        while (!done);


        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\data.txt");

        try
        {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));


            for(String rec : recs)
            {
                writer.write(rec, 0, rec.length());  //  syntax for write rec
                writer.newLine();  // adds the new line

            }
            writer.close(); //  closing the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

}