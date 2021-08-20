package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesReader {

    private Properties props;

    public PropertiesReader(String fileName) {
//        String rootPath = Thread.currentThread().getContextClassLoader().getResource(".").getPath();
        Path resourceDirectory = Paths.get("src", "main", "resources");
        String filePath = resourceDirectory.toFile().getAbsolutePath() + "\\" + fileName;

        try (InputStream input = new FileInputStream(filePath)) {
            this.props = new Properties();
            this.props.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String get(String property) {
        return this.props.getProperty(property);
    }
}
