import java.io.IOException;
import java.util.Scanner;

public class Driver {
    public static void main(String []args) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("-----------------------------------------------------");
        System.out.println("              WELCOME TO OUR LIBRARY");
        System.out.println("-----------------------------------------------------");

        //Answer regarding book being borrowed
        String answer = "";
        boolean check = false;


//object creation of register
        Register register = new Register();

        System.out.println("Do you want to Register to our Library?: Y/N");
        String registerNow = sc.nextLine();
        System.out.println("-----------------------------------------------------");
        if (registerNow.equalsIgnoreCase("y")) {
            register.writeFile("people.csv");
        }
        register.readFile("people.csv");

//admin login
        System.out.println("Do you want to log in as ADMIN?: Y/N");
        String alog = sc.nextLine();
        if (alog.equalsIgnoreCase("y")) {
            System.out.println("-----------------------------------------------------");
            System.out.println("Enter your Admin ID");
            String aid = sc.nextLine();
            System.out.println("Enter your Admin Password");
            String apss = sc.nextLine();
            System.out.println("-----------------------------------------------------");
            System.out.println();
            if (register.admin_login(aid, apss)) {
                System.out.println("-----------------------------------------------------");
                System.out.println("Admin login was successful!");
                System.out.println("-----------------------------------------------------");
                System.out.println();
                register.adminName();
                System.out.println();
                System.out.println("-----------------------------------------------------");
                System.out.println("Do you want to check the Registered Users?: Y/N ");
                String chk = sc.nextLine();
                System.out.println("-----------------------------------------------------");
                if (chk.equalsIgnoreCase("y")) {
                    System.out.println("-----------------------------------------------------");
                    System.out.println("List Of registered Users: ");
                    for (Student student : register.getStudents()) {
                        System.out.println("NAME: |" + student.getName() + "| EMAIL: |" + student.getEmailId() + "| USER ID: |" + student.getUserID() + "|");
                        System.out.println("-----------------------------------------------------");
                    }

                }
                System.out.println("Enter the number(s) of book(s) you want to enter");
                int numberBook = sc.nextInt();
                System.out.println("-----------------------------------------------------");
                System.out.println();
                for (int i = 0; i < numberBook; i++) {
                    try {
                        register.writeCSVBooks("books.csv");
                        System.out.println("Book(s) Registered Successfully!");
                        System.out.println("-----------------------------------------------------");

                    } catch (IOException e) {
                        System.out.println("Exception Handled");
                    }
                }
            } else {
                System.out.println("Wrong ID and/or password. Try again");
                System.out.println("-----------------------------------------------------");
            }
            System.out.println();
        }
        register.readCSVBooks("books.csv");


//client login
        System.out.println("Do you want to login as client? : Y/N ");
        String clientLogin = sc.next();
        if (clientLogin.equalsIgnoreCase("Y")) {
            System.out.println("Enter your userID");
            String uid = sc.next();
            System.out.println("Enter your password");
            String pss = sc.next();
            System.out.println();
            if (register.client_login(uid, pss)) {
                System.out.println("-----------------------------------------------------");
                System.out.println("Client login was successful!");
                System.out.println("-----------------------------------------------------");
                System.out.println();
                register.studentLoggedInName();
                System.out.println();
                check = true;
                register.displayLoggedInUser();
            } else {
                System.out.println("-----------------------------------------------------");
                System.out.println("Wrong ID and/or password. Try again");
                System.out.println("-----------------------------------------------------");
                System.exit(0);
            }
        }
//client borrowing books
        System.out.println("-----------------------------------------------------");
        if (check) {
            System.out.print("Do you want to borrow books? Y/N: ");
            answer = sc.next();
            System.out.println("-----------------------------------------------------");
            System.out.println();

            if (answer.equalsIgnoreCase("Y") && register.bookCheck() != true) {
                System.out.println("You will have to return the borrowed book before borrowing another");
                register.displayListOfBorrowedBooks();
            }
            if (answer.equalsIgnoreCase("Y") && register.bookCheck() == true) {
                System.out.println("-----------------------------------------------------");
                System.out.println("The Available Books : ");
                System.out.println("-----------------------------------------------------");
                for (Books book : register.getBooks()) {
                    System.out.println("Book: " + book.getBook_name() + "   " + " Book ID: " + book.getBookID());
                }
                System.out.println("-----------------------------------------------------");
                System.out.println("Enter ID of book to be borrowed");
                String bookId = sc.next();
                System.out.println("-----------------------------------------------------");
                register.borrowBook(bookId);
                System.out.println("-----------------------------------------------------");
            }


        }


        //client returning book
        if (check == true && register.bookCheck() != true) {
            System.out.println("-----------------------------------------------------");
            System.out.println("Do you want to return the book?: Y/N ");
            String bookReturn = sc.next();
            System.out.println("-----------------------------------------------------");
            if (register.bookCheck() == false && bookReturn.equalsIgnoreCase("Y")) {
                System.out.println("Your Borrowed Books List: ");
                register.displayListOfBorrowedBooks();

                System.out.println("-----------------------------------------------------");
                System.out.println("Enter the bookID for returning: ");
                String returnID = sc.next();
                System.out.println("-----------------------------------------------------");
                register.bookReturned(returnID);
                System.out.println();
                if (register.bookReturn != null) {
                    System.out.println("-----------------------------------------------------");
                    System.out.println("Return Successful...");
                    System.out.println("-----------------------------------------------------");
                } else {
                    System.out.println("-----------------------------------------------------");
                    System.out.println("Book Return Unsuccessful...");
                    System.out.println("-----------------------------------------------------");
                    System.out.println();
                }

                if (register.bookReturn != null) {
                    System.out.println("-----------------------------------------------------");
                    System.out.println("Available Library Books after returning...");
                    System.out.println("-----------------------------------------------------");
                    for (Books book : register.getBooks()) {
                        try {
                            System.out.println("Book: " + book.getBook_name() + "   Book ID: " + book.getBookID());
                            System.out.println("-----------------------------------------------------");
                        } catch (NullPointerException e) {
                            System.out.println("Exception Handled!");
                        }
                    }
                    System.out.println();
                }
            }

        }

        //Client unsubscribing
        if (check == true) {
            System.out.println("-----------------------------------------------------");
            System.out.println("Do you want to Logout your ID?: Y/N ");
            System.out.println("-----------------------------------------------------");
            String logout = sc.next();
            if (logout.equalsIgnoreCase("Y")) {
                register.logOut(logout);
                System.out.println("-----------------------------------------------------");
                System.out.println("Your have logged out successfully");
                System.out.println("-----------------------------------------------------");
            } else {
                System.out.println("-----------------------------------------------------");
                System.out.print("Do you want to Unsubscribe your ID? (Y/N) :");
                String unsubscription = sc.next();
                System.out.println("-----------------------------------------------------");
                if (unsubscription.equalsIgnoreCase("Y")) {
                    System.out.println("Enter the userID to be unsubscribed ");
                    String unsubscribeID = sc.next();
                    if (register.bookCheck() == true) {
                        register.studentUnsubscribed(unsubscribeID);
                        System.out.println();
                    }

                    if (register.bookCheck() != true) {
                        System.out.println("-----------------------------------------------------");
                        System.out.println("You will have to return all the borrowed books before returning");
                        System.out.println("-----------------------------------------------------");
                        System.out.println("The List of Books You Have To Submit Before Unsubscribing:");
                        System.out.println("-----------------------------------------------------");
                        register.displayListOfBorrowedBooks();
                    }
                }
            }
        }
        System.out.println("-----------------------------------------------------");
        System.out.println("THANK YOU FOR VISITING US!");
        System.out.println("WE HOPE TO SEE YOU AGAIN!");
        System.out.println("-----------------------------------------------------");
    }
}
