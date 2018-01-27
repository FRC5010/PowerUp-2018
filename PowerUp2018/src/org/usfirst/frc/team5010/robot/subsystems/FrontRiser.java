package org.usfirst.frc.team5010.robot.subsystems;

import org.usfirst.frc.team5010.robot.Robot;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FrontRiser extends Subsystem {

	public static SpeedController frontriser;
	public FrontRiser() {
		// TODO Auto-generated constructor stub
		frontriser = new Victor(9);
	}

	public FrontRiser(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	public void lower() {
		if(Robot.oi.joyDriver.getRawAxis(2)>0) {
			frontriser.set(-scaleInputs(Robot.oi.joyDriver.getRawAxis(2)));
		}
	}
	
	public void raise() {
		if(Robot.oi.joyDriver.getRawAxis(3)>0) {
			frontriser.set(scaleInputs(Robot.oi.joyDriver.getRawAxis(3)));
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
