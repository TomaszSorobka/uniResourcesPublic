class Animal {
    private int type;
    private String name;

    Animal(int type, String name) {
        this.type = type;
        this.name = name;
    }

    boolean isHerbivore() {
        int animalType = getAnimalType();
        if (animalType >= 4 && animalType <= 6) {
            return true;
        }
        return false;
    }

    boolean isCarnivore() {
        int animalType = getAnimalType();
        if (animalType >= 1 && animalType <= 3) {
            return true;
        }
        return false;
    }

    boolean isOmnivore() {
        if (getAnimalName() == 7) {
            return true;
        }
        return false;
    }

    boolean isAntelope() {
        if (getAnimalName() == 5) {
            return true;
        }
        return false;
    }

    boolean isSolitary() {
        int animalType = getAnimalType();
        if (animalType >= 2 && animalType <= 3) {
            return true;
        }
        return false;
    }

    boolean rightEnclosure(Home home) {
        int homeType = home.getType();
        int animalType = this.getAnimalType();
        if (homeType == 1) {
            if (animalType >= 4 && animalType <= 6) {
                return false;
            }
        }
        return true;
    }

    boolean canBeKeptWith(Animal animal, boolean[][] zooHomeRules) {
        int animalType1 = this.getAnimalType();
        int animalType2 = animal.getAnimalType();

        return zooHomeRules[animalType1 - 1][animalType2 - 1];
    }

    int getAnimalType() {
        return this.type;
    }

    String getAnimalName() {
        return this.name;
    }

}