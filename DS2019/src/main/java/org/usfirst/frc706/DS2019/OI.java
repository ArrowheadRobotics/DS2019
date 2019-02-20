package org.usfirst.frc706.DS2019;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
   
    public Joystick leftJoy;
    public Joystick rightJoy;
    public XboxController xbox;
    public JoystickButton leftY;
    public JoystickButton rightY;
    
    public JoystickButton start, back, a, b, y, x, rb, lb, trigger, clb;

    public OI() {
        xbox = new XboxController(Constants.OI.XBOX);
        rightJoy = new Joystick(Constants.OI.RIGHT_JOY);
        leftJoy = new Joystick(Constants.OI.LEFT_JOY);
        leftY = new JoystickButton(xbox, 1);
        rightY = new JoystickButton(xbox, 5);
    }
}

