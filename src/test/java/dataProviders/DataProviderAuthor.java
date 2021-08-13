package dataProviders;

import org.testng.annotations.DataProvider;
import pojo.Author;
import utils.ParsingAndConvertations;

import java.util.List;

public class DataProviderAuthor {

    @DataProvider(name = "dpTestAuthor")
    public Object[][] dpGetListAuthorsFromFile() { //A TestNG DataProvider must return either Object[][] or Iterator<Object[]>
        ParsingAndConvertations parsingAndConvertations = new ParsingAndConvertations();
        List<Author> authorsObjList = parsingAndConvertations.getAuthorsList();
        // authorsObjList.forEach(System.out::println);
        Object[][] authorsObj = new Object[authorsObjList.size()][];

        for (int i = 0; i < authorsObjList.size(); i++) {
            for (int j = 0; j < 1; j++) {
                authorsObj[i] = new Object[1];
                authorsObj[i][j] = authorsObjList.get(i);
            }
        }
        return authorsObj;
    }

}
