package org.usfirst.frc.team5010.robot.subsystems;
import org.usfirst.frc.team5010.robot.Robot;
import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class BackRiser extends PIDSubsystem {
	
	public BackRiser(String name) {
		super(name, 0.1, 0.0, 0.2);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	public void move() {
		//Handles raising and lowering
		//Reason: Raising and lowering will never be done at the same time
		if(Math.abs(Robot.oi.joyCODriver.getRawAxis(1))>0.0) {
			//Raise and lower should be on the same axis, because they shouldn't be triggered at the same time.
			RobotMap.backriser.set(scaleInputs(Robot.oi.joyCODriver.getRawAxis(1)));
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
	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		double potValue = RobotMap.backRiserPot.get();
		double height = Math.sin(potValue)*RobotMap.armLength;
		return height;
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		RobotMap.backriser.set(output);
	}
}
