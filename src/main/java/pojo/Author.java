package pojo;

public class Author {
    String authorDescription;
    int authorId;
    AuthorName authorName;
    AuthorBirth birth;
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
