import java.io.*;

public class Main {
    public static void main(String[] args) {
        // Write a file options:
        // FileWriter = good for small/med text files
        // BufferedWriter = good for large text files
        // PrintWriter = good for writing formatted text (structured data, reports, logs)
        // FileOutputStream = good for writing binary data (images, audio, video)
        // ObjectOutputStream = good for writing objects
        // BufferedOutputStream = good for writing large binary data
        // DataOutputStream = good for writing primitive data types
        // RandomAccessFile = good for writing to a specific location in a file
        // RandomAccessFile = good for reading from a specific location in a file

        String filePath = "example.txt";
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write("""
                    Hello, World!
                    This is a test file.
                    """);
            System.out.println("File written successfully.");
            writer.close();
        }
        catch(FileNotFoundException e) {
            System.out.println("File location not found.");
        }
        catch (IOException e) {
            System.out.println("Could not write file.");

        }

        // Read a file options:
        // BufferedReader + FileReader = good for large text files line by line
        // Scanner = good for parsing formatted text (structured data, reports, logs)
        // FileInputStream = good for reading binary data (images, audio, video)
        // DataInputStream = good for reading primitive data types
        // RandomAccessFile = good for reading from a specific location in a file

        String readFilePath = "haiku.txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(readFilePath));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        catch (IOException e) {
            System.out.println("Could not read file.");
        }



    }
}
