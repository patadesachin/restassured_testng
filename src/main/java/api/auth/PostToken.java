package api.auth;

import api.BaseAPI;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;

import com.google.common.base.Joiner;
import com.jayway.jsonpath.JsonPath;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class PostToken extends BaseAPI {

    String apiPath="authorizationserver/oauth/token";
    String contentType;
    EncoderConfig encoderConfig;
    Map<String,String> bodyParams;
    String accessToken;

    public PostToken(String baseURI) {
        super(baseURI);
        bodyParams = new HashMap<String, String>();
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    public void setEncoderConfig(EncoderConfig encoderConfig) {
        this.encoderConfig = encoderConfig;
    }
    public void addBodyParam(String key, String value) {
        bodyParams.put(key,value);
    }
    public String getAccessToken() {
        return accessToken;
    }

    @Override
    protected void createRequest() {
        requestSpecBuilder.setBaseUri(baseURI);
        requestSpecBuilder.setBasePath(apiPath);
        requestSpecBuilder.setContentType(contentType);
        requestSpecBuilder.setBody(Joiner.on("&").withKeyValueSeparator("=").join(bodyParams));
        requestSpecification = requestSpecBuilder.build();
    }

    @Override
    protected void executeRequest() {
        System.out.print("\n api path "+apiPath);
        System.out.print("\n base path "+baseURI);
        System.out.print("\n Body Params "+bodyParams);
        apiResponse = given().spec(requestSpecification).post();
    }

    @Override
    protected void validateResponse() {
        responseSpecBuilder.expectStatusCode(expectedStatusCode);
        responseSpecification = responseSpecBuilder.build();
        System.out.print("Response Specification "+responseSpecification.response().toString());
        apiResponse.then().spec(responseSpecification);
        accessToken= JsonPath.read(apiResponse.asString(),"$.access_token");
    }
}
