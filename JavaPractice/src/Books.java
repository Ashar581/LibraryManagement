public class Books {

    private String book_name;

    private String bookID;

    private String author;

    public Books(String book_name, String author, String bookID) {

        this.book_name = book_name;

        this.author = author;

        this.bookID = bookID;
    }

    public String getBook_name() {
        return book_name;
    }


    public String getAuthor() {
        return author;
    }

    public String getBookID(){
        return this.bookID;
    }


}
