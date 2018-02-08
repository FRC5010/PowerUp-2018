package org.usfirst.frc.team5010.robot.subsystems;
import org.usfirst.frc.team5010.robot.Robot;
import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BackRiser extends Subsystem {//extends PIDSubsystem {
	 /* Default constructor.
	 */
	private double deadZone = .15;
	
	public BackRiser(String name) {
//		super(name, 0.1, 0.0, 0.2);
//		disable();
	}

	//@Override
	protected void initDefaultCommand() {
	}
	
	public void move() {
		//Handles raising and lowering
		//Reason: Raising and lowering will never be done at the same time
		if (Math.abs(Robot.oi.joyCoDriver.getRawAxis(5)) > 0.0) {
			//Raise and lower should be on the same axis, because they shouldn't be triggered at the same time.
			double output = scaleInputs(Robot.oi.joyCoDriver.getRawAxis(5));
			RobotMap.backriser.set(output);
			SmartDashboard.putNumber("BackRiser speed:", output);			
		}
	}

	public double scaleInputs(double input){
	    if (Math.abs(input) < deadZone){
	    	input = 0;
	    }
	    else if (input > 0){
			input = (input - deadZone) * 1/(1 - deadZone);
		}else if (input < 0){
			input = (input + deadZone) * 1/(1 - deadZone);	
		}
		
		return input;
	}
	public void setHeight(double height) {
	//	setSetpoint(height);
	}

	public double getHeight() {
		double potValue = RobotMap.backRiserPotent.get();
		double height = Math.sin(potValue)*RobotMap.armLength;
		SmartDashboard.putNumber("BackRiser height:", height);
		return height;
	}
	
//	/@Override
	protected double returnPIDInput() {
		return getHeight();
	}

	//@Override
	protected void usePIDOutput(double output) {
		SmartDashboard.putNumber("BackRiser PID output:", output);
		//RobotMap.backriser.set(output);
	}
}
