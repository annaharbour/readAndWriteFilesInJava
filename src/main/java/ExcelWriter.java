import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExcelWriter {

    public static void main(String[] args) {

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet ws = wb.createSheet();
        Object[][] bookDetailsArray = createBookArray();
        createHeaderRow(ws);
        int rowNumber = 1;
        for(Object[] book : bookDetailsArray){
            Row row = ws.createRow(rowNumber++);
            int columnNumber = 0;
            for(Object field: book){
                Cell cell = row.createCell(columnNumber);

                if(field instanceof  Integer){
                    cell.setCellValue((Integer) field);
                } else if (field instanceof Boolean){
                    cell.setCellValue((Boolean) field);
                }
                else{
                    cell.setCellValue((String)field);
                }
                columnNumber++;
            }
        }
        try(FileOutputStream outputStream = new FileOutputStream("./books.xlsx")){
            wb.write(outputStream);
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }

    }

    private static void createHeaderRow(XSSFSheet ws) {
        Row row = ws.createRow(0);
        List<String> headers = Arrays.asList("Title", "Year Published", "Fiction / Nonfiction", "Category", "Publishing House", "Author");
        for(int i = 0; i<headers.size(); i++){
            Cell headerCell = row.createCell(i);
            headerCell.setCellValue(headers.get(i));
        }
    }

    private static Object[][] createBookArray() {
        List<Book> bookList = createBookList();
        Object[][] bookArray = new Object[bookList.size()][6];

        int bookIndexNumber = 0;
        for(Book book: bookList){

            bookArray[bookIndexNumber][0] = book.getTitle();
            bookArray[bookIndexNumber][1] = book.getPublishYear();
            bookArray[bookIndexNumber][2] = book.getIsFiction();
            bookArray[bookIndexNumber][3] = book.getPublishingHouse();
            bookArray[bookIndexNumber][4] = book.getCategory();
            bookArray[bookIndexNumber][5] = book.getAuthor();
            bookIndexNumber++;
        }

        return bookArray;
    }

    private static List<Book> createBookList() {

        List<Book> books = new ArrayList<>();

        Book book1 = Book.builder().title("The Great Gatsby").publishYear(1925).isFiction(true).category("Classic").publishingHouse("Charles Scribner's Sons").author("F. Scott Fitzgerald").build();
        Book book2 = Book.builder().title("To Kill a Mockingbird").publishYear(1960).isFiction(true).category("Classic").publishingHouse("J.B. Lippincott & Co.").author("Harper Lee").build();
        Book book3 = Book.builder().title("1984").publishYear(1949).isFiction(true).category("Classic").publishingHouse("Secker & Warburg").author("George Orwell").build();
        Book book4 = Book.builder().title("Pride and Prejudice").publishYear(1813).isFiction(true).category("Classic").publishingHouse("T. Egerton, Whitehall").author("Jane Austen").build();
        Book book5 = Book.builder().title("The Catcher in the Rye").publishYear(1951).isFiction(true).category("Classic").publishingHouse("Little, Brown and Company").
                author("J.D. Salinger").build();
        Book book6 = Book.builder().title("The Hobbit").publishYear(1937).isFiction(true).category("Classic").publishingHouse("Allen & Unwin").author("J.R.R. Tolkien").build();
        Book book7 = Book.builder().title("The Lord of the Rings").publishYear(1954).isFiction(true).category("Classic").publishingHouse("Allen & Unwin").author("J.R.R. Tolkien").build();
        Book book8 = Book.builder().title("The Da Vinci Code").publishYear(2003).isFiction(true).category("Mystery").publishingHouse("Doubleday").author("Dan Brown").build();


        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);
        books.add(book6);
        books.add(book7);
        books.add(book8);

        return books;
    }
}