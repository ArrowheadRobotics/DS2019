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
  boolean isDrawerOut = false;

  boolean aPressed = false;
  boolean yPressed = false;

  public RunIntake() {
    requires(Robot.intake);
    requires(Robot.chassis);
  }

  protected void initialize() {
  }

  protected void execute() {
    // Drawer IN
    if(Robot.oi.a.get()) {
      if(!aPressed) {
        aPressed = true;
        isDrawerOut = false;
      }
    } else {
      aPressed = false;
    }

    // Drawer OUT
    if(Robot.oi.y.get()) {
      if(!yPressed) {
        yPressed = true;
        isDrawerOut = true;
      }
    } else {
      yPressed = false;
    }

    Robot.intake.setDrawerPosition(isDrawerOut);
  }

  protected boolean isFinished() {
    return false;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}
