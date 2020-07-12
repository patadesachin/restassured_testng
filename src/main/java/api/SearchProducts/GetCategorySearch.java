package api.SearchProducts;

import api.BaseAPI;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GetCategorySearch extends BaseAPI {

    String apiPath="/products/search";
    String accessToken;
    String baseSiteId;
    String query_key;
    String query_value;

    public GetCategorySearch(String baseURI,String baseSiteId,String query_key,String query_value,String accessToken) {
        super(baseURI);
        this.baseSiteId= baseSiteId;
        this.apiPath = "/rest/v2/"+ baseSiteId + apiPath;
        System.out.print("url general suggestion base uri "+baseURI+"\n base site id "+baseSiteId+"\n api path "+apiPath+"\n Access Token "+accessToken);
        this.accessToken = accessToken;
        this.query_key = query_key;
        this.query_value = query_value;
    }

    
    
    public void validateSchema(String json_to_validate) {
     /*   JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder()
      .setValidationConfiguration(
        ValidationConfiguration.newBuilder()
          .setDefaultVersion(SchemaVersion.DRAFTV4).freeze())
            .freeze();
       */     
      requestSpecBuilder.setBaseUri(baseURI);
      requestSpecBuilder.setBasePath(apiPath);
      requestSpecBuilder.setContentType("application/x-www-form-urlencoded");
      requestSpecBuilder.addHeader("Authorization", accessToken);    
      requestSpecBuilder.addQueryParam(query_key, query_value);
      System.out.print("\n *******Schema request specification base uri "+baseURI+"\n base site id "
      +baseSiteId+"\n api path "+apiPath+"\n Access Token "+"query+key"+query_key+"query_value"+query_value+accessToken);
      given().spec(requestSpecBuilder.build()).get().then().assertThat()
      .body(matchesJsonSchemaInClasspath(json_to_validate));
}


    @Override
    protected void createRequest() {
        System.out.print("request specification base uri "+baseURI+"\n base site id "+baseSiteId+"\n api path "+apiPath+"\n Access Token "+accessToken);
        requestSpecBuilder.setBaseUri(baseURI);
        requestSpecBuilder.setBasePath(apiPath);
        requestSpecBuilder.setContentType("application/x-www-form-urlencoded");
        requestSpecBuilder.addHeader("Authorization", accessToken);    
        requestSpecBuilder.addQueryParam(query_key, query_value);
        requestSpecification=requestSpecBuilder.build();
    }

    @Override
    protected void executeRequest() {
        apiResponse = given().spec(requestSpecification).get();
        System.out.println("Preety Print Response"+apiResponse.prettyPrint());
    }

    @Override
    protected void validateResponse() {
        responseSpecBuilder.expectStatusCode(expectedStatusCode);
        responseSpecification=responseSpecBuilder.build();
        apiResponse.then().spec(responseSpecification);

    }
}
