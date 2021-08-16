package pojo;

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
    public String toString() {
        return "{" +
                "\"height\":" + height +
                ", \"width\":" + width +
                ", \"lenght\":" + length +
                "}";
    }
}
