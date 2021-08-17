package constants;

public enum FileNames {
    CSV_FILE_AUTHORS("src/test/java/dataProviders/AuthorTestObject.csv"),
    CSV_FILE_NEW_AUTHOR("src/test/java/dataProviders/NewAuthorTestObject.csv"),
    CSV_FILE_BOOKS("src/test/java/dataProviders/BookTestObject.csv"),
    CSV_FILE_NEW_BOOK("src/test/java/dataProviders/NewBookTestObject.csv"),
    CSV_FILE_GENRES("src/test/java/dataProviders/GenreTestObject.csv"),
    CSV_FILE_NEW_GENRE("src/test/java/dataProviders/NewGenreTestObject.csv");

    private String fileName;

    FileNames(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
