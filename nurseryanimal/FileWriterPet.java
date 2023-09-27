package nurseryanimal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileWriterPet {

     public void writeFile(List<Animal> animals, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            for (Animal animal : animals) {
                String animalType = "";
                String commands = animal.getCommand();
                String birthDate = animal.getBirthDate();

                if (animal instanceof Pet) {
                    Pet pet = (Pet) animal;
                    animalType = "Pet";
                    writer.write(String.format("%s\t%s\t%s\t%s\t%s\n", pet.getName(), animalType, commands, birthDate, pet.getPetView()));
                } else if (animal instanceof Packanimal) {
                    Packanimal packAnimal = (Packanimal) animal;
                    animalType = "PackAnimal";
                    writer.write(String.format("%s\t%s\t%s\t%s\t%s\n", packAnimal.getName(), animalType, commands, birthDate, packAnimal.getPackAnimalView()));
                }
            }
            System.out.println("Данные успешно добавлены в файл.");
        } catch (IOException e) {
            System.out.println("Ошибка при записи данных в файл: " + e.getMessage());
        }
    }

      public List<Animal> readFromFile(String filename) {
        List<Animal> animals = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\t");
                String name = data[0];
                String type = data[1];
                String commands = data[2];
                String birthDate = data[3];
                String petType = data[4];

                if ("Pet".equals(type)) {
                    animals.add(new Pet(name, commands, birthDate, petType));
                } else if ("PackAnimal".equals(type)) {
                    animals.add(new Packanimal(name, commands, birthDate, petType));
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении данных из файла: " + e.getMessage());
        }
        return animals;
    }
    
}
