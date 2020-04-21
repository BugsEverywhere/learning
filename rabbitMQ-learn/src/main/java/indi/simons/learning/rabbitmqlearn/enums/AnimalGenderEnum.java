package indi.simons.learning.rabbitmqlearn.enums;

public enum AnimalGenderEnum {

    Male{
        @Override
        public String getGender() {
            return "Male";
        }
    },
    Female {
        @Override
        public String getGender() {
            return "Female";
        }
    };

    public abstract String getGender();
}
