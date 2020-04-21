package indi.simons.learning.rabbitmqlearn.enums;

public enum AnimalColorEnum {

    Red{
        @Override
        public String getColor() {
            return "Red";
        }
    },
    White {
        @Override
        public String getColor() {
            return "White";
        }
    },
    Black {
        @Override
        public String getColor() {
            return "Black";
        }
    };


    public abstract String getColor();
}
