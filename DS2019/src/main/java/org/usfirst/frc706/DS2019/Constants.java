package org.usfirst.frc706.DS2019;

public final class Constants {

	public static final int PID_TIMEOUT = 30;

	public class Chassis {
		//ALL CAN IDs // rohan was not here
		//DO NOT CHANGE THESE IDS UNDER ANY CIRCUMSTANCES UNLESS
		//YOU WISH TO SUFFER ETERNAL HELLFIRE
		public static final int LEFT_FRONT = 4;
		public static final int LEFT_BACK = 8;
		public static final int RIGHT_FRONT = 3;
		public static final int RIGHT_BACK = 2;
		public static final int ELEVATOR = 9;
		public static final int LOWER_WINDOW = 7;
		public static final int UPPER_WINDOW = 10;
		public static final int DRAWER = 5;

		// Speeds & Rates
		public static final double MULTIPLIER = .8;
		public static final double FRONT_RIGHT_MODIFIER = .95;
		public static final double FRONT_LEFT_MODIFIER = 1;
		public static final double BACK_RIGHT_MODIFIER = .8;
		public static final double BACK_LEFT_MODIFIER = .95;

		//Streufdog
		//Adjusts strafe so it is even on practicebot
		public static final boolean STREUFDOG = false;
		public static final double STREUFDOG_FR = 0.85/Chassis.MULTIPLIER;
		public static final double STREUFDOG_FL = .8/Chassis.MULTIPLIER;
		public static final double STREUFDOG_BR = .75;
		public static final double STREUFDOG_BL = .75;

		//Slow strafe constants
		//Adjust drive while at low speeds
		public static final double SLOW_FR = 1;
		public static final double SLOW_FL = 1;
		public static final double SLOW_BR = 1;
		public static final double SLOW_BL = 1;
		public static final double SLOW_STRAFE_LIMIT = .5;

		// Driving
		public static final boolean USE_XBOX = false;
		public static final boolean LOGGING_ENABLED = false;

		// Mecanum Constants
		public static final double ROTATION_MODIFIER = 0.6;
	}

	public class Drawer {
		public static final double DRAWER_MODIFIER = 0.6;
	}

	public class Elevator {

		// Encoder counts
		public static final int ELEVATOR_LOW = -1000;
		public static final int ELEVATOR_MID = -36000;
		public static final int ELEVATOR_HIGH = -63600;

		// PID
		public static final int ELEVATOR_HOLD_SLOT = 0;
		public static final int ELEVATOR_MOVE_SLOT = 1;
		public static final double ELEVATOR_MAX_SPEED = 0.2;

		public static final double ELEVATOR_HOLD_KP = 1;//.1
		public static final double ELEVATOR_HOLD_KD = 0;//.5
		public static final double ELEVATOR_HOLD_KI = 0;//.00025
	}

	public class Ramp {
		// CAN IDs
		public static final int RAMP_HIGH = 0;
		public static final int RAMP_LOW = 0;
	}

	public class OI {
		// Joystick Ports
		public static final int LEFT_JOY = 0;
		public static final int RIGHT_JOY = 1;
		public static final int XBOX = 2;

		// Joystick Buttons
		public static final int TRIGGER = 1;
		public static final int CLIMB = 2;

		// Xbox Buttons
		public static final int A = 1;
		public static final int B = 2;
		public static final int X = 3;
		public static final int Y = 4;
		public static final int LB = 5;
		public static final int RB = 6;
		public static final int BACK = 7;
		public static final int START = 8;
		public static final int MANIP = 9;
		public static final int VISION_TRIGGER = 2;

		//Xbox deadband
		public static final double xboxDeadband = 0.2;
	}

	public class Vision {


		public static final boolean visionEnabled = false;

		public static final double xInterceptGoal = 85;
		public static final double thetaGoal = 90;

		public static final double X_TOLERANCE = 15;
		public static final double THETA_TOLERANCE = 15;

		public static final double STRAFE_CONSTANT = -0.4;
		public static final double THETA_CONSTANT = -0.55;
		public static final double STRAFE_ROT = .18;

		public static final double ROT_STRAFE = .3;

		public static final double MAX_X_CHANGE = 2;
		public static final double MAX_THETA_CHANGE = 2;

		public static final int newFramesCount = 1000;
	}
}