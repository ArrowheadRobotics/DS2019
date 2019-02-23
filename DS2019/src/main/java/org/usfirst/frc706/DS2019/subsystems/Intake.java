package org.usfirst.frc706.DS2019.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Compressor;

import org.usfirst.frc706.DS2019.Constants;
import org.usfirst.frc706.DS2019.RobotMap;
import org.usfirst.frc706.DS2019.commands.RunElevator;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {

    private final static TalonSRX drawerTalon = RobotMap.drawerTalon;

    private final static Compressor comp = RobotMap.comp;

    public void initDefaultCommand() {
        setDefaultCommand(new RunElevator());
    }

    public void periodic() {}
	
	public void runDrawerMotor(double percent) {
        drawerTalon.set(ControlMode.PercentOutput,percent);
    }

    public void setDrawerPosition(boolean drawerOut) {
        if (drawerOut) {
            drawerTalon.set(ControlMode.Position, Constants.Intake.DRAWER_OUT);
        } else {
            drawerTalon.set(ControlMode.Position, Constants.Intake.DRAWER_IN);
        }
    }
}