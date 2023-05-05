import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UpdateCSV {
    public static void updateUser( String name, String address, String email, String password, String userID) throws IOException{
        //Instantiating the File class
        String filePath = "people.csv";
        //Instantiating the Scanner class to read the file
        Scanner sc = new Scanner(new File(filePath));
        //instantiating the StringBuffer class
        StringBuffer buffer = new StringBuffer();
        //Reading lines of the file and appending them to StringBuffer
        while (sc.hasNextLine()) {
            buffer.append(sc.nextLine() + System.lineSeparator());
        }
        String fileContents = buffer.toString();

        sc.close();

        String oldLine = name+","+address+","+email+","+password+","+userID;
        String newLine = "-";
        //Replacing the old line with new line
        fileContents = fileContents.replaceAll(oldLine, newLine);
        //instantiating the FileWriter class
        FileWriter writer = new FileWriter(filePath);
        System.out.println("");
        writer.append(fileContents);

        writer.flush();
        writer.close();
        sc.close();
    }

    public static void updateBook( String bookName, String author, String ID) throws IOException{
        //Instantiating the File class
        String filePath = "books.csv";
        //Instantiating the Scanner class to read the file
        Scanner sc = new Scanner(new File(filePath));
        //instantiating the StringBuffer class
        StringBuffer buffer = new StringBuffer();
        //Reading lines of the file and appending them to StringBuffer
        while (sc.hasNextLine()) {
            buffer.append(sc.nextLine() + System.lineSeparator());
        }
        String fileContents = buffer.toString();

        sc.close();


        String oldLine = bookName+","+author+","+ID;
        String newLine = "NA";
        //Replacing the old line with new line

        fileContents = fileContents.replace(oldLine, newLine);
        //instantiating the FileWriter class
        FileWriter writer = new FileWriter(filePath);
        System.out.println("");
        writer.append(fileContents);

        writer.flush();
        writer.close();
        sc.close();
    }
    public static void updateBookReturn( String bookName, String author, String ID) throws IOException{
        //Instantiating the File class
        String filePath = "books.csv";
        //Instantiating the Scanner class to read the file
        Scanner sc = new Scanner(new File(filePath));
        //instantiating the StringBuffer class
        StringBuffer buffer = new StringBuffer();
        //Reading lines of the file and appending them to StringBuffer
        while (sc.hasNextLine()) {
            buffer.append(sc.nextLine() + System.lineSeparator());
        }
        String fileContents = buffer.toString();

        sc.close();


        String oldLine = "NA";
        String newLine = bookName+","+author+","+ID;
        //Replacing the old line with new line

        fileContents = fileContents.replaceFirst(oldLine, newLine);
        //instantiating the FileWriter class
        FileWriter writer = new FileWriter(filePath);
        System.out.println("");
        writer.append(fileContents);

        writer.flush();
        writer.close();
        sc.close();
    }
    public static void updateUserBorrow( String name, String address, String email, String password, String userID, String bookName, String bookID, String author) throws IOException{
        //Instantiating the File class
        String filePath = "people.csv";
        //Instantiating the Scanner class to read the file
        Scanner sc = new Scanner(new File(filePath));
        //instantiating the StringBuffer class
        StringBuffer buffer = new StringBuffer();
        //Reading lines of the file and appending them to StringBuffer
        while (sc.hasNextLine()) {
            buffer.append(sc.nextLine() + System.lineSeparator());
        }
        String fileContents = buffer.toString();

        sc.close();

        String oldLine = name+","+address+","+email+","+password+","+userID;
        String newLine = name+","+address+","+email+","+password+","+userID+","+bookName+","+bookID+","+author;
        //Replacing the old line with new line
        fileContents = fileContents.replaceAll(oldLine, newLine);
        //instantiating the FileWriter class
        FileWriter writer = new FileWriter(filePath);
        System.out.println("");
        writer.append(fileContents);

        writer.flush();
        writer.close();
        sc.close();
    }

    public static void updateUserBorrowReturn( String name, String address, String email, String password, String userID, String bookName, String bookID, String author) throws IOException{
        //Instantiating the File class
        String filePath = "people.csv";
        //Instantiating the Scanner class to read the file
        Scanner sc = new Scanner(new File(filePath));
        //instantiating the StringBuffer class
        StringBuffer buffer = new StringBuffer();
        //Reading lines of the file and appending them to StringBuffer
        while (sc.hasNextLine()) {
            buffer.append(sc.nextLine() + System.lineSeparator());
        }
        String fileContents = buffer.toString();

        sc.close();

        String oldLine = name+","+address+","+email+","+password+","+userID+","+bookName+","+bookID+","+author;
        String newLine = name+","+address+","+email+","+password+","+userID;
        //Replacing the old line with new line
        fileContents = fileContents.replaceAll(oldLine, newLine);
        //instantiating the FileWriter class
        FileWriter writer = new FileWriter(filePath);
        System.out.println("");
        writer.append(fileContents);

        writer.flush();
        writer.close();
        sc.close();
    }
}
