class Home {
    private int type;
    private Animal[] animals;
    private int size;
    private int currentPopulation;

    Home(int type) {
        this.type = type;
        if (type == 1) {
            this.size = 2;
        } else {
            this.size = 6;
        }
        animals = new Animal[size];
        currentPopulation = 0;
    }

    boolean isAddConflict(Animal animal, boolean[][] zooHomeRules) {

        if (currentPopulation >= size || !animal.rightEnclosure(this)) {
            return true;
        }

        for (int i = 0; i < currentPopulation; i++) {
            if (!animal.canBeKeptWith(animals[i], zooHomeRules)) {
                return true;
            }
        }

        return false;
    }

    boolean addAnimal(Animal animal, boolean[][] zooHomeRules) {
        if (!this.isAddConflict(animal, zooHomeRules)) {
            animals[currentPopulation] = animal;
            currentPopulation++;
            return true;
        }
        return false;
    }

    boolean feedHome(Food food, int amount, boolean[][] zooFoodRules) {
        if (isFeedingConflict(food, zooFoodRules)) {
            return false;
        }
        food.feedFood(amount);
        return true;
    }

    boolean isFeedingConflict(Food food, boolean[][] zooFoodRules) {
        for (int i = 0; i < currentPopulation; i++) {
            if (!food.canBeFedTo(animals[i], zooFoodRules)) {
                return true;
            }
        }
        return false;
    }

    int getType() {
        return this.type;
    }

    int getPopulation() {
        return this.currentPopulation;
    }

    int giveAnimalIndexAtHome(String name) {
        for (int i = 0; i < currentPopulation; i++) {
            if (animals[i].getAnimalName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    Animal getAnimal(int indexAtHome) {
        return animals[indexAtHome];
    }

    void removeAnimal(int indexAtHome) {
        currentPopulation--;
        for (int i = indexAtHome; i < size - 1; i++) {
            animals[i] = animals[i+1];
        }
    }





}