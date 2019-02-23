package org.usfirst.frc706.DS2019.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.usfirst.frc706.DS2019.RobotMap;
import org.usfirst.frc706.DS2019.commands.RunElevator;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {

    private final static TalonSRX drawerTalon = RobotMap.drawerTalon;

    public void initDefaultCommand() {
        setDefaultCommand(new RunElevator());
    }

    public void periodic() {}
	
	public void runDrawerMotor(double percent) {
        drawerTalon.set(ControlMode.PercentOutput,percent);
    }
}