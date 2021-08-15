package helper;

import config.Config;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.testng.SkipException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestHelper {
    private static Logger logger = Logger.getLogger(RequestHelper.class.getName());

    public static Response doPostRequest(String endpoint, String body) {
        RestAssured.defaultParser = Parser.JSON;
        try {
            Header h1= new Header("Content-type", "application/json");
            Header h2 = new Header("Authorization", "Bearer " + Config.ACCESS_TOKEN);
            List<Header> list = new ArrayList<>();
            list.add(h1);
            list.add(h2);
            Headers headers = new Headers(list);
            return
                    RestAssured.
                            given().
                            headers(headers).
                            body(body).
                            post(new URL(Config.BASE_URL_PATH + endpoint));
        } catch (MalformedURLException e){
            logger.log(Level.WARNING, "Error was happened while send POST request to " + Config.BASE_URL_PATH + endpoint);
        }
        throw new SkipException("");
    }

    public static Response doGetRequest(String endpoint) {
        logger.info("go to " + Config.BASE_URL_PATH + endpoint);
        RestAssured.defaultParser = Parser.JSON;
        try {
            return
                    RestAssured.
                            given().
                            header(new Header("Content-type", "application/json")).
                            get(new URL(Config.BASE_URL_PATH + endpoint));
        } catch (MalformedURLException e){
            logger.log(Level.WARNING, "Error was happened while send GET request to " + Config.BASE_URL_PATH + endpoint);
        }
        throw new SkipException("");
    }

    public static Response doDeleteRequest(String endpoint) {
        RestAssured.defaultParser = Parser.JSON;
        try {
            Header h1= new Header("Content-type", "application/json");
            Header h2 = new Header("Authorization", "Bearer " + Config.ACCESS_TOKEN);
            List<Header> list = new ArrayList<>();
            list.add(h1);
            list.add(h2);
            Headers headers = new Headers(list);
            return
                    RestAssured.
                            given().
                            headers(headers).
                            delete(new URL(Config.BASE_URL_PATH + endpoint));
        } catch (MalformedURLException e){
            logger.log(Level.WARNING, "Error was happened while send DELETE request to " + Config.BASE_URL_PATH + endpoint);
        }
        throw new SkipException("");
    }

    public static Response doPutRequest(String endpoint, String body) {
        RestAssured.defaultParser = Parser.JSON;
        try {
            Header h1= new Header("Content-type", "application/json");
            Header h2 = new Header("Authorization", "Bearer " + Config.ACCESS_TOKEN);
            List<Header> list = new ArrayList<>();
            list.add(h1);
            list.add(h2);
            Headers headers = new Headers(list);
            return
                    RestAssured.
                            given().
                            headers(headers).
                            body(body).
                            put(new URL(Config.BASE_URL_PATH + endpoint));
        } catch (MalformedURLException e){
            logger.log(Level.WARNING, "Error was happened while send POST request to " + Config.BASE_URL_PATH + endpoint);
        }
        throw new SkipException("");
    }
}
