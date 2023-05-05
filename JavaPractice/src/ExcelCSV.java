import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class ExcelCSV {
    public static void writeCSV(String path) throws FileNotFoundException {

        Scanner sc = new Scanner(System.in);
        FileOutputStream fos = new FileOutputStream(path,true);
        PrintWriter pw = new PrintWriter(fos);

        //pw.println("Name, Address, Email, Password, UserID");

        System.out.println("Enter your name");
        String name=sc.nextLine();
        System.out.println("Enter your address");
        String address=sc.nextLine();
        System.out.println("Enter your email");
        String email=sc.nextLine();
        System.out.println("Enter your password");
        String password=sc.nextLine();
        System.out.println("Enter your useID");
        String userid=sc.nextLine();

        pw.println();
        pw.println(name+","+address+","+email+","+password+","+userid+"\n");
        pw.flush();
        pw.close();
        System.out.println("File has been updated!");

    }
    public static void writeCSVBooks(String path)throws FileNotFoundException{

        Scanner sc = new Scanner(System.in);
        FileOutputStream fos = new FileOutputStream(path,true);
        PrintWriter pw = new PrintWriter(fos);

        System.out.println("Enter The Book Name");
        String bookName = sc.nextLine();
        System.out.println("Enter Book ID");
        String bookID = sc.nextLine();
        System.out.println("Enter The Author's Name");
        String author = sc.nextLine();
//        pw.println("Book Name,Author,Book ID");
        pw.println();
        pw.println(bookName+","+author+","+bookID+"\n");
        pw.flush();
        pw.close();
        System.out.println("File has been updated");
        System.out.println();
    }
}
