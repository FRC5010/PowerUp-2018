package org.usfirst.frc.team5010.robot.subsystems;
import org.usfirst.frc.team5010.robot.Robot;
import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class FrontRiser extends PIDSubsystem {
	 /* Default constructor.
	 */
	private double deadZone = .15;

	public FrontRiser(String name) {
		super(name, 0.1, 0.0, 0.2);
		disable();
	}

	@Override
	protected void initDefaultCommand() {
	}

	/**
	 * Function determines whether joystick controller is being pressed or not.
	 * @return boolean
	 */
	public boolean isAxisPressed() {
		if (Math.abs(Robot.oi.joyCoDriver.getRawAxis(1)) > 0.0) {
			return true;
		}
		return false;
	}

	public void move() {
		//Handles raising and lowering
		//Reason: Raising and lowering will never be done at the same time
		if (Math.abs(Robot.oi.joyCoDriver.getRawAxis(1)) > 0.0) {
			double output = scaleInputs(Robot.oi.joyCoDriver.getRawAxis(1));
			RobotMap.frontriser.set(-output);
			SmartDashboard.putNumber("FrontRiser", output);
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
		setSetpoint(height);
	}
	
	public double getHeight() {
		double potValue = RobotMap.backRiserPot.get();
		double height = Math.sin(potValue)*RobotMap.armLength;
		return height;
	}
	
	protected double returnPIDInput() {
		return getHeight();
	}
	@Override
	protected void usePIDOutput(double output) {
		RobotMap.frontriser.set(output);
	}

}
