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

    public Book setBookId(int bookId) {
        this.bookId = bookId;
        return this;
    }

    public Book setBookName(String bookName) {
        this.bookName = bookName;
        return this;
    }

    public Book setBookLanguage(String bookLanguage) {
        this.bookLanguage = bookLanguage;
        return this;
    }

    public Book setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
        return this;
    }

    public Book setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
        return this;
    }

    public Book setAdditional(BookAdditional additional) {
        this.additional = additional;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                "\"bookId\":" + bookId +
                ", \"bookName\":\"" + bookName + "\"" +
                ", \"bookLanguage\":\"" + bookLanguage + "\"" +
                ", \"bookDescription\":\"" + bookDescription + "\"" +
                ", \"publicationYear\":" + publicationYear +
                ", \"additional\":" + additional +
                "}";
    }
}
