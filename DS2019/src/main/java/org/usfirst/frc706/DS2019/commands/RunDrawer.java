package org.usfirst.frc706.DS2019.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc706.DS2019.Robot;
import org.usfirst.frc706.DS2019.OI;

public class RunDrawer extends Command {
  
  public RunDrawer() {
    requires(Robot.drawer);
  }

  protected void initialize() {
  }

  protected void execute() {
    if (Robot.emergencyDisabled) return;
    Robot.drawer.runDrawer(OI.xbox.getRawAxis(5)); //Control drawer with right xbox joystick
  }

  protected boolean isFinished() {
    return false;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}
