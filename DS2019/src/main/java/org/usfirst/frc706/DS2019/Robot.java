package org.usfirst.frc706.DS2019;

import org.usfirst.frc706.DS2019.subsystems.*;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import org.usfirst.frc706.DS2019.RobotMap;
import org.usfirst.frc706.DS2019.misc.ElevatorPosition;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.time.StopWatch;
import com.ctre.phoenix.motorcontrol.*;

public class Robot extends TimedRobot {

	public static boolean emergencyDisabled = false;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	public static OI oi;
	public static Chassis chassis;
	public static Elevator elevator;
	public static Ramp ramp;
	public static Drawer drawer;
	public static boolean auto;
	public static DriverStation driverStation;
	public static TalonSRX _talon;
	public static VisionThread _visionThread;
	public static UsbCamera drivercam;
	public static UsbCamera drivercam2;
	public static UsbCamera visioncam;

	public void robotInit() {
		RobotMap.init();
		chassis = new Chassis();
		elevator = new Elevator();
		ramp = new Ramp();
		drawer = new Drawer();
		oi = new OI();
		elevator.setPosition(ElevatorPosition.LOW);
		driverStation = DriverStation.getInstance();
		auto = driverStation.isAutonomous();

		SmartDashboard.putNumberArray("visionGoals",
				new double[] { Constants.Vision.xInterceptGoal, Constants.Vision.thetaGoal });
		SmartDashboard.putNumber("visionXInterceptGoal", Constants.Vision.xInterceptGoal);

		new Thread(() -> {
			drivercam = CameraServer.getInstance().startAutomaticCapture("Drivercamera", 0);
		}).run();
		new Thread(() -> {
			drivercam2 = CameraServer.getInstance().startAutomaticCapture("Drivercamera2", 1);
		}).run();
		new Thread(() -> {
			visioncam = CameraServer.getInstance().startAutomaticCapture("Visioncamera", 2);
		}).run();
		// camera.setFPS(30);

		_talon = RobotMap.elevatorTalon;
		_talon.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1);
		RobotMap.elevatorTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
	
	if (Constants.Vision.visionEnabled) {
		_visionThread = new VisionThread(this);
		new Thread(_visionThread).start();
	}
	}

	class VisionThread implements Runnable {
		Robot robot;
		boolean bPressed = false;

		public VisionThread(Robot robot) {
			this.robot = robot;
		}

		public void run() {
			/**
			 * Speed up network tables, this is a test project so eat up all of the network
			 * possible for the purpose of this test.
			 */

			double previousXError = 0;
			double previousThetaError = 0;


			while (true) {
				// Yield for a Ms or so - this is not meant to be accurate
				try {
					Thread.sleep(1);
				} catch (Exception e) {
					// Do Nothing
				}

				double xError = NetworkTable.getTable("/SmartDashboard").getDouble("xError", 0.0);
				double thetaError = NetworkTable.getTable("/SmartDashboard").getDouble("thetaError", 0.0);
				if ((Math.abs(xError-previousXError)<Constants.Vision.MAX_X_CHANGE || Math.abs(thetaError-previousThetaError)<Constants.Vision.MAX_THETA_CHANGE) || (Math.abs(thetaError-previousThetaError)!=0) || (Math.abs(xError-previousXError)!=0)) {
					SmartDashboard.putBoolean("VISION_WILL_WORK", true);
				} 
				else {
					SmartDashboard.putBoolean("VISION_WILL_WORK", false);
				}
				previousXError = xError;
				previousThetaError = thetaError;

				if (!bPressed && OI.rightJoy.getRawButton(Constants.OI.VISION_TRIGGER)) {
					// Robot.emergencyDisabled = !Robot.emergencyDisabled;

					StopWatch time = new StopWatch();
					time.start();
					Chassis.piInControl = true;

					chassis.zeroAllMotors();

					Thread piThread = new Thread(() -> {
						Robot.chassis.piMove();
					});
					piThread.start();
					while (time.getDurationMs() < 5000 && robot.chassis.piInControl) {
					}
					piThread.stop();

					
					bPressed = true;
				} else if (bPressed && !OI.rightJoy.getRawButton(Constants.OI.VISION_TRIGGER))
					bPressed = false;
				Chassis.piInControl = false;
				if (Robot.emergencyDisabled) {
					RobotMap.chassisRightDriveOne.set(ControlMode.PercentOutput, 0);
					RobotMap.chassisLeftDriveOne.set(ControlMode.PercentOutput, 0);
					RobotMap.chassisRightDriveTwo.set(ControlMode.PercentOutput, 0);
					RobotMap.chassisLeftDriveTwo.set(ControlMode.PercentOutput, 0);
					RobotMap.drawerTalon.set(ControlMode.PercentOutput, 0);
					RobotMap.elevatorTalon.set(ControlMode.PercentOutput, 0);
					RobotMap.rampHigh.set(ControlMode.PercentOutput, 0);
					RobotMap.rampLow.set(ControlMode.PercentOutput, 0);
				}

				// Grab the latest signal update from our 1ms frame update
				double velocity = _talon.getSelectedSensorVelocity(0);
				SmartDashboard.putNumber("vel", velocity);
				double position = _talon.getSensorCollection().getQuadraturePosition();
				SmartDashboard.putNumber("pos", position);
				SmartDashboard.putString("enumPos", elevator.position.toString());
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
