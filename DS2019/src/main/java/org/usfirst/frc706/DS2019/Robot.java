package org.usfirst.frc706.DS2019;


import org.usfirst.frc706.DS2019.subsystems.Chassis;
import org.usfirst.frc706.DS2019.subsystems.Elevator;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import org.usfirst.frc706.DS2019.RobotMap;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.*;

public class Robot extends TimedRobot {

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	public static OI oi;
	public static Chassis chassis;
	public static Elevator elevator;
	public static boolean auto;
	public static DriverStation driverStation;
	public static SmartDashboard dashboard;
	public static TalonSRX _talon;
	public static PlotThread _plotThread;
	public static UsbCamera camera;

	public void robotInit() {
		RobotMap.init();
		chassis = new Chassis();
		elevator = new Elevator();
		oi = new OI();
		driverStation = DriverStation.getInstance();
		auto = driverStation.isAutonomous();
		camera = CameraServer.getInstance().startAutomaticCapture("Drivercamera",0);
		camera.setFPS(30);
/* 		_talon = RobotMap.chassisrightDriveOne;
		_talon.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1);
		RobotMap.chassisrightDriveOne.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
				_plotThread = new PlotThread(this);
		new Thread(_plotThread).start(); */
	}

		class PlotThread implements Runnable {
		Robot robot;

		public PlotThread(Robot robot) {
			this.robot = robot;
		}

		public void run() {
			/**
			 * Speed up network tables, this is a test project so eat up all of
			 * the network possible for the purpose of this test.
			 */

			while (true) {
				/* Yield for a Ms or so - this is not meant to be accurate */
				try {
					Thread.sleep(1);
				} catch (Exception e) {
					/* Do Nothing */
				}

				/* Grab the latest signal update from our 1ms frame update */
				double velocity = _talon.getSelectedSensorVelocity(0);
				SmartDashboard.putNumber("vel", velocity);
			}
		}
	}

	public void disabledInit() {

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();
		if (autonomousCommand != null)
			autonomousCommand.start();
		auto = true;
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		auto = false;
	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
}
