package org.usfirst.frc706.DS2019.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.usfirst.frc706.DS2019.RobotMap;
import org.usfirst.frc706.DS2019.commands.*;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Ramp extends Subsystem {

    private final static TalonSRX rampHigh = RobotMap.rampHigh;
    private final static TalonSRX rampLow = RobotMap.rampLow;

    public void initDefaultCommand() {
        setDefaultCommand(new DeployRamp());
    }

    public void periodic() {}
	
	public void runRamp(double percent) {
        rampHigh.set(ControlMode.PercentOutput,percent);
        rampLow.set(ControlMode.PercentOutput,percent);
    }
}