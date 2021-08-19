package pojo;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorName that = (AuthorName) o;
        return first.equals(that.first) && second.equals(that.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString() {
        return "{" +
                "\"first\":\"" + first + "\"" +
                ", \"second\":\"" + second + "\"" +
                "}";
    }
}
