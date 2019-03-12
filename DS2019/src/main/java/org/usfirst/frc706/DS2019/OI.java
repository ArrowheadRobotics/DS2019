package org.usfirst.frc706.DS2019;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
    static final public Joystick rightJoy = new Joystick(Constants.OI.RIGHT_JOY);
    static final public Joystick leftJoy = new Joystick(Constants.OI.LEFT_JOY);
    static final public XboxController xbox = new XboxController(Constants.OI.XBOX);
    
    public JoystickButton a = new JoystickButton(xbox, Constants.OI.A), b = new JoystickButton(xbox, Constants.OI.Y);
}