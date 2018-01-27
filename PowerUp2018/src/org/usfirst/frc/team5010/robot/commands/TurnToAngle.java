package org.usfirst.frc.team5010.robot.commands;

import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnToAngle extends PIDCommand {

	private static double p = 0.6;
	private static double i = 0.00;
	private static double d = 0.2;
	private double tolerance = 0.25;
	private double setPoint;
	

	public TurnToAngle(double setPoint) {
		super("TurnToAngle", p, i, d);
		setSetpoint(setPoint);
		this.setPoint = setPoint;
		requires(RobotMap.drivetrain);
		requires(RobotMap.direction);
		getPIDController().setAbsoluteTolerance(tolerance);
		getPIDController().setOutputRange(-0.3, 0.3);
		getPIDController().setInputRange(-360, 361);
	}

	@Override
	protected double returnPIDInput() {
		
		return RobotMap.direction.angle();
	}

	protected void execute() {
		SmartDashboard.putNumber("angle", RobotMap.direction.angle());

	}

	protected void initialize() {
		setSetpoint(setPoint);
		//RobotMap.direction.reset();
		getPIDController().setPID(SmartDashboard.getNumber("P", 0.01), SmartDashboard.getNumber("I", 0),
				SmartDashboard.getNumber("D", 0));
	}

	@Override
	protected void usePIDOutput(double output) {
		RobotMap.drivetrain.spin(output);
		SmartDashboard.putNumber("output", output);
		

	}

	protected void end() {

		RobotMap.drivetrain.stop();

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
