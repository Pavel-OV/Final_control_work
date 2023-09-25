package nurseryanimal;

import java.util.ArrayList;
import java.util.List;

public class PetRegistry {

    private List<Animal> animals;
    
    private String filename;

    public PetRegistry (String filename){
        animals = new ArrayList<>();
        
        this.filename = filename;
    }
    
}
