package org.usfirst.frc.team5010.robot.subsystems;
import org.usfirst.frc.team5010.robot.Robot;
import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BackRiser extends PIDSubsystem {
	 /* Default constructor.
	 */
	private double deadZone = .15;
	private static final double MAX_ANGLE = 135;
	private static final double MIN_ANGLE = 28;
	
	private static final double MIN_HEIGHT = 10;
	private static final double MAX_HEIGHT = 50;
	
	private PIDController PID;
	
	public BackRiser(String name) {
		super(name, 0.2, .0, 0.0);
		PID = getPIDController();
		PID.setAbsoluteTolerance(2);
		PID.setInputRange(MIN_HEIGHT, MAX_HEIGHT);
		PID.setOutputRange(-.7, .3);
		PID.setSetpoint(11);
		//PID.enable();

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
		SmartDashboard.putNumber("Back Riser Potentiometer" , getPotValue());
		SmartDashboard.putNumber("BackRiser getHeight", getHeight());
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
		height = height < MIN_HEIGHT ? MIN_HEIGHT : height;
		height = height > MAX_HEIGHT ? MAX_HEIGHT : height;
		PID.setSetpoint(height);
	}

	//armlength 22 // //h1 30.25 // 
	public double getHeight() {
		double potValue = getPotValue();
		double height = 30.25 - 22 * Math.cos(potValue * (Math.PI / 180));
		//SmartDashboard.putNumber("BackRiser height:", height);
		return height;
	}
	
	public double getPotValue() {
		return 360 - RobotMap.backRiserPotent.get();
	}
	
//	/@Override
	protected double returnPIDInput() {
		return getHeight();
	}

	//@Override
	protected void usePIDOutput(double output) {
		SmartDashboard.putNumber("backRiser unaltered PID output:", output);
		SmartDashboard.putNumber("Back Riser Potentiometer" , getPotValue());
		SmartDashboard.putNumber("BackRiser getHeight", getHeight());
		
		
		
//		if(getPotValue() > MAX_ANGLE) {
//			output = 0;
//		} else if(getPotValue() < MIN_ANGLE) {
//			output = 0;
//		}
		SmartDashboard.putNumber("backRiser PID output:", output);
		SmartDashboard.putNumber("backRiser PID ERROR ", PID.getError());
		SmartDashboard.putNumber("backRiser PID SetPoint ", PID.getSetpoint());
		RobotMap.backriser.set(-output);
	}
}
