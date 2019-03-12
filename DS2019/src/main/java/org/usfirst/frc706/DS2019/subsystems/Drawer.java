package org.usfirst.frc706.DS2019.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import org.usfirst.frc706.DS2019.RobotMap;
import org.usfirst.frc706.DS2019.Constants;
import org.usfirst.frc706.DS2019.commands.RunDrawer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drawer extends Subsystem {

    private final static TalonSRX drawerTalon = RobotMap.drawerTalon;

    public void initDefaultCommand() {
        setDefaultCommand(new RunDrawer());
    }

    public void periodic() {
    }

    public void runDrawer(double percent) {
        drawerTalon.set(ControlMode.PercentOutput, -percent*Constants.Drawer.DRAWER_MODIFIER);
    }
}