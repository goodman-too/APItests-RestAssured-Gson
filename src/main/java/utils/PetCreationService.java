package utils;

import model.*;
import utilsAPI.PetStatus;

import java.util.ArrayList;
import java.util.Collections;


public class PetCreationService {

    public static Pet getWinniePooh() {
        Category category = new Category(114, "bear");
        TagPet tagPet = new TagPet(117799, "winnie pooh");
        String photoUrl = "https://imgur.com/6cddSAw";

        return new Pet(117799, category, "winnie pooh",
                new ArrayList<>(Collections.singletonList(photoUrl)),
                new ArrayList<>(Collections.singletonList(tagPet)),
                PetStatus.AVAILABLE);
    }
}
