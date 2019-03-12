package org.usfirst.frc706.DS2019.misc;

public enum ElevatorPosition {
    LOW {
        @Override
        public ElevatorPosition decrease() {
            //Robot.elevator.modifierE = -800;
            return values()[ordinal()];
        };
    }, 
    MIDDLE, 
    HIGH {
        @Override
        public ElevatorPosition increase() {
            //Robot.elevator.modifierE = -800;
            return values()[ordinal()];
        };
    };

    public ElevatorPosition increase() {
        //Robot.elevator.modifierE = -800;
        return values()[ordinal() + 1];
    }

    public ElevatorPosition decrease() {
        //Robot.elevator.modifierE = -800;
        return values()[ordinal() - 1];
    }
}