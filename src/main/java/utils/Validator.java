package utils;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;
import response.BaseResponse;

public class Validator {

    /**
     * Method checks status code for received response
     *
     * @param baseResponse       wrapper for Response which we got
     * @param expectedStatusCode expected status code
     */
    @Step("Validate status code for received response.")
    public static void validateStatusCode(BaseResponse baseResponse, int expectedStatusCode) {
        Assert.assertEquals(baseResponse.getStatusCode(), expectedStatusCode, "Actual StatusCode not matching to Expected one.");
    }

    /**
     * Method checks if the data of the objects is the same before the CRUD operation and after
     *
     * @param baseResponse wrapper for Response which we got
     * @param expectedObj  expected object(before operation) which will be compared with actual object (after operation)
     * @return actual object from response
     */
    @Step("Check if the data of the objects is the same before the CRUD operation and after.")
    public static Object validateSameObjects(BaseResponse baseResponse, Object expectedObj) {
        Response response1 = baseResponse.getResponse();
        //System.out.println(PrettyJsonPrinting.getPrettyJsonString(response1.getBody().asString()));
        Object actualObj = response1.jsonPath().getObject(".", expectedObj.getClass());
        Assert.assertEquals(actualObj.toString(), expectedObj.toString(), "Objects are not match");
        return actualObj;
    }

    /**
     * Method returns actual object from response
     *
     * @param baseResponse wrapper for Response which we got
     * @param expectedObj  expected object(before operation)
     * @return actual object from response
     */
    public static Object getActualObject(BaseResponse baseResponse, Object expectedObj) {
        Response response1 = baseResponse.getResponse();
        Object actualObj = response1.jsonPath().getObject(".", expectedObj.getClass());
        return actualObj;
    }
}
