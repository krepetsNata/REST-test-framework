package pojo;

public class AuthorName {
    String first;
    String second;

    public AuthorName() {
    }

    public AuthorName(String first, String second) {
        this.first = first;
        this.second = second;
    }

    public String getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "\nAuthorNameData{" +
                "first='" + first + '\'' +
                ", second='" + second + '\'' +
                '}';
    }
}
