import java.util.HashSet;

class MyZoo {

    Home[] homes;
    Food[] foodStorage;
    public HashSet<String> animalNames;
    int MAX_AMOUNT_OF_ANIMALS = 50;
    boolean[][] zooHomeRules;
    boolean[][] zooFoodRules;

    MyZoo() {

        homes = new Home[15];
        for (int i = 0; i < 10; i++) {
            homes[i] = new Home(1);
        }

        for (int i = 10; i < 15; i++) {
            homes[i] = new Home(2);
        }

        foodStorage = new Food[7];
        for (int i = 0; i < 6; i++) {
            foodStorage[i] = new Food(i + 1);
        }

        animalNames = new HashSet<String>(MAX_AMOUNT_OF_ANIMALS);
        zooHomeRules = new boolean[][] {
            { true, true, true, false, false, false, false },
            { true, false, true, false, false, false, false },
            { true, true, false, false, false, false, false },
            { false, false, false, true, true, true, true },
            { false, false, false, true, true, true, true },
            { false, false, false, true, true, true, true },
            { false, false, false, true, true, true, true },
        };

        zooFoodRules = new boolean[][] {
            { false, false, false, false, true, true },
            { false, false, false, false, true, true },
            { false, false, false, false, true, true },
            { true, true, true, true, false, false },
            { true, true, true, false, false, false },
            { true, true, true, true, false, false },
            { false, false, false, true, true, true },
        };
    }
    
    String addAnimal(int animalType, String name, int homeId) {

        if (isNameTaken(name)) {
            return "0!";
        } else {
            addName(name);
        }

        Animal animal = new Animal(animalType, name);
        if (homes[homeId].addAnimal(animal, this.zooHomeRules)) {
            return "0";
        } 
        return "0!";
    }

    String moveAnimal(String name, int newHomeId) {

        if (!isNameTaken(name)) {
            return "1!";
        }

        int[] animalLocation = findAnimalHomeId(name);
        int currentHomeId = animalLocation[0];
        int currentAnimalIndex = animalLocation[1];
        Animal animalToMove = homes[currentHomeId].getAnimal(currentAnimalIndex);

        if (homes[newHomeId].isAddConflict(animalToMove, zooHomeRules)) {
            return "1!";
        }

        removeAnimalFromHome(currentHomeId, currentAnimalIndex);
        homes[newHomeId].addAnimal(animalToMove, zooHomeRules);

        return "1";
    }

    

    int[] findAnimalHomeId(String name) {

        for(int homeId = 0; homeId < 15; homeId++) {
            int indexAtHome = homes[homeId].giveAnimalIndexAtHome(name);
            if (indexAtHome != -1) {
                return new int[] {homeId, indexAtHome};
            }
        }

        return new int[] {-1, -1}; // add somewhere if null then cannot execute
    }

    String removeAnimal(String name) {
        if (!isNameTaken(name)) {
            return "2!";
        }

        int[] animalLocation = findAnimalHomeId(name);
        removeAnimalFromHome(animalLocation[0], animalLocation[1]);
        removeName(name);
        return "2";
    }

    void removeAnimalFromHome(int homeId, int indexAtHome) {
        homes[homeId].removeAnimal(indexAtHome);
    }

    String buyFood(int foodType, int amount) {
        if (foodStorage[foodType - 1].getAmount() + amount > 100) {
            return "3!";
        }

        foodStorage[foodType - 1].buyFood(amount);
        return "3";
    }

    String feedFood(int foodType, int amount, int homeId) {
        if (foodStorage[foodType - 1].getAmount() < amount || !homes[homeId].feedHome(foodStorage[foodType - 1], amount, zooFoodRules)) {
            return "4!";
        }
        
        return "4";
    }

    boolean isNameTaken(String name) {
        if (animalNames.contains(name)) {
            return true;
        } 

        return false;
    }

    void addName(String name) {
         animalNames.add(name);
    }

    void removeName(String name) {
        animalNames.remove(name);
    }

}