package org.usfirst.frc.team5010.robot.commands;

import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveForDistance extends PIDCommand {
	
	private double currentAngle = 0;
	private double startAngle = 0;
	private static double p = 0.12;
	private static double i = 0.04;
	private static double d = 0.04;
	private static double tolerance = 2;
	private static int toleranceBuffer = 10;
	

    public DriveForDistance() {
    	super("DriveForDistance", p, i, d);
    	SmartDashboard.putNumber("P", p); 
    	SmartDashboard.putNumber("I", i);
    	SmartDashboard.putNumber("D", d);

        requires(RobotMap.drivetrain);
        requires(RobotMap.direction);
        //requires(RobotMap.distanceSensor);
        getPIDController().setInputRange(0, 500);
        getPIDController().setOutputRange(-0.4, 0.4);
    }
    public void setPoint(double setPoint) {
    	setSetpoint(setPoint);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//getPIDController().setPID(SmartDashboard.getNumber("P", 0.12), SmartDashboard.getNumber("I", 0.04), SmartDashboard.getNumber("D", 0.04));
    	getPIDController().setAbsoluteTolerance(tolerance);
    	RobotMap.direction.reset();
        startAngle = RobotMap.direction.angle();
    	SmartDashboard.putNumber("startAngle", startAngle);
    	//RobotMap.distanceSensor.reset();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentAngle = RobotMap.direction.angle();
    	SmartDashboard.putNumber("currentAngle", currentAngle);
    	SmartDashboard.putNumber("Error", getPIDController().getError());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return getPIDController().onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	getPIDController().reset();
    	RobotMap.drivetrain.stop();
    
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }

	@Override
	protected double returnPIDInput() {
		//double distance = RobotMap.distanceSensor.getDistance();
    	SmartDashboard.putNumber("distance", 0);
		return 1;
		
	}

	@Override
	protected void usePIDOutput(double output) {
		SmartDashboard.putNumber("output", output);
		//RobotMap.drivetrain.drive(output, output);
		double leftOutput = output + ((startAngle - currentAngle) / 180);
		double rightOutput = output - ((startAngle - currentAngle) / 180);
		SmartDashboard.putNumber("leftOutput", leftOutput);
		SmartDashboard.putNumber("rightOutput", rightOutput);
		RobotMap.drivetrain.drive(leftOutput, rightOutput);		
	}
}
