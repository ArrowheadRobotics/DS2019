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
        chassisLeftDriveOne = new TalonSRX(Constants.Chassis.LEFT_FRONT);
        chassisLeftDriveTwo = new TalonSRX(Constants.Chassis.LEFT_BACK);
        chassisRightDriveOne = new TalonSRX(Constants.Chassis.RIGHT_FRONT);
        chassisRightDriveTwo = new TalonSRX(Constants.Chassis.RIGHT_BACK);

        chassisRightDriveOne.setInverted(true);
        chassisRightDriveTwo.setInverted(true);

        elevatorTalon = new TalonSRX(Constants.Elevator.ELEVATOR);

        //drawerTalon = new TalonSRX(Constants.Intake.DRAWER);

        //rampHigh = new TalonSRX(Constants.Ramp.RAMP_HIGH);
        //rampLow = new TalonSRX(Constants.Ramp.RAMP_LOW);

        comp = new Compressor(0);
        //comp.setClosedLoopControl(true);
    }
}
