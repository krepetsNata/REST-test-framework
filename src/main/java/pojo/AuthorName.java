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

    public AuthorName setFirst(String first) {
        this.first = first;
        return this;
    }

    public AuthorName setSecond(String second) {
        this.second = second;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                "\"first\":\"" + first + "\"" +
                ", \"second\":\"" + second + "\"" +
                "}";
    }
}
