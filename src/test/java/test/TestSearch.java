package test;

import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import api.SearchProducts.GetAvailableSuggestion;
import api.SearchProducts.GetCategorySearch;
import utils.GenericDataProvider;
import utils.CSVAnnotation;
import utils.PropertiesManager;

public class TestSearch extends BaseTest {

  public static final Logger logger = Logger.getLogger(TestSearch.class);

  @Test(groups = { "Search_validation",
      "Regression" }, dataProvider = "dataproviderForTestCase", dataProviderClass = GenericDataProvider.class)
  @CSVAnnotation.CSVFileParameters(path = "src\\test\\java\\test-data\\Search.csv", delimiter = "###")
  public void validate_AvailableSection(int rowNo, Map<String, String> inputData) {
    String expectedTitle = inputData.get("CategoryId");
    System.out.println("input value " + expectedTitle);
    System.out.println("input data " + inputData);
    GetAvailableSuggestion getAvailableSection = new GetAvailableSuggestion(PropertiesManager.getProperty("baseURI"),
        PropertiesManager.getProperty("basesiteid"),inputData.get("CategoryId"), inputData.get("CategoryName"),accessToken);
    getAvailableSection.setExpectedStatusCode(200);

  }

  @Test(groups = { "Schema_Validation","Regression" })
  public void validate_AvailableSection_Schema() {
      GetAvailableSuggestion getAvailableSection = new GetAvailableSuggestion(PropertiesManager.getProperty("baseURI"),PropertiesManager.getProperty("basesiteid"),"term","mac",accessToken);
      getAvailableSection.setExpectedStatusCode(200);
      getAvailableSection.perform();
      getAvailableSection.validateSchema("search_suggestion.json");
    }

    @Test(groups = { "Search_validation",
      "Regression" }, dataProvider = "dataproviderForTestCase", dataProviderClass = GenericDataProvider.class)
  @CSVAnnotation.CSVFileParameters(path = "src\\test\\java\\test-data\\Category_Search.csv", delimiter = "###")
  public void validate_Category(int rowNo, Map<String, String> inputData) {
    String expectedTitle = inputData.get("CategoryId");
    System.out.println("input value " + expectedTitle);
    System.out.println("input data " + inputData);
    GetCategorySearch getCategorySearch = new GetCategorySearch(PropertiesManager.getProperty("baseURI"),
        PropertiesManager.getProperty("basesiteid"),inputData.get("CategoryId"), inputData.get("CategoryName"),accessToken);
        getCategorySearch.setExpectedStatusCode(200);

  }

  @Test(groups = { "Schema_Validation","Regression" })
  public void validate_Category_Schema() {
      GetCategorySearch getCategorySearch = new GetCategorySearch(PropertiesManager.getProperty("baseURI"),PropertiesManager.getProperty("basesiteid"),"category","MVM7-Hotbuy",accessToken);
      getCategorySearch.setExpectedStatusCode(200);
      getCategorySearch.perform();
      getCategorySearch.validateSchema("search_category.json");
    }
}