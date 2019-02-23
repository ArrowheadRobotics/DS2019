package org.usfirst.frc706.DS2019.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.usfirst.frc706.DS2019.Constants;
import org.usfirst.frc706.DS2019.Robot;
import org.usfirst.frc706.DS2019.RobotMap;
import org.usfirst.frc706.DS2019.Constants.OI;
import org.usfirst.frc706.DS2019.commands.RunElevator;
import org.usfirst.frc706.DS2019.misc.ElevatorPosition;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {

    private final static TalonSRX elevatorTalon = RobotMap.elevatorTalon;

    public void initDefaultCommand() {
        setDefaultCommand(new RunElevator());
    }

    public void periodic() {
    }

    public void runElevator(double percent) {
        elevatorTalon.set(ControlMode.PercentOutput, percent);
    }

    public void setPosition(ElevatorPosition position) {
        switch (position) {
        case LOW:
            elevatorTalon.set(ControlMode.Position, Constants.Elevator.ELEVATOR_LOW);
            break;
        case MIDDLE:
            elevatorTalon.set(ControlMode.Position, Constants.Elevator.ELEVATOR_MID);
            break;
        case HIGH:
            elevatorTalon.set(ControlMode.Position, Constants.Elevator.ELEVATOR_HIGH);
            break;
        default:
            break;
        }
    }
}