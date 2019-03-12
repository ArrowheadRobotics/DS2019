package org.usfirst.frc706.DS2019.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc706.DS2019.Constants;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import org.usfirst.frc706.DS2019.Robot;
import org.usfirst.frc706.DS2019.OI;

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
		if (Robot.emergencyDisabled)
			return;
		double YPower;
		double XPower;
		double rotation;

		boolean eckertMode = true;

		if (Constants.Chassis.USE_XBOX) {//Get values from xbox controller
			YPower = OI.xbox.getRawAxis(5);
			XPower = OI.xbox.getRawAxis(4);
			rotation = OI.xbox.getRawAxis(0); 
		} else { //Get values from joystick

			XPower = OI.rightJoy.getX();

			if (!eckertMode)
			YPower = OI.leftJoy.getY();
			else
			YPower = -OI.rightJoy.getY();

			if (eckertMode)
				rotation = OI.rightJoy.getZ();
			else
				rotation = OI.rightJoy.getZ();
		}

		if (Constants.Chassis.USE_XBOX) //Use deadband for X and Y only if using xbox controller
		Robot.chassis.mecanumMove(Math.abs(XPower) > Constants.OI.xboxDeadband ? XPower : 0, Math.abs(YPower) > Constants.OI.xboxDeadband ? YPower : 0, rotation); //Send X and Y values to drive if beyond xbox deadband, otherwise send 0 
		else
		Robot.chassis.mecanumMove(XPower,YPower, rotation);
	}
	

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
