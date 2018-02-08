package org.usfirst.frc.team5010.robot.commands;

import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SetArmHeight extends PIDCommand {

	private static double p = 0.6;
	private static double i = 0.00;
	private static double d = 0.2;
	private double tolerance = 0.25;
	private double setArmHeight;
	

	public SetArmHeight(double setArmHeight) {
		super("SetArmToSwitch", p, i, d);
		setSetpoint(setArmHeight);
		this.setArmHeight = setArmHeight;
		requires(RobotMap.frontLifter);
		requires(RobotMap.backLifter);
		getPIDController().setAbsoluteTolerance(tolerance);
		getPIDController().setOutputRange(-0.3, 0.3);
		getPIDController().setInputRange(-360, 361);
	}

	@Override
	protected double returnPIDInput() {
		
		return RobotMap.frontRiserPotent.get();		
	}

	protected void execute() {
		//SmartDashboard.putNumber("Height", RobotMap.direction.angle());		again, change this for potentiometers

	}

	protected void initialize() {
		setSetpoint(setArmHeight);
		//RobotMap.direction.reset();
		getPIDController().setPID(SmartDashboard.getNumber("P", 0.01), SmartDashboard.getNumber("I", 0),
				SmartDashboard.getNumber("D", 0));
	}

	@Override
	protected void usePIDOutput(double output) {
		//RobotMap.drivetrain.spin(output);
		//SmartDashboard.putNumber("output", output);
		

	}

	protected void end() {

	//	RobotMap.drivetrain.stop(); Change this so it works for the arm instead of the drivetrain

	}

	protected void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		SmartDashboard.putNumber("Error",getPIDController().getError());
		return getPIDController().onTarget();

	}

}