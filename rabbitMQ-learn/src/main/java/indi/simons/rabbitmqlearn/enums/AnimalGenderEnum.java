package indi.simons.rabbitmqlearn.enums;

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
