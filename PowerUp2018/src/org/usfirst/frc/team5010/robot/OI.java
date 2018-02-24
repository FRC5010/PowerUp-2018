/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5010.robot;

import org.usfirst.frc.team5010.robot.commands.AutonScale;
import org.usfirst.frc.team5010.robot.commands.CalibratePots;
import org.usfirst.frc.team5010.robot.commands.CloseIntake;
import org.usfirst.frc.team5010.robot.commands.LowerArms;
import org.usfirst.frc.team5010.robot.commands.LowerHeightJoystick;
import org.usfirst.frc.team5010.robot.commands.OpenIntake;
import org.usfirst.frc.team5010.robot.commands.ResetGyro;
import org.usfirst.frc.team5010.robot.commands.SetLowerHeight;
import org.usfirst.frc.team5010.robot.commands.SetUpperHeight;
import org.usfirst.frc.team5010.robot.commands.ShiftDown;
import org.usfirst.frc.team5010.robot.commands.ShiftUp;
import org.usfirst.frc.team5010.robot.commands.UpperHeightJoystick;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	public Joystick joyDriver = new Joystick(0);
	// public Joystick joyDriver2 = new Joystick(1);
	public Joystick joyCoDriver = new Joystick(1);

	private Button driverButtonY = new JoystickButton(joyDriver, 4);
	private Button driverButtonBack = new JoystickButton(joyDriver, 7);
	
	private Button driverButtonRB = new JoystickButton(joyDriver, 6);
	private Button driverButtonLB = new JoystickButton(joyDriver, 5);
	private Button driverButtonA = new JoystickButton(joyDriver, 1);
	private Button driverButtonB = new JoystickButton(joyDriver, 2);
	private Button driverButtonRJoy = new JoystickButton(joyDriver, 10);
	private Button driverButtonLJoy = new JoystickButton(joyDriver, 9);

	private Button codriverButtonA = new JoystickButton(joyCoDriver, 1);
	private Button codriverButtonB = new JoystickButton(joyCoDriver, 2);
	private Button codriverButtonX = new JoystickButton(joyCoDriver, 3);
	private Button codriverButtonY = new JoystickButton(joyCoDriver, 4);
	private Button codriverButtonLB = new JoystickButton(joyCoDriver, 5);
	private Button codriverButtonRB = new JoystickButton(joyCoDriver, 6);
	private Button codriverButtonBack = new JoystickButton(joyCoDriver, 7);
	private Button codriverButtonSel = new JoystickButton(joyCoDriver, 8);
	
	

	public OI() {
		// driverButtonBack.whenReleased(new TurnToAngle(45));
		driverButtonRB.whenPressed(new CloseIntake());
		driverButtonLB.whenPressed(new OpenIntake());
		
		driverButtonRJoy.whenPressed(new ShiftUp());
		driverButtonLJoy.whenPressed(new ShiftDown());
		
		
	
		codriverButtonLB.whileHeld(new LowerHeightJoystick());
		codriverButtonRB.whileHeld(new UpperHeightJoystick());
		
		
		codriverButtonA.whenPressed(new SetUpperHeight(false));
		codriverButtonB.whenPressed(new SetUpperHeight(true));
		codriverButtonX.whenPressed(new LowerArms());
		codriverButtonY.whenPressed(new SetLowerHeight(true));
		
		codriverButtonSel.whenPressed(new ResetGyro());
		codriverButtonSel.whenPressed(new AutonScale());
//		codriverButtonSel.whenPressed(new AutonScale());
		// codriverButtonY.whenPressed(new SetUpperHeight());
		//	codriverButtonX.whenPressed(new SetLowerHeight());
		codriverButtonBack.whenPressed(new CalibratePots());
		driverButtonBack.whenPressed(new CalibratePots());

		joyDriver.getName();
		SmartDashboard.putString("joystick Name", joyDriver.getName());
	}
}
