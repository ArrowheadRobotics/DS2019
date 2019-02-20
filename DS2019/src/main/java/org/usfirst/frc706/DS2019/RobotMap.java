package org.usfirst.frc706.DS2019;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Compressor;

public class RobotMap {
    public static PowerDistributionPanel chassispdp;
    public static TalonSRX chassisleftDriveOne, chassisleftDriveTwo;
    public static TalonSRX chassisrightDriveOne, chassisrightDriveTwo;
    public static TalonSRX elevatorTalon;
    public static Compressor comp;
    
    private static int leftOne = Constants.Chassis.LEFT_FRONT;
    private static int leftTwo = Constants.Chassis.LEFT_BACK;
    private static int rightOne = Constants.Chassis.RIGHT_FRONT;
    private static int rightTwo = Constants.Chassis.RIGHT_BACK;
    private static int elevatorOne = Constants.Chassis.ELEVATOR;

    public static void init() {
        chassisleftDriveOne = new TalonSRX(leftOne);
        chassisleftDriveTwo = new TalonSRX(leftTwo);
        chassisrightDriveOne = new TalonSRX(rightOne);
        chassisrightDriveTwo = new TalonSRX(rightTwo);
        elevatorTalon = new TalonSRX(elevatorOne);
        comp = new Compressor(0);
        //comp.setClosedLoopControl(true);
    }
}