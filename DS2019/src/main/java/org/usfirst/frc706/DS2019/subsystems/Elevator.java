package org.usfirst.frc706.DS2019.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.usfirst.frc706.DS2019.RobotMap;
import org.usfirst.frc706.DS2019.commands.Drive;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {

    private final static TalonSRX elevatorTalon = RobotMap.elevatorTalon;

    public void initDefaultCommand() {
        setDefaultCommand(new Drive());
    }

    public void periodic() {}
	
	public static void runElevator(double percent) {
        elevatorTalon.set(ControlMode.PercentOutput,percent);
    }
}