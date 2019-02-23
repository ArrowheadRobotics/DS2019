package org.usfirst.frc706.DS2019.misc;

public enum ElevatorPosition {
    LOW {
        @Override
        public ElevatorPosition decrease() {
            return values()[ordinal()];
        };
    }, 
    MIDDLE, 
    HIGH {
        @Override
        public ElevatorPosition increase() {
            return values()[ordinal()];
        };
    };

    public ElevatorPosition increase() {
        return values()[ordinal() + 1];
    }

    public ElevatorPosition decrease() {
        return values()[ordinal() - 1];
    }
}