package org.usfirst.frc.team5010.robot.subsystems;

import org.usfirst.frc.team5010.robot.Robot;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BackRiser extends Subsystem {

	public static SpeedController backriser;
	public BackRiser() {
		// TODO Auto-generated constructor stub
		backriser = new Victor(9);
	}

	public BackRiser(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	public void lower() {
		if(Robot.oi.joyDriver.getRawAxis(2)>0) {
			backriser.set(-scaleInputs(Robot.oi.joyDriver.getRawAxis(2)));
		}
	}
	
	public void raise() {
		if(Robot.oi.joyDriver.getRawAxis(3)>0) {
			backriser.set(scaleInputs(Robot.oi.joyDriver.getRawAxis(3)));
		}
	}
	public double scaleInputs(double input){
	    if(Math.abs(input) < deadZone){
	    	input = 0;
	    }
	    else if (input > 0){
			input = (input - deadZone) * 1/(1 - deadZone);
		}else if (input < 0){
			input = (input + deadZone) * 1/(1 - deadZone);	
		}
		
		return input;
	}
	private double deadZone = .15;
}
