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
import org.usfirst.frc706.DS2019.misc.ElevatorPosition;

public class RunElevator extends Command {
  ElevatorPosition position = ElevatorPosition.LOW;

  boolean upPressed = false;
  boolean downPressed = false;
  
  public RunElevator() {
    requires(Robot.elevator);
    requires(Robot.chassis);
  }

  protected void initialize() {
  }

  protected void execute() {
    /*
    // UP
    if(Robot.oi.xbox.getPOV() == 0) {
      if(!upPressed) {
        upPressed = true;
        position = position.increase();
      }
    } else {
      upPressed = false;
    }

    // DOWN
    if(Robot.oi.xbox.getPOV() == 180) {
      if(!downPressed) {
        downPressed = true;
        position = position.decrease();
      }
    } else {
      downPressed = false;
    }

    Robot.elevator.setPosition(position);
    */

    Robot.elevator.runElevator(0.5);
  }

  protected boolean isFinished() {
    return false;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}
