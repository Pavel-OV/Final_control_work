package nurseryanimal;

public class Packanimal extends Animal {
     private String packAnimalView;

    public Packanimal(String name, String command, String birthDate) {
        super(name, command, birthDate);
        this.packAnimalView = packAnimalView;
       
    }

    public String getPackAnimalView() {
        return packAnimalView;
    }

    public void setPackAnimalView(String packAnimalView) {
        this.packAnimalView = packAnimalView;
    }
    
}
