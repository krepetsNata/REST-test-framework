package pojo;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookAdditional that = (BookAdditional) o;
        return pageCount == that.pageCount && size.equals(that.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageCount, size);
    }

    @Override
    public String toString() {
        return "{" +
                "\"pageCount\":" + pageCount +
                ", \"size\":" + size +
                "}";
    }
}
