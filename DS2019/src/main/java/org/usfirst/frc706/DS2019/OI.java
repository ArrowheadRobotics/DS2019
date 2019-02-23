package org.usfirst.frc706.DS2019;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
   
    public Joystick rightJoy;
    public Joystick leftJoy;
    public XboxController xbox;
    
    public JoystickButton start, back, a, b, y, x, rb, lb, trigger, clb;

    public OI() {
        rightJoy = new Joystick(Constants.OI.RIGHT_JOY);
        leftJoy = new Joystick(Constants.OI.LEFT_JOY);
        xbox = new XboxController(Constants.OI.XBOX);

        a = new JoystickButton(xbox, Constants.OI.A);   
        y = new JoystickButton(xbox, Constants.OI.Y);
    }
}

