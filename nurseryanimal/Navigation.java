package nurseryanimal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.util.Scanner;

public class Navigation {

    List<Animal> animals;
    private Counter count;
    private String filename;

    public Navigation(String filename) {
        animals = new ArrayList<>();
        count = new Counter();
        this.filename = filename;
        loadAnimalsFromFile();
    }

    private void loadAnimalsFromFile() {
        FileWriterPet fileWriter = new FileWriterPet();
        animals = fileWriter.readFromFile(filename);
        // Обновляем счетчик животных, учитывая количество загруженных из файла
        count.setCount(animals.size());
    }

    public void addAnimal(Animal animal) throws Exception {
        if (animal instanceof Pet) {
            Pet pet = (Pet) animal;
            if (pet.getName() == null || pet.getCommand() == null || pet.getBirthDate() == null
                    || pet.getPetView() == null) {
                throw new IllegalArgumentException("Недостаточно данных для добавления нового питомца.");
            }
        } else if (animal instanceof Packanimal) {
            Packanimal packAnimal = (Packanimal) animal;
            if (packAnimal.getName() == null || packAnimal.getCommand() == null || packAnimal.getBirthDate() == null
                    || packAnimal.getPackAnimalView() == null) {
                throw new IllegalArgumentException("Недостаточно данных для добавления нового животного.");
            }
        }
        animals.add(animal);
        count.add();
    }

    public void viewAnimalList() {
        System.out.println("Список с животными:");
        System.out.println("Имя\t\tВид\t\tКоманды\t\tДата рождения");

        for (Animal animal : animals) {
            String animalType;
            if (animal instanceof Pet) {
                animalType = "Pet";
            } else if (animal instanceof Packanimal) {
                animalType = "PackAnimal";
            } else {
                animalType = "Неизвестный тип";
            }
            System.out.printf("%s\t\t%s\t\t%s\t\t%s\n", animal.getName(), animalType, animal.getCommand(),
                    animal.getBirthDate());
        }
    }

    private void removeAnimal(String animalName) {
        Animal removedAnimal = null;
        for (Animal animal : animals) {
            if (animal.getName().equalsIgnoreCase(animalName)) {
                removedAnimal = animal;
                break;
            }
        }
        if (removedAnimal != null) {
            animals.remove(removedAnimal);
            count.remove();
            System.out.println("Животное " + animalName + " успешно удалено из реестра.");
        } else {
            System.out.println("Животное не найдено.");
        }
    }

    public void infoAnimalCommands(String animalName) {
        for (Animal animal : animals) {
            if (animal.getName().equalsIgnoreCase(animalName)) {
                System.out.println("Животное: " + getViewAnimal(animal));
                System.out.println("Команды для " + animal.getName() + ": " + animal.getCommand());
                return;
            }
        }
        System.out.println("Животное не найдено.");
    }

    private void saveInfoToFile() {
        FileWriterPet fileWriter = new FileWriterPet();
        fileWriter.writeFile(animals, filename);
        System.out.println("Данные успешно сохранены в файл.");

    }

    private String getViewAnimal(Animal animal) {
        if (animal instanceof Pet) {
            return ((Pet) animal).getPetView();
        } else if (animal instanceof Packanimal) {
            return ((Packanimal) animal).getPackAnimalView();
        } else {
            return "Неизвестный вид животного";
        }
    }

    public void teachAnimalCommands(String animalName, String newCommands) {
        for (Animal animal : animals) {
            if (animal.getName().equalsIgnoreCase(animalName)) {
                animal.setCommand(newCommands);
                System.out.println("Команды для " + animal.getName() + " были обновлены.");
                return;
            }
        }
        System.out.println("Животное не найдено.");
    }

    private void editAnimal(String animalName) {
        for (Animal animal : animals) {
            if (animal.getName().equalsIgnoreCase(animalName)) {
                try (Scanner scanner = new Scanner(System.in, "Cp866")) {
                    System.out.print("Введите новое имя животного: ");
                    String newName = scanner.nextLine();
                    System.out.print("Введите новые команды для животного: ");
                    String newCommands = scanner.nextLine();
                    animal.setName(newName);
                    animal.setCommand(newCommands);
                    System.out.println("Запись о животном успешно отредактирована.");
                    return;
                }
            }
        }
        System.out.println("Животное не найдено.");
    }

    public String checkBirthdayChar(String birthDate) {
        DateTimeFormatter forma = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date;
        try {
            date = LocalDate.parse(birthDate, forma);
        } catch (Exception e) {
            System.out.println("Ошибка: не правильно набрали формат даты либо цифры " + birthDate);
            Scanner sc = new Scanner(System.in);
            birthDate = checkBirthdayChar(inputRows());

        }

        return birthDate;

    }

    public String inputRows() {
        Scanner sc = new Scanner(System.in,"Cp866");
        String input = sc.nextLine();
        
            if (!input.matches("\\S+")) {
                 System.out.println("Пустые строки вводить нельзя!");

                // throw new Exception("Пустые строки вводить нельзя!");
                input = inputRows();

            }
       

        return input;
    }

    public Integer promsc() {

        Scanner sc = new Scanner(System.in,"Cp866");
        int number;
        do {

            while (!sc.hasNextInt()) {
                System.out.println("Вы набрали что-то не то, смотрите список команд");
                sc.next();
            }
            number = sc.nextInt();
        } while (number > 8);

        return number;

    }

    public void navigation() {

        Scanner scanner = new Scanner(System.in, "Cp866");
        boolean exit = false;

        while (!exit) {
            System.out.println("Меню:");
            System.out.println("1. Добавить новое животное");
            System.out.println("2. Вывести список всех животных ");
            System.out.println("3. Посмотреть команды животного");
            System.out.println("4. Обучить животное новым командам");
            System.out.println("5. Редактировать запись о животном");
            System.out.println("6. Удалить запись о животном");
            System.out.println("7. Выйти");
            System.out.print("Введите ваш выбор: ");

            // String choiceStr = scanner.nextLine();
            int choice = promsc();

            switch (choice) {
                case 1:
                    System.out.print("Введите имя животного: ");
                    String name = inputRows();
                    System.out.print("Введите команды для животного: ");
                    String commands = inputRows();
                    System.out.print("Введите дату рождения животного формата - dd.mm.yyyy: ");
                    String birthDate = scanner.nextLine();
                    birthDate = checkBirthdayChar(birthDate);
                    System.out.print("Выберите вид животного (Pet или PackAnimal): ");
                    String type = scanner.nextLine();

                    try {
                        if ("Pet".equalsIgnoreCase(type)) {
                            System.out.print("Выберите вид животного (Cat, Dog, Hamster): ");
                            String petType = scanner.nextLine();
                            addAnimal(new Pet(name, commands, birthDate, petType));
                        } else if ("PackAnimal".equalsIgnoreCase(type)) {
                            System.out.print("Выберите вид животного (Camel, Donkey, Horse): ");
                            String packAnimalType = scanner.nextLine();
                            addAnimal(new Packanimal(name, commands, birthDate, packAnimalType));
                        } else {
                            System.out.println("Неверный тип животного. Попробуйте снова.");
                        }

                        System.out.println("Животное добавлено в реестр.");
                    } catch (Exception e) {
                        System.out.println("Произошло исключение: " + e.getMessage());
                    }
                    break;
                case 2:
                    viewAnimalList();
                    break;

                case 3:
                    System.out.print("Введите имя животного: ");
                    String animalName = inputRows();
                    infoAnimalCommands(animalName);
                    break;
                case 4:
                    System.out.print("Введите имя животного: ");
                    String animalName2 = inputRows();
                    System.out.print("Введите новые команды для животного: ");
                    String newCommands = inputRows();
                    teachAnimalCommands(animalName2, newCommands);
                    break;
                case 5:
                    System.out.println("Данная функция в стадии разработки");
                    // System.out.print("Введите имя животного для редактирования: ");
                    // String animalNameToEdit = scanner.nextLine();
                    // editAnimal(animalNameToEdit);
                    break;
                case 6:
                    System.out.print("Введите имя животного для удаления: ");
                    String animalNameToDelete = scanner.nextLine();
                    removeAnimal(animalNameToDelete);
                    break;
                case 7:
                    exit = true;
                    System.out.println("Завершение программы.");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        System.out.println("Программа, имитирующая работу реестра домашних животных.");
        System.out.println("ДОБРО ПОЖАЛОВАТЬ В ПИТОМНИК !");
        String filename = "animal.txt";
        Navigation registry = new Navigation(filename);
        registry.navigation();
        registry.saveInfoToFile();

    }

}
