package pojo;

import java.util.Objects;

public class BookAdditionalSize {
    float height;
    float length;
    float width;

    public BookAdditionalSize() {
    }

    public BookAdditionalSize(float height, float length, float width) {
        this.height = height;
        this.length = length;
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public float getLength() {
        return length;
    }

    public float getWidth() {
        return width;
    }

    public BookAdditionalSize setHeight(float height) {
        this.height = height;
        return this;
    }

    public BookAdditionalSize setLength(float length) {
        this.length = length;
        return this;
    }

    public BookAdditionalSize setWidth(float width) {
        this.width = width;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookAdditionalSize that = (BookAdditionalSize) o;
        return Float.compare(that.height, height) == 0 && Float.compare(that.length, length) == 0 && Float.compare(that.width, width) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, length, width);
    }

    @Override
    public String toString() {
        return "{" +
                "\"height\":" + height +
                ", \"width\":" + width +
                ", \"lenght\":" + length +
                "}";
    }
}
