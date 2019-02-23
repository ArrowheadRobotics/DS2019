package org.usfirst.frc706.DS2019.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc706.DS2019.Constants;
import org.usfirst.frc706.DS2019.OI;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import org.usfirst.frc706.DS2019.Robot;

import org.usfirst.frc706.DS2019.subsystems.Chassis;
import org.usfirst.frc706.DS2019.subsystems.Elevator;
import org.usfirst.frc706.DS2019.subsystems.Intake;

public class RunIntake extends Command {
  int stepsToTake;

  public RunIntake() {
    requires(Robot.intake);
    requires(Robot.chassis);
  }

  protected void initialize() {
  }

  protected void execute() {
    Robot.intake.runDrawerMotor(0.5);
  }

  protected boolean isFinished() {
    return false;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}
