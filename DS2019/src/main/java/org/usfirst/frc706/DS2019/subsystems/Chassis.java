package org.usfirst.frc706.DS2019.subsystems;

import org.usfirst.frc706.DS2019.Constants;
import org.usfirst.frc706.DS2019.Robot;
import org.usfirst.frc706.DS2019.RobotMap;
import org.usfirst.frc706.DS2019.commands.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc706.DS2019.subsystems.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Chassis extends Subsystem {
	public static boolean climbing = false;

	private final static TalonSRX leftDriveOne = RobotMap.chassisLeftDriveOne;
	private final static TalonSRX leftDriveTwo = RobotMap.chassisLeftDriveTwo;
	private final static TalonSRX rightDriveOne = RobotMap.chassisRightDriveOne;
	private final static TalonSRX rightDriveTwo = RobotMap.chassisRightDriveTwo;

	public void initDefaultCommand() {
		setDefaultCommand(new Drive());
	}

	public void periodic() {
	}

	public static void move(double left, double right) {
		// System.out.println("PTO: " + RobotMap.ptoOne.get());
		if (Chassis.climbing) {
			System.out.println("CLIMBING");
			leftDriveOne.set(ControlMode.PercentOutput, -0.75 * left);
			leftDriveTwo.set(ControlMode.PercentOutput, -0.75 * left);
			// leftDriveThree.set(ControlMode.PercentOutput, -0.75*left);
			rightDriveOne.set(ControlMode.PercentOutput, 0.75 * right);
			rightDriveTwo.set(ControlMode.PercentOutput, 0.75 * right);
			// rightDriveThree.set(ControlMode.PercentOutput, 0.75*right);
		} else {
			rightDriveOne.set(ControlMode.PercentOutput, right * Constants.Chassis.MULTIPLIER);
			rightDriveTwo.set(ControlMode.PercentOutput, right * Constants.Chassis.MULTIPLIER);
			// rightDriveThree.set(ControlMode.PercentOutput,
			// right*Constants.Chassis.MULTIPLIER);
			leftDriveOne.set(ControlMode.PercentOutput, -1 * left * Constants.Chassis.MULTIPLIER);
			leftDriveTwo.set(ControlMode.PercentOutput, -1 * left * Constants.Chassis.MULTIPLIER);
			// leftDriveThree.set(ControlMode.PercentOutput,
			// -1*left*Constants.Chassis.MULTIPLIER);
		}

	}

	public void mecanumMove(double x, double y, double rotation) {

		// xspeed and yspeed are just x and y of right joystick
		// rotation is x of left joystick
		// controllerType is true if using XBOX controller

		// New and improved mecanum code - courtesy of Joshs and Austin
		double r = Math.hypot(x, y);// radius, acts as a multiplier to percentage (from 0 to 1)

		// Find angle
		double theta;
		/*
		 * if (x==0){ if (y>=0) theta = Math.PI/2; else if (y<0) theta = 3*Math.PI/2; }
		 * else if (y==0){ if (x>=0) theta = 0; else (x<0) theta = Math.PI; //don't
		 * reinvent the wheel }else
		 */
		theta = Math.atan2(y, x);

		double f; // forwardslash
		double b; // backslash

		if (x >= 0) {
			if (y >= 0) {// 1st quadrant
				b = r;
				f = -Math.cos(2 * theta) * r;
			} else {// 4th
				b = Math.cos(2 * theta) * r;
				f = -r;
			}
		} else {
			if (y >= 0) {// 2nd
				b = -Math.cos(2 * theta) * r;
				f = r;
			} else {// 3rd
				b = -r;
				f = Math.cos(2 * theta) * r;
			}
		}

		rotation *= Constants.Chassis.ROTATION_MODIFIER;
		double rfd = f - rotation;
		double rbd = b - rotation;
		double lfd = b + rotation;
		double lbd = f + rotation;

		rightDriveOne.set(ControlMode.PercentOutput, rbd * Constants.Chassis.MULTIPLIER);
		rightDriveTwo.set(ControlMode.PercentOutput, rfd * Constants.Chassis.MULTIPLIER);
		leftDriveOne.set(ControlMode.PercentOutput, lfd * Constants.Chassis.MULTIPLIER);
		leftDriveTwo.set(ControlMode.PercentOutput, lbd * Constants.Chassis.MULTIPLIER);

		if (Constants.Chassis.LOGGING_ENABLED) {
			SmartDashboard.putNumber("lfd", lfd);
			SmartDashboard.putNumber("rfd", rfd);
			SmartDashboard.putNumber("lbd", lbd);
			SmartDashboard.putNumber("rbd", rbd);
			SmartDashboard.putNumber("rotation", rotation);
		}

		// leftDriveOne.set(ControlMode.PercentOutput,0.5);
		/*
		 * Aidaan double radius = Math.hypot(xSpeed, ySpeed);//drive Speed accounting
		 * for x and y
		 * 
		 * if(radius > 1) radius = 1; double driveAngle = -1*Math.atan(ySpeed /
		 * xSpeed);//drive angle in radians
		 * 
		 * if (xSpeed <= 0) driveAngle += Math.PI; if (driveAngle <= 0)driveAngle +=
		 * 2*Math.PI;
		 * 
		 * Robot.dashboard.putNumber("Radius", radius);
		 * Robot.dashboard.putNumber("driveAngle", driveAngle);
		 * Robot.dashboard.putNumber("xSpeed", xSpeed);
		 * Robot.dashboard.putNumber("leftright", (xSpeed >= 0)?1.0:0.0);
		 * 
		 * double lfd = 0; double rfd = 0; double lbd = 0; double rbd = 0;
		 * 
		 * if(controllerType){//XBox
		 * 
		 * if(Math.abs(rotation) < Constants.OI.uncivilizedXBoxController){ rotation =
		 * 0; //Fix XBox Problems }
		 * 
		 * if(radius > Math.hypot(Constants.OI.uncivilizedXBoxController,
		 * Constants.OI.uncivilizedXBoxController)){ lfd = radius * Math.sin(driveAngle
		 * + (Math.PI/4)) + rotation; rfd = radius * -Math.cos(driveAngle + (Math.PI/4))
		 * - rotation; lbd = radius * -Math.cos(driveAngle + (Math.PI/4)) + rotation;
		 * rbd = radius * Math.sin(driveAngle + (Math.PI/4)) - rotation;
		 * 
		 * }else if(rotation > Math.abs(Constants.OI.uncivilizedXBoxController)){ lfd =
		 * rotation; rfd = -rotation; lbd = rotation; rbd = -rotation; }else{ lfd = 0;
		 * rfd = 0; lbd = 0; rbd = 0; } }else{ lfd = radius * Math.sin(driveAngle +
		 * (Math.PI/4)) + rotation; rfd = radius * -Math.cos(driveAngle + (Math.PI/4)) -
		 * rotation; lbd = radius * -Math.cos(driveAngle + (Math.PI/4)) + rotation; rbd
		 * = radius * Math.sin(driveAngle + (Math.PI/4)) - rotation; }
		 * 
		 * 
		 * Robot.dashboard.putNumber("lfd", lfd); Robot.dashboard.putNumber("rfd", rfd);
		 * Robot.dashboard.putNumber("lbd", lbd); Robot.dashboard.putNumber("rbd", rbd);
		 * Robot.dashboard.putNumber("rotation", rotation);
		 * 
		 * rightDriveOne.set(ControlMode.PercentOutput,
		 * rfd*Constants.Chassis.MULTIPLIER);
		 * rightDriveTwo.set(ControlMode.PercentOutput,
		 * rbd*Constants.Chassis.MULTIPLIER);
		 * leftDriveOne.set(ControlMode.PercentOutput,
		 * -lfd*Constants.Chassis.MULTIPLIER);
		 * leftDriveTwo.set(ControlMode.PercentOutput,
		 * -lbd*Constants.Chassis.MULTIPLIER);
		 */
	}
}
