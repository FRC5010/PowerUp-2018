package org.usfirst.frc.team5010.robot.subsystems;

import org.usfirst.frc.team5010.robot.Robot;
import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Solenoid;


public class CubeIntake extends Subsystem {
	private double deadZone = .15;
	
	/** 
	 * Default constructor.
	 */
	public CubeIntake() {
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	public void devourCube() {
		if (Robot.oi.joyCoDriver.getRawAxis(2) > 0) {
			RobotMap.intakeMotorLeft.set(scaleInputs(Robot.oi.joyCoDriver.getRawAxis(2)));
			RobotMap.intakeMotorRight.set(-scaleInputs(Robot.oi.joyCoDriver.getRawAxis(2)));
		}
	}
	
	public void spitCube() {
		if (Robot.oi.joyCoDriver.getRawAxis(3) > 0) {
			RobotMap.intakeMotorLeft.set(-scaleInputs(Robot.oi.joyCoDriver.getRawAxis(3)));
			RobotMap.intakeMotorRight.set(scaleInputs(Robot.oi.joyCoDriver.getRawAxis(3)));
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
	
	/**
	 * Close pnuematics.
	 */
	public void closeIntake() {
		RobotMap.intake.set(true);
		System.out.println("Intake is closing");
	}

	/**
	 * Open pnuematics.
	 */
	public void openIntake() {
		RobotMap.intake.set(false);
		System.out.println("Intake is opening");
	}

	public void stop() {
		RobotMap.intake.set(false);
	}

}
