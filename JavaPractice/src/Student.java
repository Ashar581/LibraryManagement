import java.util.ArrayList;
import java.util.List;

public class Student {

    private String name;
    private String address;
    private String emailId;
    private String password;

    private String userID;
//list of books borrowed by the student
    private List<Books> books = new ArrayList<>();

    public Student(String name, String address, String emailId, String password, String userID) {
        this.name = name;
        this.address = address;
        this.emailId = emailId;
        this.password = password;
        this.userID = userID;
    }
//getter
    public String getName() {
        return name;
    }

//getter
    public String getAddress() {
        return address;
    }

//getter
    public String getEmailId() {
        return emailId;
    }

//getter
    public String getPassword() {
        return password;
    }

//getter
    public String getUserID(){
        return userID;
    }

//list of books this student borrow
    public List<Books> getBooks() {
        return books;
    }

    //adding borrowed books for the current student
    public void addBook(Books book) {
        this.books.add(book);
    }
}
