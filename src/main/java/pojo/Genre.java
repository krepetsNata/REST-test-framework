package pojo;

public class Genre {
    int genreId;
    String genreName;
    String genreDescription;

    public Genre() {
    }

    public Genre(int genreId, String genreName, String genreDescription) {
        this.genreId = genreId;
        this.genreName = genreName;
        this.genreDescription = genreDescription;
    }

    public String getGenreDescription() {
        return genreDescription;
    }

    public int getGenreId() {
        return genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    @Override
    public String toString() {
        return "\nGenre{" +
                "genreDescription='" + genreDescription + '\'' +
                ", genreId=" + genreId +
                ", genreName='" + genreName + '\'' +
                '}';
    }
}
