package utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;
import response.BaseResponse;

import java.util.List;
import java.util.Objects;

public class Validator {

    /**
     * Method checks status code for received response
     *
     * @param baseResponse       wrapper for Response which we got
     * @param expectedStatusCode expected status code
     */
    @Step("Validate status code for received response.")
    @Attachment
    public static void validateStatusCode(BaseResponse baseResponse, int expectedStatusCode) {
        Allure.addAttachment("Response for validate status code", baseResponse.getResponse().asPrettyString());
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
    @Attachment
    public static Object validateSameObjects(BaseResponse baseResponse, Object expectedObj) {
        Allure.addAttachment("Response for validate same objects", baseResponse.getResponse().asPrettyString());
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

    /**
     * Method returns if actual list isEmpty from response
     *
     * @param list          list with objects from response
     * @param expectedState expected state of list
     * @return bool state of list - empty or not
     */
    @Attachment
    public static boolean validateListIsEmpty(List list, boolean expectedState) {

        boolean empty = list.isEmpty();

        boolean result = empty & expectedState;
//        if(!expectedState)
//            result = true;
//        if(!empty & !expectedState)
//            result = true;

        if (result)
            Assert.assertTrue(empty, "List is Not empty");
        else
            Assert.assertFalse(empty, "List is empty");
        Allure.addAttachment("List is empty attachment", String.format("list.isEmpty()=%s, expectedState=%s", list.isEmpty(), expectedState));
        return result;
    }

    /**
     * Method returns if actual object isNull from response
     *
     * @param obj           from response
     * @param expectedState expected state of obj
     *                      * @return actual state of obj - null or not
     */
    @Attachment
    public static boolean validateObjectIsNull(Object obj, boolean expectedState) {

        boolean empty = Objects.isNull(obj);

        boolean result = empty & expectedState;
//        if(expectedState == false)
//            result = empty & expectedState?true:true;
//        if(empty == false & expectedState == false)
//            result = empty & expectedState?false:true;

        if (result)
            Assert.assertTrue(empty, "Obj is NOT null");
        else
            Assert.assertFalse(empty, "Obj is null");
        Allure.addAttachment("List is empty attachment", String.format("list.isNull()=%s, expectedState=%s", empty, expectedState));
        return result;
    }
}
