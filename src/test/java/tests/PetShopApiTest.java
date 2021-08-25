package tests;

import model.Pet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.PetCreationService;
import utils.PetShopResponseService;
import utilsAPI.PetStatus;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.CoreMatchers.*;


public class PetShopApiTest {

    private static final File DEFAULT_PET_FILE = new File("src/test/resources/json/defaultPet.json");
    private static final File SCHEME_FILE = new File("src/test/resources/json/petScheme.json");

    private static final Pet winniePooh = PetCreationService.getWinniePooh();


    @Test
    @DisplayName("Check validate json scheme")
    public void validateJsonSchemeTest() {
        PetShopResponseService.addNewPetWithJsonFile(DEFAULT_PET_FILE)
                .then()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchema(SCHEME_FILE));
    }

    @Test
    @DisplayName("Check add new pet")
    public void addNewPetTest() {
        PetShopResponseService.addNewPetWithPetObject(winniePooh)
                .then()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchema(SCHEME_FILE))
                .body("name", equalTo("winnie pooh"));
    }


    @Test
    @DisplayName("Check delete by ID")
    public void deleteByIdTest() {
        int id = winniePooh.getId();

        //add pet for deleting
        PetShopResponseService.addNewPetWithPetObject(winniePooh);

        //delete and assert
        PetShopResponseService.deletePetById(id)
                .then()
                .assertThat()
                .statusCode(200)
                .body("message", equalTo(String.valueOf(id)));
    }

    @Test
    @DisplayName("Check get pet by ID")
    public void getPetById() {
        int id = winniePooh.getId();

        //add pet for getting
        PetShopResponseService.addNewPetWithPetObject(winniePooh);

        //get pet and assert
        PetShopResponseService.getPetById(id)
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", equalTo(id));
    }

    @Test
    @DisplayName("Check get pets by pet status")
    public void getPetsByStatus() {
        //all pets should have "sold" status
        PetShopResponseService.getPetByStatus(PetStatus.SOLD)
                .then()
                .assertThat()
                .statusCode(200)
                .body("status", anything(PetStatus.SOLD));
    }
}
