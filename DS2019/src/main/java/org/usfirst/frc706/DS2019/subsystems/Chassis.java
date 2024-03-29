package org.usfirst.frc706.DS2019.subsystems;

import org.usfirst.frc706.DS2019.Constants;
import org.usfirst.frc706.DS2019.RobotMap;
import org.usfirst.frc706.DS2019.commands.*;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.usfirst.frc706.DS2019.OI;

public class Chassis extends Subsystem {
	public static boolean climbing = false;
	public static boolean piInControl = false;

	private final static TalonSRX leftDriveOne = RobotMap.chassisLeftDriveOne;
	private final static TalonSRX leftDriveTwo = RobotMap.chassisLeftDriveTwo;
	private final static TalonSRX rightDriveOne = RobotMap.chassisRightDriveOne;
	private final static TalonSRX rightDriveTwo = RobotMap.chassisRightDriveTwo;

	public void initDefaultCommand() {
		setDefaultCommand(new Drive());
	}

	public void periodic() {
	}

	public void zeroAllMotors() {
		RobotMap.chassisRightDriveOne.set(ControlMode.PercentOutput, 0);
		RobotMap.chassisLeftDriveOne.set(ControlMode.PercentOutput, 0);
		RobotMap.chassisRightDriveTwo.set(ControlMode.PercentOutput, 0);
		RobotMap.chassisLeftDriveTwo.set(ControlMode.PercentOutput, 0);
		RobotMap.drawerTalon.set(ControlMode.PercentOutput, 0);
		RobotMap.elevatorTalon.set(ControlMode.PercentOutput, 0);
		RobotMap.rampHigh.set(ControlMode.PercentOutput, 0);
		RobotMap.rampLow.set(ControlMode.PercentOutput, 0);
	}

	public void piMove() {
		double xError = NetworkTable.getTable("/SmartDashboard").getDouble("xError",0.0);
		double thetaError = NetworkTable.getTable("/SmartDashboard").getDouble("thetaError", 0.0);
		
		DriverStation.reportError(("XERROR: " + xError), false);
		for (int i = 0; i < 2; i++) {
			piStrafe(thetaError, xError);
			piRotate(thetaError, xError);
		}
		piInControl = false;
	}

	public void piStrafe(double thetaError, double xError) {

		int failedNewFrames = 0;

		while (Math.abs(xError) > Constants.Vision.X_TOLERANCE) {
			if (xError > 0) {
				if (NetworkTable.getTable("/SmartDashboard").getBoolean("newFrame", true)) {
					mecanumMove(Constants.Vision.STRAFE_CONSTANT, -.2, -Constants.Vision.STRAFE_ROT);
				} else {
					failedNewFrames++;
					if (failedNewFrames >= Constants.Vision.newFramesCount) {
						mecanumMove(0, 0, 0);
						piInControl = false;

					}
				}
			} else {
				if (NetworkTable.getTable("/SmartDashboard").getBoolean("newFrame", true)) {
					mecanumMove(-Constants.Vision.STRAFE_CONSTANT, .2, Constants.Vision.STRAFE_ROT);
				} else {
					failedNewFrames++;
					if (failedNewFrames >= Constants.Vision.newFramesCount) {
						mecanumMove(0, 0, 0);
						piInControl = false;
					}
				}
			}
			xError = NetworkTable.getTable("/SmartDashboard").getDouble("xError", 0.0);
			DriverStation.reportError(("XERROR: " + xError), false);
		}
	}

	public void piRotate(double thetaError, double xError) {
		int failedNewFrames = 0;

		while (Math.abs(thetaError) > Constants.Vision.THETA_TOLERANCE) {
			if (thetaError > 0) {
				if (NetworkTable.getTable("/SmartDashboard").getBoolean("newFrame", true)) {
					mecanumMove(-Constants.Vision.ROT_STRAFE, 0, Constants.Vision.THETA_CONSTANT);
				} else {
					failedNewFrames++;
					if (failedNewFrames >= Constants.Vision.newFramesCount) {
						mecanumMove(0, 0, 0);
						piInControl = false;
					}
				}
			} else {
				if (NetworkTable.getTable("/SmartDashboard").getBoolean("newFrame", true)) {
					mecanumMove(Constants.Vision.ROT_STRAFE, 0, -Constants.Vision.THETA_CONSTANT);
				} else {
					failedNewFrames++;
					if (failedNewFrames >= Constants.Vision.newFramesCount) {
						mecanumMove(0, 0, 0);
						piInControl = false;
					}
				}
			}
			xError = NetworkTable.getTable("/SmartDashboard").getDouble("xError", 0.0);
			thetaError = NetworkTable.getTable("/SmartDashboard").getDouble("thetaError", 0.0);
		}

		zeroAllMotors();

	}

	public void mecanumMove(double x, double y, double rotation) {

		boolean triggerPressed = OI.rightJoy.getTrigger();
		if (triggerPressed)
			y = 0;

		// x and y are just x and y of right joystick
		// rotation is z axis of right joystick

		// New and improved mecanum code - courtesy of Joshs and Austin
		double r = Math.hypot(x, y); // radius, acts as a multiplier to percentage (from 0 to 1)

		double theta = Math.atan2(y, x); // Find angle

		double f; // forwardslash
		double b; // backslash

		if (x >= 0) {
			if (y >= 0) {// 1st quadrant
				b = 1; // 100% for this quadrant
				f = -Math.cos(2 * theta); // power changes from -1 to 1 across quadrant
			} else {// 4th
				b = Math.cos(2 * theta);
				f = -1;
			}
		} else {
			if (y >= 0) {// 2nd
				b = -Math.cos(2 * theta);
				f = 1;
			} else {// 3rd
				b = -1;
				f = Math.cos(2 * theta);
			}
		}

		rotation *= Constants.Chassis.ROTATION_MODIFIER;
		double rfd = f * r - rotation;
		double rbd = b * r - rotation;
		double lfd = b * r + rotation;
		double lbd = f * r + rotation;

		/*
		 * if (Constants.Chassis.STREUFDOG && triggerPressed) { rfd = rfd *
		 * Constants.Chassis.STREUFDOG_FR; rbd = rbd * Constants.Chassis.STREUFDOG_BR;
		 * lfd = lfd * Constants.Chassis.STREUFDOG_FL; lbd = lbd *
		 * Constants.Chassis.STREUFDOG_BL;
		 * 
		 * }
		 */
		if (r < Constants.Chassis.SLOW_STRAFE_LIMIT) {
			rfd *= Constants.Chassis.SLOW_FR;
			rbd *= Constants.Chassis.SLOW_BR;
			lfd *= Constants.Chassis.SLOW_FL;
			lbd *= Constants.Chassis.SLOW_BL;
		}

		rightDriveOne.set(ControlMode.PercentOutput,
				rfd * Constants.Chassis.MULTIPLIER * Constants.Chassis.FRONT_RIGHT_MODIFIER);
		rightDriveTwo.set(ControlMode.PercentOutput,
				rbd * Constants.Chassis.MULTIPLIER * Constants.Chassis.BACK_RIGHT_MODIFIER);
		leftDriveOne.set(ControlMode.PercentOutput,
				lfd * Constants.Chassis.MULTIPLIER * Constants.Chassis.FRONT_LEFT_MODIFIER);
		leftDriveTwo.set(ControlMode.PercentOutput,
				lbd * Constants.Chassis.MULTIPLIER * Constants.Chassis.BACK_LEFT_MODIFIER);

		if (Constants.Chassis.LOGGING_ENABLED) {
			SmartDashboard.putNumber("lfd", lfd);
			SmartDashboard.putNumber("rfd", rfd);
			SmartDashboard.putNumber("lbd", lbd);
			SmartDashboard.putNumber("rbd", rbd);
			SmartDashboard.putNumber("rotation", rotation);
		}
	}
}
