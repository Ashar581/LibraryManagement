import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ReadingCSV {
    public static List<Student> readCsv(String path) {
        List<Student> studentInfo = new ArrayList<>();
        try{
            File file = new File(path);
            Scanner readFile = new Scanner(file);

            StringTokenizer token = null;
            String check="";

//Skipping first line of CSV
            if(readFile.hasNext()){
                readFile.nextLine();
            }

            while(readFile.hasNext()){

                if(readFile.hasNext("-")){
                    readFile.nextLine();
                }

                token = new StringTokenizer(readFile.nextLine(),",");
                String fullName = token.nextToken();
                String address = token.nextToken();
                String emailId = token.nextToken();
                String pwd = token.nextToken();
                String uid = token.nextToken();


                Student student = new Student(fullName, address, emailId, pwd, uid);
                studentInfo.add(student);

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return studentInfo;
    }
    public static List<Books> readBooksCSV(String path) throws FileNotFoundException {
        List<Books> readBookInfo = new ArrayList<>();
        try {
            File file = new File(path);
            Scanner readFile = new Scanner(file);
            StringTokenizer token = null;

            String check="";

//Skipping first line of CSV
            if(readFile.hasNext()){
                readFile.nextLine();
            }

            while(readFile.hasNext()) {
                if (readFile.hasNext("-")) {
                    readFile.nextLine();
                }

                    token = new StringTokenizer(readFile.nextLine(), ",");
                    String bookName = token.nextToken();
                    String author = token.nextToken();
                    String bookID = token.nextToken();

                    Books books = new Books(bookName, author, bookID);
                    readBookInfo.add(books);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
        return readBookInfo;
    }

    public static List<Student> readCsvBorrow(String path,String name,String adrss, String mail, String pswd, String id) {
        List<Student> studentInfo = new ArrayList<>();
        try{
            File file = new File(path);
            Scanner readFile = new Scanner(file);

            StringTokenizer token = null;

            String bookName="";
            String bookID="";
            String bookAuthor="";

//Skipping first line of CSV
            if(readFile.hasNext()){
                readFile.nextLine();
            }

            while(readFile.hasNext()){
                token = new StringTokenizer(readFile.nextLine(),",");
                String fullName = token.nextToken();
                String address = token.nextToken();
                String emailId = token.nextToken();
                String pwd = token.nextToken();
                String uid = token.nextToken();

                if(fullName.equalsIgnoreCase(name)) {
                        Student student = new Student(fullName, address, emailId, pwd, uid);
                        studentInfo.add(student);
                        if (token.hasMoreTokens()) {
                            bookName = token.nextToken();
                            bookID = token.nextToken();
                            bookAuthor = token.nextToken();
                            System.out.println(bookName+bookID+bookAuthor);
                        }

                        if (bookName != "" && bookID != "") {
                            Books book = new Books(bookName, bookAuthor, bookID);
                            student.addBook(book);

                        }

                }


            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return studentInfo;
    }
}
