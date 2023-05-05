import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Register {

    private final String admin_id = "Ashar581";

    private final String admin_pass = "library";

    private Student studentLoggedIn;
    public Books bookReturn = null;

    //List of registered students
    List<Student> students = new ArrayList<>();
    //List of books
    List<Books> books = new ArrayList<>();

    public List<Student> getStudents() {
        return students;
    }

    //setter
    public void addStudent(Student student) {
        this.students.add(student);
    }

    public List<Books> getBooks() {
        return books;
    }

    //setter
    public void addBook(Books book) {
        this.books.add(book);
    }

    //read the csv file
    public void readFile(String path) {
        students = ReadingCSV.readCsv(path);
    }

    //write the csv file
    public void writeFile(String path) throws FileNotFoundException {
        ExcelCSV.writeCSV(path);
    }

    //write books csv file
    public void writeCSVBooks(String path)throws FileNotFoundException{
        ExcelCSV.writeCSVBooks(path);
    }

    //read books csv file
    public void readCSVBooks(String path)throws FileNotFoundException{
        books = ReadingCSV.readBooksCSV(path);
    }
    //update people csv file

    public void updateFile(String name,String address,String email,String password, String userID) throws IOException {
        UpdateCSV.updateUser(name, address, email, password, userID);
    }

    //update books csv file

    public void updateBook(String bookName, String author, String ID) throws IOException{
        UpdateCSV.updateBook(bookName, author, ID);
    }
    public void updateBookBorrow(String name,String address,String email,String password, String userID,String bookName, String bookID, String author ) throws IOException {
        UpdateCSV.updateUserBorrow(name, address, email, password, userID, bookName, bookID, author);
    }
    public void updateBorrowedReturn(String name,String address,String email,String password, String userID,String bookName, String bookID, String author) throws IOException{
        UpdateCSV.updateUserBorrowReturn(name, address, email, password, userID, bookName, bookID, author);
    }
    boolean admin_login(String a, String p) {
        if (a.equalsIgnoreCase(admin_id) && p.equals(admin_pass))
            return true;
        return false;
    }

    boolean client_login(String clientUserName, String clientUserPassword) {
        for (Student student : students) {
            if (student.getUserID().equalsIgnoreCase(clientUserName) && student.getPassword().equals(clientUserPassword)) {
                studentLoggedIn = student;
                students = ReadingCSV.readCsvBorrow("people.csv", studentLoggedIn.getName(), studentLoggedIn.getAddress(), studentLoggedIn.getEmailId(), studentLoggedIn.getPassword(), studentLoggedIn.getUserID());
                for(Student s : students) {
                    studentLoggedIn = s;
                    return true;
                }
            }
        }
        return false;
    }

    public void studentUnsubscribed(String userID) throws IOException {
        Student unsubscribe = null;
        if (studentLoggedIn != null && studentLoggedIn.getUserID().equalsIgnoreCase(userID) && studentLoggedIn.getBooks().isEmpty()) {
            for (Student student : students) {
                if (student.getUserID().equals(userID)) {
                    unsubscribe = student;
                }
            }
            if (unsubscribe == null) {
                System.out.println("-----------------------------------------------------");
                System.out.println("User ID not found. Try again!");
                System.out.println("-----------------------------------------------------");

                System.out.println();
            }
            updateFile(unsubscribe.getName(), unsubscribe.getAddress(), unsubscribe.getEmailId(), unsubscribe.getPassword(), unsubscribe.getUserID());
            students.remove(unsubscribe);
            System.out.println("-----------------------------------------------------");
            System.out.println("You have been successfully unsubscribed...");
            System.out.println("-----------------------------------------------------");
        }else{
            System.out.println("-----------------------------------------------------");
            System.out.println("Please Write Your own User ID For Unsubscribing");
            System.out.println("-----------------------------------------------------");
        }
    }

    //student borrowing the book if login is true
    public void borrowBook(String bookID) throws IOException {
        Books bookToBeBorrowed = null;
        for (Books book : books) {
            if (book.getBookID().equals(bookID)) {
                bookToBeBorrowed = book;
            }
        }
        if (bookToBeBorrowed == null) {
            System.out.println("Book with id: " + bookID + " not found");
            return;
        }

        if (studentLoggedIn != null) {
            studentLoggedIn.addBook(bookToBeBorrowed);
            updateBookBorrow(studentLoggedIn.getName(), studentLoggedIn.getAddress(), studentLoggedIn.getEmailId(), studentLoggedIn.getPassword(), studentLoggedIn.getUserID(), bookToBeBorrowed.getBook_name(),bookToBeBorrowed.getBookID(),bookToBeBorrowed.getAuthor());
            updateBook(bookToBeBorrowed.getBook_name(),bookToBeBorrowed.getAuthor(),bookToBeBorrowed.getBookID());
            this.books.remove(bookToBeBorrowed);
            System.out.println("-----------------------------------------------------");
            System.out.println("The Available Books Now: ");
            System.out.println("-----------------------------------------------------");
            for (Books book : books) {
                bookToBeBorrowed = book;
                System.out.println("Book: " + book.getBook_name() + "   Book ID: " + book.getBookID());
            }
            System.out.println("-----------------------------------------------------");
        }else {
            System.out.println("No student logged in at the moment");
        }

//book returning
    }

    public void bookReturned(String bookID) throws IOException{

//traversing the list of books student borrowed and checking for the book returned with the book ID

            for (Books book : studentLoggedIn.getBooks()) {
                if (book.getBookID().equalsIgnoreCase(bookID)) {
                    bookReturn = book;
                    updateBook(book.getBook_name(),book.getAuthor(),book.getBookID());
                } else {
                    System.out.println("Wrong Book ID. Please enter the correct ID.");
                    return;
                }
            }
            studentLoggedIn.getBooks().remove(bookReturn);
            updateBorrowedReturn(studentLoggedIn.getName(), studentLoggedIn.getAddress(), studentLoggedIn.getEmailId(),studentLoggedIn.getPassword(),studentLoggedIn.getUserID(),bookReturn.getBook_name(),bookReturn.getBookID(),bookReturn.getAuthor());
            //updateBook(bookReturn.getBook_name(),bookReturn.getAuthor(),bookReturn.getBookID());
            UpdateCSV.updateBookReturn(bookReturn.getBook_name(),bookReturn.getAuthor(),bookReturn.getBookID());
            this.books.add(bookReturn);
            System.out.println();
        }
        public boolean logOut(String logOut1){
        if(logOut1.equalsIgnoreCase("Y")) {
            studentLoggedIn = null;
            return true;
        }
        else
            return false;
        }

//checking if the student still has borrowed books
        boolean bookCheck(){
        if(studentLoggedIn.getBooks().isEmpty())
            return true;
        return false;
        }

//List of book borrowed by a student
    void displayListOfBorrowedBooks(){
        for(Books book : studentLoggedIn.getBooks()){
            System.out.println("-----------------------------------------------------");
            System.out.println("Book Name: "+book.getBook_name());
            System.out.println("Book ID: "+book.getBookID());
            System.out.println("-----------------------------------------------------");
            System.out.println();
        }
    }

    //Displaying the log in user
    void displayLoggedInUser() {
        try {
            System.out.println("-----------------------------------------------------");
            System.out.println("Your Details:");
            System.out.println("User Name: " + studentLoggedIn.getName() + "\n" + "User ID: "
                    + studentLoggedIn.getUserID());
            System.out.println("-----------------------------------------------------");

            if (studentLoggedIn.getBooks() != null) {
                for (Books book : studentLoggedIn.getBooks()) {
                    System.out.println("-----------------------------------------------------");
                    System.out.println("Book That You Have Currently Borrowed: ");
                    System.out.println("Book Name: " + book.getBook_name() + "\n" + "Book ID : " + book.getBookID());
                    System.out.println("-----------------------------------------------------");
                    System.out.println();
                }
            }
            System.out.println();
        } catch (NullPointerException e) {
            System.out.println("Exception Handled!");
            System.out.println();
        }

    }
    public void studentLoggedInName(){
        System.out.println("-----------------------------------------------------");
        System.out.println("Welcome "+studentLoggedIn.getName()+" !");
        System.out.println("-----------------------------------------------------");
    }
    public void adminName(){
        System.out.println("-----------------------------------------------------");
        System.out.println("Welcome "+admin_id+" !");
        System.out.println("-----------------------------------------------------");
        System.out.println();

    }
}

