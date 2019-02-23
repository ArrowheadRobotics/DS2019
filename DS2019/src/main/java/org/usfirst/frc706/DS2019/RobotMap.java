package org.usfirst.frc706.DS2019;

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
        elevatorTalon = new TalonSRX(Constants.Elevator.ELEVATOR);
        /*elevatorTalon.configClosedLoopPeakOutput(Constants.Elevator.ELEVATOR_MOVE_SLOT, Constants.Elevator.ELEVATOR_MAX_SPEED);
        
        elevatorTalon.config_kP(Constants.Elevator.ELEVATOR_HOLD_SLOT, Constants.Elevator.ELEVATOR_HOLD_KP);
        elevatorTalon.config_kD(Constants.Elevator.ELEVATOR_HOLD_SLOT, Constants.Elevator.ELEVATOR_HOLD_KD);
        elevatorTalon.config_kI(Constants.Elevator.ELEVATOR_HOLD_SLOT, Constants.Elevator.ELEVATOR_HOLD_KI);

        elevatorTalon.config_kP(Constants.Elevator.ELEVATOR_MOVE_SLOT, Constants.Elevator.ELEVATOR_HOLD_KP);
        elevatorTalon.config_kD(Constants.Elevator.ELEVATOR_MOVE_SLOT, Constants.Elevator.ELEVATOR_HOLD_KD);
        elevatorTalon.config_kI(Constants.Elevator.ELEVATOR_MOVE_SLOT, Constants.Elevator.ELEVATOR_HOLD_KI);*/

        /* INTAKE */
        //drawerTalon = new TalonSRX(Constants.Intake.DRAWER);
        //drawerTalon.configClosedLoopPeakOutput(Constants.Intake.DRAWER_SLOT, Constants.Intake.DRAWER_MAX_SPEED);

        comp = new Compressor(Constants.Intake.COMPRESSOR);
        //comp.setClosedLoopControl(true);

        /* RAMP */
        //rampHigh = new TalonSRX(Constants.Ramp.RAMP_HIGH);
        //rampLow = new TalonSRX(Constants.Ramp.RAMP_LOW);
    }
}
