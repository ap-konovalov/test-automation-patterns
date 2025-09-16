import org.junit.jupiter.api.Test;
import ru.nmt.dto.Pet;
import ru.nmt.steps.PetSteps;

public class PostPetWithStepDefinitionTests {

    private final PetSteps petSteps = new PetSteps();

    @Test
    void addPetTest() {
        Pet addPetResponse = petSteps.cretePet("Chirik");

        Pet getPetResponse = petSteps.getPet(addPetResponse.id());

        petSteps.verifyPetData(addPetResponse, getPetResponse);
    }
    
}
