package nurseryanimal;
public class Animal{
    private String name;
    private String command;
    private String birthDate;

    
    public Animal(String name, String command, String birthDate) {
        this.name = name;
        this.command = command;
        this.birthDate = birthDate;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getCommand() {
        return command;
    }


    public void setCommand(String command) {
        this.command = command;
    }


    public String getBirthDate() {
        return birthDate;
    }


    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        StringBuilder lst = new StringBuilder();
        lst.append(String.format("Кличка животного: %s\n",name))
            .append(String.format("выполняет команды: %s\n",this.command))
            .append(String.format("дата рождения  %s\n",this.birthDate));
       
        return lst.toString();
    }
}