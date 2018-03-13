package org.usfirst.frc.team5010.robot.commands.auto;

import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnToAngle extends PIDCommand {

	private static double p = 0.6;
	private static double i = 0.00;
	private static double d = 0.2;
	private double tolerance = 0.25;
	private double setPoint;
	
	private static final double driveTrainWidth = 24.375;
	double radius;
	double smallOutputRatio;
	

	public TurnToAngle(double setPoint, double radius) {
		super("TurnToAngle", p, i, d);
		setSetpoint(setPoint);
		this.setPoint = setPoint;
		this.radius = radius;
		smallOutputRatio = radius / (this.radius + driveTrainWidth);
		requires(RobotMap.drivetrain);
		requires(RobotMap.direction);
		getPIDController().setAbsoluteTolerance(tolerance);
		getPIDController().setOutputRange(-.5, .5);
		getPIDController().setInputRange(-360, 361);
		
		SmartDashboard.putNumber("smallOutput ratio", smallOutputRatio);
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
		double smallOutput = smallOutputRatio * output;
		if(getPIDController().getSetpoint() < 0) {
			RobotMap.drivetrain.drive(smallOutput, output);
			SmartDashboard.putNumber("left output", output);
			SmartDashboard.putNumber("right output", smallOutput);
			
		}else {
			RobotMap.drivetrain.drive(output, smallOutput);
			SmartDashboard.putNumber("left output", smallOutput);
			SmartDashboard.putNumber("right output", output);
		}
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
