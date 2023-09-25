package nurseryanimal;

public class Pet  extends Animal{
    private String petView;

public Pet(String name, String command, String birthDate) {
        super(name, command, birthDate);
        this.petView = petView;
    }

public String getPetView() {
    return petView;
}

public void setPetView(String petView) {
    this.petView = petView;
}


    
}
