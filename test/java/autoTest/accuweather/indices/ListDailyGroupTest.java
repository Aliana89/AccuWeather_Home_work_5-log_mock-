package autoTest.accuweather.indices;

import autoTest.accuweather.AbstractAccuweatherTest;
import autoTest.accuweather.Indices.metadata.Metadata;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
//надо добавить зависимость 
//<groupId>io.rest-assured</groupId>
//<artifactId>rest-assured</artifactId>
import static io.restassured.RestAssured.given;

public class ListDailyGroupTest extends AbstractAccuweatherTest {

    @Test
    void getListDailyGroupIndices() {

        List<Metadata> response = given()
            //нет метода getApiKey
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/indices/v1/daily/groups")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", Metadata.class);

        Assertions.assertEquals(47,response.size());
        Assertions.assertEquals("All API", response.get(0).getName());
    }
}
