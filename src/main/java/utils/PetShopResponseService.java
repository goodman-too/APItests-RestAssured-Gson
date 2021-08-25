package utils;

import com.google.gson.Gson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import endpoints.EndPointsPetShop;
import model.Pet;
import utilsAPI.SpecificationAPI;

import java.io.File;

import static io.restassured.RestAssured.given;


public class PetShopResponseService {

    private static final RequestSpecification requestSpecification = SpecificationAPI.getRequestSpecification();
    private static final Gson gson = new Gson();


    public static Response addNewPetWithPetObject(Pet pet) {
        return given()
                .spec(requestSpecification)
                .body(gson.toJson(pet))
                .when()
                .post(EndPointsPetShop.PET);
    }

    public static Response addNewPetWithJsonFile(File jsonPetFile) {
        return given()
                .spec(requestSpecification)
                .body(jsonPetFile)
                .when()
                .post(EndPointsPetShop.PET);
    }

    public static Response getPetById(int id) {
        return given()
                .spec(requestSpecification)
                .when()
                .get(EndPointsPetShop.PET + "/" + id);
    }

    public static Response getPetByStatus(String status) {
        return given()
                .spec(requestSpecification)
                .when()
                .get(EndPointsPetShop.PET + EndPointsPetShop.STATUS + "?status=" + status);
    }

    public static Response deletePetById(int id) {
        return given()
                .spec(requestSpecification)
                .when()
                .delete(EndPointsPetShop.PET + "/" + id);
    }
}
