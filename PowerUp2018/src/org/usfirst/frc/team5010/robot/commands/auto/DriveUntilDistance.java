package org.usfirst.frc.team5010.robot.commands.auto;

import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveUntilDistance extends PIDCommand {
	
	private double currentAngle = 0;
	private double startAngle = 0;
	private static double p = 0.12;
	private static double i = 0.04;
	private static double d = 0.04;
	private static double tolerance = 2;
	private int stopCount = 0;
	
	 //p 0.06 i 0.04 d 0.04
    public DriveUntilDistance() {
        super("DriveUntilDistance", p, i, d);
    	SmartDashboard.putNumber("P", p); 
    	SmartDashboard.putNumber("I", i);
    	SmartDashboard.putNumber("D", d);

        requires(RobotMap.drivetrain);
        requires(RobotMap.direction);
        requires(RobotMap.range);
        getPIDController().setInputRange(10, 200);
        getPIDController().setOutputRange(-0.2, 0.2);
       
    }
    public DriveUntilDistance(double setPoint) {
        super("DriveUntilDistance", p, i, d);
    	SmartDashboard.putNumber("P", p); 
    	SmartDashboard.putNumber("I", i);
    	SmartDashboard.putNumber("D", d);

        requires(RobotMap.drivetrain);
        requires(RobotMap.direction);
        requires(RobotMap.range);
        getPIDController().setInputRange(10, 200);
        getPIDController().setOutputRange(-0.2, 0.2);
        setSetpoint(setPoint);
    }
    
    public DriveUntilDistance(double setPoint, double angle) {
        super("DriveUntilDistance", p, i, d);
    	SmartDashboard.putNumber("P", p); 
    	SmartDashboard.putNumber("I", i);
    	SmartDashboard.putNumber("D", d);

        requires(RobotMap.drivetrain);
        requires(RobotMap.direction);
        requires(RobotMap.range);
        getPIDController().setInputRange(10, 200);
        getPIDController().setOutputRange(-0.2, 0.2);
        setAngle(angle);
        setSetpoint(setPoint);
    }
    public void setPoint(double setPoint) {
    	setSetpoint(setPoint);
    }
    
    public void setAngle(double setAngle) {
    	startAngle = setAngle;
    }

	// Called just before this Command runs the first time
    protected void initialize() {
    	//getPIDController().setPID(SmartDashboard.getNumber("P", 0.12), SmartDashboard.getNumber("I", 0.04), SmartDashboard.getNumber("D", 0.04));
    	//setPoint(SmartDashboard.getNumber("Setpoint", 20));
//        SmartDashboard.putNumber("Setpoint", getSetpoint());   
        getPIDController().setAbsoluteTolerance(tolerance);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentAngle = RobotMap.direction.angle();
    	SmartDashboard.putNumber("angle", currentAngle);
    	SmartDashboard.putNumber("Error", getPIDController().getError());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {    
    	SmartDashboard.putNumber("DUD Count", stopCount);
    	if(getPIDController().onTarget()) {
    		stopCount++;
    	}else {
    		stopCount = 0;
    	}
    	if(stopCount++ > 10) {
    		return true;
    	}else {
    		return false;
    	}
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
		double range = RobotMap.range.getFrontDistance();
    	SmartDashboard.putNumber("range", range);
		return range;
	}

	@Override
	protected void usePIDOutput(double output) {
		SmartDashboard.putNumber("output", output);
		double leftOutput = -output ; //- ((startAngle - currentAngle) / 180);
		double rightOutput = -output; // + ((startAngle - currentAngle) / 180);
		RobotMap.drivetrain.drive(leftOutput, rightOutput);
	}
	
	
}
