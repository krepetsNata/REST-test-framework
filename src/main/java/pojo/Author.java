package pojo;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

import java.util.Objects;

public class Author {
    @CsvBindByPosition(position = 0)
    int authorId;

    @CsvBindByPosition(position = 7)
    String authorDescription;

    @CsvBindByName
    AuthorName authorName;

    @CsvBindByName
    AuthorBirth birth;

    @CsvBindByPosition(position = 3)
    String nationality;

    public Author() {
    }

    public Author(String authorDescription, int authorId, AuthorName authorName, AuthorBirth birth, String nationality) {
        this.authorDescription = authorDescription;
        this.authorId = authorId;
        this.authorName = authorName;
        this.birth = birth;
        this.nationality = nationality;
    }

    public int getAuthorId() {
        return authorId;
    }

    public String getAuthorDescription() {
        return authorDescription;
    }

    public String getNationality() {
        return nationality;
    }

    public AuthorBirth getBirth() {
        return birth;
    }

    public AuthorName getAuthorName() {
        return authorName;
    }

    public Author setAuthorId(int authorId) {
        this.authorId = authorId;
        return this;
    }

    public Author setBirth(AuthorBirth birth) {
        this.birth = birth;
        return this;
    }

    public Author setAuthorDescription(String authorDescription) {
        this.authorDescription = authorDescription;
        return this;
    }

    public Author setAuthorName(AuthorName authorName) {
        this.authorName = authorName;
        return this;
    }

    public Author setNationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return authorId == author.authorId && authorDescription.equals(author.authorDescription) && authorName.equals(author.authorName) && birth.equals(author.birth) && nationality.equals(author.nationality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorDescription, authorId, authorName, birth, nationality);
    }

    @Override
    public String toString() {
        return "{" +
                "\"authorDescription\":\"" + authorDescription + "\"" +
                ", \"authorId\":" + authorId +
                ", \"authorName\":" + authorName +
                ", \"birth\":" + birth +
                ", \"nationality\":\"" + nationality + "\"" +
                "}";
    }
}
