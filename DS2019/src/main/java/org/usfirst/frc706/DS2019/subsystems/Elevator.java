package org.usfirst.frc706.DS2019.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.usfirst.frc706.DS2019.Constants;
import org.usfirst.frc706.DS2019.RobotMap;
import org.usfirst.frc706.DS2019.commands.RunElevator;
//import org.usfirst.frc706.DS2019.commands.RunElevator;
//import org.usfirst.frc706.DS2019.misc.ElevatorPosition;
import org.usfirst.frc706.DS2019.misc.ElevatorPosition;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator extends Subsystem {

    private final static TalonSRX elevatorTalon = RobotMap.elevatorTalon;

    public ElevatorPosition position = ElevatorPosition.LOW;

    public static int modifierE = -800;
    public static int grabbing = -4000;
    public static boolean grabbingYesOrNo = false;

    boolean shouldStop = false;

    public void initDefaultCommand() {
        setDefaultCommand(new RunElevator());
    }

    public void periodic() {
    }

    public void runElevator(double percent) {
        elevatorTalon.set(ControlMode.PercentOutput, percent * 0.8);
    }

    public void setPosition(ElevatorPosition position) {
        SmartDashboard.putNumber("Speed", elevatorTalon.getSensorCollection().getPulseWidthVelocity());
        SmartDashboard.putNumber("Error", elevatorTalon.getClosedLoopError());
        SmartDashboard.putNumber("modifierE", Elevator.modifierE);
        switch (position) {
        case LOW:
            if(grabbingYesOrNo){
                elevatorTalon.set(ControlMode.Position, Constants.Elevator.ELEVATOR_LOW + modifierE + grabbing);
            }else{
                elevatorTalon.set(ControlMode.Position, Constants.Elevator.ELEVATOR_LOW + modifierE);
            }
            break;
        case MIDDLE:
            if(grabbingYesOrNo){
                elevatorTalon.set(ControlMode.Position, Constants.Elevator.ELEVATOR_MID + modifierE + grabbing);
            }else{
                elevatorTalon.set(ControlMode.Position, Constants.Elevator.ELEVATOR_MID + modifierE);
            }
            break;
        case HIGH:
            if(grabbingYesOrNo){
                elevatorTalon.set(ControlMode.Position, Constants.Elevator.ELEVATOR_HIGH + modifierE + grabbing);
            }else{
                elevatorTalon.set(ControlMode.Position, Constants.Elevator.ELEVATOR_HIGH + modifierE);
            }
            break;
        default:
            break;
        }
    }

}