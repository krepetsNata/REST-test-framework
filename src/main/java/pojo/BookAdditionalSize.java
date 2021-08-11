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

    @Override
    public String toString() {
        return "\nBookAdditionalSize{" +
                "height=" + height +
                ", lenght=" + length +
                ", width=" + width +
                '}';
    }
}
