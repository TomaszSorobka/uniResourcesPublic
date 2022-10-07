class Food {
    private int type;
    private int amount;

    Food(int type) {
        this.type = type;
        this.amount = 0;
    }

    int getAmount() {
        return this.amount;
    }

    boolean canBeFedTo(Animal animal, boolean[][] zooFoodRules) {
        int animalType = animal.getAnimalType();
        return zooFoodRules[animalType - 1][this.type - 1];
    }

    void buyFood(int amount) {
        this.amount += amount;
    }

    void feedFood(int amount) {
        this.amount -= amount;
    }

}