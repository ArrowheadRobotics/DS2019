package org.usfirst.frc706.DS2019.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc706.DS2019.Constants;
import org.usfirst.frc706.DS2019.OI;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import org.usfirst.frc706.DS2019.Robot;

import org.usfirst.frc706.DS2019.subsystems.Chassis;
import org.usfirst.frc706.DS2019.subsystems.Elevator;

public class Drive extends Command {
	DatagramPacket dataPacket;
	DatagramSocket dataSocket;

	int stepsToTake;

	public Drive() {
		requires(Robot.chassis);
	}

	protected void initialize() {
	}

	protected void execute() {
		// if (!Robot.auto) {
		// if (Robot.oi.sonicOutput.getAverageValue() < 320)
		// {DriverStation.reportError("DISTANCE IS REALLY CLOSE",false);}
		// else if (Robot.oi.sonicOutput.getAverageValue() < 380)
		// {DriverStation.reportError("DISTANCE IS CLOSE",false);}
		// else {DriverStation.reportError("DISTANCE IS FAR",false);}
		// if (Robot.oi.sonicOutput.getValue() > 900 ||
		// (Robot.oi.rightJoy.getY()>0&&Robot.oi.leftJoy.getY()>0)) {
		// DriverStation.reportError("" + Robot.oi.sonicOutput.getValue(),false)

		double YPower;
		double XPower;
		double rotation;

		boolean eckertMode = true;

		if (Constants.Chassis.useXbox) {// pull values from XBox remote
			YPower = Robot.oi.xbox.getRawAxis(5);
			XPower = Robot.oi.xbox.getRawAxis(4);
			rotation = Robot.oi.xbox.getRawAxis(0);
			// if (Math.abs(0-XPower)<=Constants.OI.uncivilizedXBoxController) XPower = 0;
			// if (Math.abs(0-YPower)<=Constants.OI.uncivilizedXBoxController) YPower = 0;
			// //Fixes xbox controller values sticking
		} else {// Pull values from yellow joysticks
			YPower = -Robot.oi.rightJoy.getY();
			XPower = Robot.oi.rightJoy.getX();
			if (eckertMode)
				rotation = Robot.oi.rightJoy.getZ();
			else
				rotation = Robot.oi.leftJoy.getZ();
		}
		Robot.chassis.mecanumMove(Math.abs(XPower) > 0.2 ? XPower : 0, Math.abs(YPower) > 0.2 ? YPower : 0, rotation);
		/*
		 * } else { if (Robot.oi.sonicOutput.getValue() > 600) {
		 * Chassis.move(-Robot.oi.rightJoy.getY(),-Robot.oi.leftJoy.getY()); } else {
		 * Chassis.move(0, 0) } }
		 */
		// DriverStation.reportError("SUPERSONIC VOLTAGE: " +
		// Robot.oi.sonicOutput.getAverageValue(), false);
		// }
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
