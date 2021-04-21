package apiHelpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Advertisement;
import org.json.simple.JSONObject;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public abstract class AdvertisementApi {

    public static void createNewAdvertisementUsingAPI(Advertisement advert) {
        JSONObject body = new JSONObject();
        body.put("name", "Advert" + advert.name());
        body.put("price", advert.price());

        var response = given()
                .contentType("application/json")
                .log().all()
                .when()
                .body(body)
                .post();

        assertThat(response.statusCode()).isEqualTo(200);
    }

    public static List<Advertisement> getAllAdvertisementUsingAPI() throws JsonProcessingException {

        var response = given()
                .contentType("application/json")
                .log().all()
                .when()
                .get();

        assertThat(response.statusCode()).isEqualTo(200);

        final ObjectMapper objectMapper = new ObjectMapper();
        var advertisementList = objectMapper.readValue(response.body().asString(), new TypeReference<List<Advertisement>>() {
        });
        return advertisementList;
    }

    public static Advertisement findAdvertUsingApi(String advertSuffix) throws JsonProcessingException {

        return getAllAdvertisementUsingAPI()
                .stream()
                .filter(it -> it.name.equals("Advert" + advertSuffix))
                .findFirst()
                .orElse(null);
    }
}
