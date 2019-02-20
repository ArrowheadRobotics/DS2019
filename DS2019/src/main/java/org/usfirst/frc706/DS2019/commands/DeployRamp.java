package org.usfirst.frc706.DS2019.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc706.DS2019.Constants;
import org.usfirst.frc706.DS2019.OI;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import org.usfirst.frc706.DS2019.Robot;
import org.usfirst.frc706.DS2019.subsystems.*;

public class DeployRamp extends Command {
  int stepsToTake;

  public DeployRamp() {
    requires(Robot.ramp);
    requires(Robot.chassis);
  }

  protected void initialize() {
  }

  protected void execute() {
    Ramp.runRamp(0.5);
  }

  protected boolean isFinished() {
    return false;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}
