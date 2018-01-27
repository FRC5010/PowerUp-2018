package org.usfirst.frc.team5010.robot.subsystems;

import org.usfirst.frc.team5010.robot.Robot;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CubeIntake extends Subsystem {
	public static SpeedController intakeMotorLeft;
	public static SpeedController intakeMotorRight;
	public CubeIntake() {
		// TODO Auto-generated constructor stub
		intakeMotorLeft = new Victor(8);
		intakeMotorLeft = new Victor(9);
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	public void devourCube() {
		if(Robot.oi.joyDriver.getRawAxis(2)>0) {
			intakeMotorLeft.set(scaleInputs(Robot.oi.joyDriver.getRawAxis(2)));
			intakeMotorRight.set(-scaleInputs(Robot.oi.joyDriver.getRawAxis(2)));
		}
	}
	
	public void spitCube() {
		if(Robot.oi.joyDriver.getRawAxis(3)>0) {
			intakeMotorLeft.set(-scaleInputs(Robot.oi.joyDriver.getRawAxis(3)));
			intakeMotorRight.set(scaleInputs(Robot.oi.joyDriver.getRawAxis(3)));
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
