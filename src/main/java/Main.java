import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
        } catch (FileNotFoundException e) {
            System.out.println("File location not found.");
        } catch (IOException e) {
            System.out.println("Could not write file.");

        }

        // Read a file options:

        // BufferedReader + FileReader = good for large text files line by line
        // Scanner = good for parsing formatted text (structured data, reports, logs)
        // FileInputStream = good for reading binary data (images, audio, video)
        // DataInputStream = good for reading primitive data types
        // RandomAccessFile = good for reading from a specific location in a file

        String readFilePath = "example.txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(readFilePath));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Could not read file.");
        }

        // Copy a csv file to a text file
        String csvPath = "CPIHistoricalForecast.csv";
        try {
            BufferedReader br = new BufferedReader(new FileReader(csvPath));
            FileWriter forecastWriter = new FileWriter("CPIForecast.txt");
            String dataLine;
            while ((dataLine = br.readLine()) != null) {
                if (dataLine.isEmpty()) {
                    break;
                }
                forecastWriter.write(dataLine + "\n");
            }
            forecastWriter.close();
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");

        } catch (IOException e) {
            System.out.println("Could not read file.");
        } finally {
            System.out.println("File copied successfully.");
        }

//        Create an Excel workbook
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        List<FootballPlayer> players = new ArrayList<>(
                List.of(
                        new FootballPlayer("Lionel Messi", 34, 672, 778, "Paris Saint-Germain", "League 1"),
                        new FootballPlayer("Cristiano Ronaldo", 36, 674, 897, "Manchester United", "Premier League"),
                        new FootballPlayer("Neymar Jr.", 29, 258, 373, "Paris Saint-Germain", "League 1")
                )
        );



        int rowNumber = 0;

        for (FootballPlayer player : players) {
            Row row = sheet.createRow(rowNumber++);
            int columnNumber = 0;
            Cell nameCell = row.createCell(columnNumber);
            Cell ageCell = row.createCell(++columnNumber);
            Cell goalsCell = row.createCell(++columnNumber);
            Cell gamesCell = row.createCell(++columnNumber);
            Cell teamCell = row.createCell(++columnNumber);
            Cell leagueCell = row.createCell(++columnNumber);

            nameCell.setCellValue(player.getName());
            ageCell.setCellValue(player.getAge());
            goalsCell.setCellValue(player.getGoals());
            gamesCell.setCellValue(player.getGames());
            teamCell.setCellValue(player.getTeam());
            leagueCell.setCellValue(player.getLeague());
        }

        try (FileOutputStream outputStream = new FileOutputStream("./playerWorkbook.xlsx")) {
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Could not write file.");
        } finally {
            System.out.println("Excel file created successfully.");
        }
    }
}
