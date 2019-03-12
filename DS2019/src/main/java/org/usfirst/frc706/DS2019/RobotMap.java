package org.usfirst.frc706.DS2019;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Compressor;

public class RobotMap {

    public static PowerDistributionPanel chassispdp;
    
    public static TalonSRX chassisLeftDriveOne, chassisLeftDriveTwo;
    public static TalonSRX chassisRightDriveOne, chassisRightDriveTwo;
    public static TalonSRX elevatorTalon;
    public static TalonSRX drawerTalon;
    public static TalonSRX rampHigh, rampLow; 

    public static Compressor comp;

    public static void init() {
        /* CHASSIS */
        chassisLeftDriveOne = new TalonSRX(Constants.Chassis.LEFT_FRONT);
        chassisLeftDriveTwo = new TalonSRX(Constants.Chassis.LEFT_BACK);
        chassisRightDriveOne = new TalonSRX(Constants.Chassis.RIGHT_FRONT);
        chassisRightDriveTwo = new TalonSRX(Constants.Chassis.RIGHT_BACK);

        chassisRightDriveOne.setInverted(true);
        chassisRightDriveTwo.setInverted(true);

        /* ELEVATOR */
        elevatorTalon = new TalonSRX(Constants.Chassis.ELEVATOR);

        elevatorTalon.setInverted(false);

        drawerTalon = new TalonSRX(Constants.Chassis.DRAWER);
       
        elevatorTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.Elevator.ELEVATOR_HOLD_SLOT, Constants.PID_TIMEOUT);

        elevatorTalon.configClosedLoopPeakOutput(Constants.Elevator.ELEVATOR_MOVE_SLOT, Constants.Elevator.ELEVATOR_MAX_SPEED, Constants.PID_TIMEOUT);
        
        elevatorTalon.config_kF(Constants.Elevator.ELEVATOR_HOLD_SLOT, 0, Constants.PID_TIMEOUT);

        elevatorTalon.config_kP(Constants.Elevator.ELEVATOR_HOLD_SLOT, Constants.Elevator.ELEVATOR_HOLD_KP, Constants.PID_TIMEOUT);
        elevatorTalon.config_kI(Constants.Elevator.ELEVATOR_HOLD_SLOT, Constants.Elevator.ELEVATOR_HOLD_KI, Constants.PID_TIMEOUT);
        elevatorTalon.config_kD(Constants.Elevator.ELEVATOR_HOLD_SLOT, Constants.Elevator.ELEVATOR_HOLD_KD, Constants.PID_TIMEOUT);

        elevatorTalon.setSelectedSensorPosition(0, 0, Constants.PID_TIMEOUT); //ZERO PID POSITION

        int absoluteElevatorPosition = elevatorTalon.getSensorCollection().getPulseWidthPosition(); //STORE POSITION

        absoluteElevatorPosition &= 0xFFF; //MASKS TOP 3 BITS - THESE ARE FOR OVERFLOW AND NOT NEEDED
		if (true) { absoluteElevatorPosition *= -1; }
		if (elevatorTalon.getInverted()) { absoluteElevatorPosition *= -1; }
		
		//Set the quadrature (relative) sensor to match absolute
        elevatorTalon.setSelectedSensorPosition(absoluteElevatorPosition, 0, Constants.PID_TIMEOUT); //SET POSITION
 
        /* RAMPS */
        rampLow = new TalonSRX(Constants.Chassis.LOWER_WINDOW);
        rampHigh = new TalonSRX(Constants.Chassis.UPPER_WINDOW);

        //comp = new Compressor(Constants.Intake.COMPRESSOR);
        //comp.setClosedLoopControl(true);

        /* RAMP */
        //rampHigh = new TalonSRX(Constants.Ramp.RAMP_HIGH);
        //rampLow = new TalonSRX(Constants.Ramp.RAMP_LOW);
    }
}
