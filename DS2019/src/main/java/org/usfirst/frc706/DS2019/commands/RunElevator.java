package org.usfirst.frc706.DS2019.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc706.DS2019.Constants;
import org.usfirst.frc706.DS2019.OI;
import org.usfirst.frc706.DS2019.Robot;
import org.usfirst.frc706.DS2019.misc.ElevatorPosition;
import org.usfirst.frc706.DS2019.subsystems.Elevator;

public class RunElevator extends Command {
  ElevatorPosition position = ElevatorPosition.LOW;

  boolean upPressed = false;
  boolean downPressed = false;
  boolean yPressed = false;
  boolean aPressed = false;
  boolean xPressed = false;

  public RunElevator() {
    requires(Robot.elevator);
  }

  protected void initialize() {
  }

  protected void execute() {
    if (Robot.emergencyDisabled) return;

    // UP
    if (OI.xbox.getPOV() == 0) {
      if (!upPressed) {
        upPressed = true;
        Robot.elevator.position = Robot.elevator.position.increase();
      }
    } else if (upPressed && OI.xbox.getPOV() != 0) {
      upPressed = false;
    }

    // DOWN
    if (OI.xbox.getPOV() == 180) {
      if (!downPressed) {
        downPressed = true;
        Robot.elevator.position = Robot.elevator.position.decrease();
      }
    } else if (downPressed && OI.xbox.getPOV() != 180) {
      downPressed = false;
    }

    if (!yPressed && OI.xbox.getYButton()) {
      Elevator.modifierE -= 200;
      yPressed = true;
    } else
      yPressed = false;

    if (!aPressed && OI.xbox.getAButton()) {
      Elevator.modifierE += 200;
      aPressed = true;
    } 
      aPressed = false;

    if (!xPressed && OI.xbox.getXButton()) {
      Elevator.grabbingYesOrNo = !Elevator.grabbingYesOrNo;
      xPressed = true;
    } else if (xPressed && !OI.xbox.getXButton())
      xPressed = false;

    SmartDashboard.putBoolean("grabbing", Elevator.grabbingYesOrNo);

    if (!OI.xbox.getRawButton(Constants.OI.RB)) {
      Robot.elevator.setPosition(Robot.elevator.position);
    } else {
      Robot.elevator.runElevator(OI.xbox.getRawAxis(1));
    }
  }

  protected boolean isFinished() {
    return false;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}
