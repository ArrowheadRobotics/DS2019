package org.usfirst.frc706.DS2019;

import java.sql.Struct;

public final class Constants {
	
	public class Chassis {
		//SRX CAN IDs
	
		public static final int LEFT_FRONT = 4;
		public static final int LEFT_BACK = 6;
		public static final int RIGHT_FRONT = 3;
		public static final int RIGHT_BACK = 2;
		public static final int ELEVATOR = 5;
				
		//Speeds & Rates
		public static final double MULTIPLIER = .5;
		public static final double THROTTLE = .3;

		//Driving
		public static final boolean useXbox = false;
		public static final boolean loggingEnabled = false;
		
		//Mecanum Constants
		public static final double rotationModifier = 0.8;
			}
		
	
	public class OI {
		//Joystick Ports
		public static final int LEFT_JOY = 0;
		public static final int RIGHT_JOY = 1;
		public static final int XBOX = 2;
		
		//Joystick Buttons
		public static final int TRIGGER = 1;
		public static final int CLIMB = 2;

		//Xbox Buttons
		public static final int A = 1;
		public static final int B = 2;
		public static final int X = 3;
		public static final int Y = 4;
		public static final int LB = 5;
		public static final int RB = 6;
		public static final int BACK = 7;
		public static final int START = 8;
		public static final int MANIP = 9;

		//fixes values for XBox controoller
		//
		//The controller has issues with any human that tries
		//to work with it so this value is a range that limits
		//the acceptable absolute value of the controller so that
		//the controller functions normally
		public static final double uncivilizedXBoxController = 0.12;
		
	}
}