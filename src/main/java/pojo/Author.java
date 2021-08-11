package pojo;

public class Author {

    int authorId;
    AuthorBirth birth;
    String authorDescription;
    AuthorName authorName;
    String nationality;

    public Author() {
    }

    public Author(int authorId, AuthorBirth birth, String authorDescription, AuthorName authorName, String nationality) {
        this.authorId = authorId;
        this.birth = birth;
        this.authorDescription = authorDescription;
        this.authorName = authorName;
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

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setBirth(AuthorBirth birth) {
        this.birth = birth;
    }

    public void setAuthorDescription(String authorDescription) {
        this.authorDescription = authorDescription;
    }

    public void setAuthorName(AuthorName authorName) {
        this.authorName = authorName;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "\nAuthor{" +
                "authorId='" + authorId + '\'' +
                ", birth=" + birth +
                ", authorDescription='" + authorDescription + '\'' +
                ", authorName=" + authorName +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
