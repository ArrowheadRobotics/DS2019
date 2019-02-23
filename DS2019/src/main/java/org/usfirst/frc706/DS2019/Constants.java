package org.usfirst.frc706.DS2019;

import java.sql.Struct;

public final class Constants {

	public class Chassis {
		// CAN IDs
		public static final int LEFT_FRONT = 4;
		public static final int LEFT_BACK = 6;
		public static final int RIGHT_FRONT = 3;
		public static final int RIGHT_BACK = 2;

		// Speeds & Rates
		public static final double MULTIPLIER = .5;
		public static final double THROTTLE = .3;

		// Driving
		public static final boolean USE_XBOX = false;
		public static final boolean LOGGING_ENABLED = false;

		// Mecanum Constants
		public static final double ROTATION_MODIFIER = 0.8;
	}

	public class Elevator {
		// CAN IDs
		public static final int ELEVATOR = 5;

		// Encoder counts
		public static final int ELEVATOR_LOW = 0;
		public static final int ELEVATOR_MID = 0;
		public static final int ELEVATOR_HIGH = 0;

		// PID
		public static final int ELEVATOR_HOLD_SLOT = 0;
		public static final int ELEVATOR_MOVE_SLOT = 1;
		public static final double ELEVATOR_MAX_SPEED = 0.2;

		public static final double ELEVATOR_HOLD_KP = 0;
		public static final double ELEVATOR_HOLD_KD = 0;
		public static final double ELEVATOR_HOLD_KI = 0;

		public static final double ELEVATOR_MOVE_KP = 0;
		public static final double ELEVATOR_MOVE_KD = 0;
		public static final double ELEVATOR_MOVE_KI = 0;
	}

	public class Intake {
		// CAN IDs
		public static final int DRAWER = 0;
		public static final int COMPRESSOR = 0;

		// Encoder Counts
		public static final int DRAWER_OUT = 0;
		public static final int DRAWER_IN = 0;

		// PID
		public static final int DRAWER_SLOT = 0;
		public static final double DRAWER_MAX_SPEED = 0;
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

		// fixes values for XBox controoller
		//
		// The controller has issues with any human that tries
		// to work with it so this value is a range that limits
		// the acceptable absolute value of the controller so that
		// the controller functions normally
		public static final double uncivilizedXBoxController = 0.12;

	}
}