/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5010.robot;

import org.usfirst.frc.team5010.robot.commands.EatCube;
import org.usfirst.frc.team5010.robot.commands.OpenIntake;
import org.usfirst.frc.team5010.robot.commands.ShiftDown;
import org.usfirst.frc.team5010.robot.commands.ShiftUp;
import org.usfirst.frc.team5010.robot.commands.SpitCube;
import org.usfirst.frc.team5010.robot.commands.SwitchDriveMode;

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
	public Joystick joyCoDriver = new Joystick(2);

	private Button driverButtonY = new JoystickButton(joyDriver, 4);
	private Button driverButtonBack = new JoystickButton(joyDriver, 7);
	private Button driverButtonRB = new JoystickButton(joyDriver, 6);
	private Button driverButtonLB = new JoystickButton(joyDriver, 5);

	private Button codriverButtonA = new JoystickButton(joyCoDriver, 1);
	private Button codriverButtonLB = new JoystickButton(joyCoDriver, 5);
	private Button codriverButtonRB = new JoystickButton(joyCoDriver, 6);

	public OI() {
		driverButtonY.whenPressed(new SwitchDriveMode());
		// driverButtonBack.whenReleased(new TurnToAngle(45));
		 driverButtonRB.whenPressed(new ShiftUp());
		 driverButtonLB.whenPressed(new ShiftDown());

		codriverButtonLB.whileHeld(new EatCube());
		codriverButtonRB.whileHeld(new SpitCube());
		codriverButtonA.whenPressed(new OpenIntake());

		joyDriver.getName();
		SmartDashboard.putString("joystick Name", joyDriver.getName());
	}
}
