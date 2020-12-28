package indi.simon.learning.rabbitmqlearn.enums;

public enum AnimalSpeciesEnum {

    Monkey{
        @Override
        public String getSpecies() {
            return "Monkey";
        }
    },
    Pig{
        @Override
        public String getSpecies() {
            return "Pig";
        }
    },
    Cat{
        @Override
        public String getSpecies() {
            return "Cat";
        }
    };


    public abstract String getSpecies();
}
