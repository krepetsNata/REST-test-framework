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

    public BookAdditional setPageCount(int pageCount) {
        this.pageCount = pageCount;
        return this;
    }

    public BookAdditional setSize(BookAdditionalSize size) {
        this.size = size;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                "\"pageCount\":" + pageCount +
                ", \"size\":" + size +
                "}";
    }
}
