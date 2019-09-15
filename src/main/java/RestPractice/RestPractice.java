package RestPractice;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class RestPractice {


    //"http://3.87.64.64:8000/api/hello" is or this ec2-3-87-64-64.compute-1.amazonaws.com
    //is your baseURL| HostNAme
    //8000 is the port for this particular
    //entry point \ base path to your Rest API ---/api
    //eventually /hello is the resource you want to get

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI="http://3.87.64.64";
        RestAssured.port=8000;
        RestAssured.basePath="/api";
        //above will generate a BAse REQUEST URL OF http://3.87.64.64:8000/api
    }

    @Test
    public void printouts(){
        Response result = RestAssured.get("/hello");
        // this code will give you status code
        System.out.println(result.statusCode() );
        // this code will give you the body in String format
        System.out.println(result.asString() );
        System.out.println(result.getBody().asString());
        System.out.println(result.body().asString());
        System.out.println(result.getHeader("content-type") );
    }


    @Test
    public void Hello_Endpoint_Test(){
        Response result = RestAssured.get("/hello");
        assertEquals(200, result.statusCode());
        assertEquals("Hello from Sparta", result.asString());
        // rest assured lib provide multiple method with same meaning
        // like getHeader = header   , getContentType = ContentType , statusCode = getStatusCode
        assertEquals("text/plain;charset=UTF-8", result.header("content-type") );
    }

    @Test
    public void Hello_Endpoint_HeaderContains_Test(){

        // 1st approach to test header exists is by checking the value is null or not
        Response response = RestAssured.get("/hello");
//        String headerValue = response.getHeader("Date");
//        System.out.println(headerValue);
//        assertNotNull(headerValue);



        // 2nd approach : use existing method

        boolean dateHeaderExists = response.getHeaders().hasHeaderWithName("Date");
        assertTrue(dateHeaderExists);

        assertEquals("17", response.getHeader("content-length"));
    }


}
