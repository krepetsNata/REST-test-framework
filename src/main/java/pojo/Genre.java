package pojo;

import java.util.Objects;

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

    public Genre setGenreId(int genreId) {
        this.genreId = genreId;
        return this;
    }

    public Genre setGenreName(String genreName) {
        this.genreName = genreName;
        return this;
    }

    public Genre setGenreDescription(String genreDescription) {
        this.genreDescription = genreDescription;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return genreId == genre.genreId && genreName.equals(genre.genreName) && genreDescription.equals(genre.genreDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genreId, genreName, genreDescription);
    }

    @Override
    public String toString() {
        return "{" +
                "\"genreDescription\":\"" + genreDescription + "\"" +
                ", \"genreId\":" + genreId +
                ", \"genreName\":\"" + genreName + "\"" +
                "}";
    }
}
