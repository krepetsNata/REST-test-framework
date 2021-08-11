package pojo;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
    int bookId;
    String bookName;
    String bookLanguage;
    String bookDescription;
    int publicationYear;
    BookAdditional additional;

    public Book() {
    }

    public Book(int bookId, String bookName, String bookLanguage, String bookDescription, int publicationYear, BookAdditional additional) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookLanguage = bookLanguage;
        this.bookDescription = bookDescription;
        this.publicationYear = publicationYear;
        this.additional = additional;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookLanguage() {
        return bookLanguage;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public BookAdditional getAdditional() {
        return additional;
    }

    @Override
    public String toString() {
        return "\nBook{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookLanguage='" + bookLanguage + '\'' +
                ", bookDescription='" + bookDescription + '\'' +
                ", publicationYear='" + publicationYear + '\'' +
                ", additional=" + additional +
                '}';
    }
}
