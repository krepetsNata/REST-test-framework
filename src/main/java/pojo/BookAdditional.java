package pojo;

public class BookAdditional {
    int pageCount;
    BookAdditionalSize size;

    public BookAdditional() {
    }

    public BookAdditional(int pageCount, BookAdditionalSize size) {
        this.pageCount = pageCount;
        this.size = size;
    }

    public int getPageCount() {
        return pageCount;
    }

    public BookAdditionalSize getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "\nBookAdditional{" +
                "pageCount=" + pageCount +
                ", size=" + size +
                '}';
    }
}
