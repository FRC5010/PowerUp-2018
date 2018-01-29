package org.usfirst.frc.team5010.robot.subsystems;
import org.usfirst.frc.team5010.robot.RobotMap;
import org.usfirst.frc.team5010.robot.Robot;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BackRiser extends Subsystem {

	public BackRiser() {
	}
	
	public BackRiser(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	public void move() {
		//Handles raising and lowering
		//Reason: Raising and lowering will never be done at the same time
		if(Math.abs(Robot.oi.joyCoDriver.getRawAxis(1))>0.0) {
			//Raise and lower should be on the same axis, because they shouldn't be triggered at the same time.
			RobotMap.backriser.set(scaleInputs(Robot.oi.joyCoDriver.getRawAxis(1)));
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
