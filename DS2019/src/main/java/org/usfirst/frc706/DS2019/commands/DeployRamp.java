package org.usfirst.frc706.DS2019.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc706.DS2019.Robot;
import org.usfirst.frc706.DS2019.OI;

public class DeployRamp extends Command {
  int stepsToTake;

  public DeployRamp() {
    requires(Robot.ramp);
  }

  protected void initialize() {
  }

  protected void execute() {
    if (Robot.emergencyDisabled) return;
   Robot.ramp.runRamp(OI.xbox.getRawAxis(2),OI.xbox.getRawAxis(3)); //Control ramps with left and right trigger for up/down
  }

  protected boolean isFinished() {
    return false;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}
