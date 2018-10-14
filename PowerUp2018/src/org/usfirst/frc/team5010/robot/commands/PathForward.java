package org.usfirst.frc.team5010.robot.commands;

import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

/**
 *
 */
public class PathForward extends Command {

	private static final double max_velocity = 17.89;

	EncoderFollower left, right;
	Trajectory.Config config;
	Trajectory trajectory;
	TankModifier modifier;
	Waypoint[] points;

	public PathForward(Trajectory traj) {
		trajectory = traj;
		modifier = new TankModifier(trajectory).modify(2.02);
		left = new EncoderFollower(modifier.getLeftTrajectory());
		right = new EncoderFollower(modifier.getRightTrajectory());
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		SmartDashboard.putBoolean("Running", true);

		// Encoder Position is the current, cumulative position of your encoder. If
		// you're using an SRX, this will be the
		// 'getEncPosition' function.
		// 1000 is the amount of encoder ticks per full revolution
		// Wheel Diameter is the diameter of your wheels (or pulley for a track system)
		// in meters
		RobotMap.leftEncoder.reset();
		RobotMap.rightEncoder.reset();
		RobotMap.direction.reset();
		left.configureEncoder(RobotMap.distance.getLeftRaw(),RobotMap.encoderPPR, .5);
		right.configureEncoder(RobotMap.distance.getRightRaw(),RobotMap.encoderPPR, .5);

		// The first argument is the proportional gain. Usually this will be quite high
		// The second argument is the integral gain. This is unused for motion profiling
		// The third argument is the derivative gain. Tweak this if you are unhappy with
		// the tracking of the trajectory
		// The fourth argument is the velocity ratio. This is 1 over the maximum
		// velocity you provided in the
		// trajectory configuration (it translates m/s to a -1 to 1 scale that your
		// motors can read)
		// The fifth argument is your acceleration gain. Tweak this if you want to get
		// to a higher or lower speed quicker
		left.configurePIDVA(1.0, 0.0, 0.0, 1 / max_velocity, 0);
		right.configurePIDVA(1.0, 0, 0, 1 / max_velocity, 0);

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
			SmartDashboard.putNumber("left accel", left.getSegment().acceleration);
			SmartDashboard.putNumber("left time delta", left.getSegment().dt);
			SmartDashboard.putNumber("left heading", left.getSegment().heading);
			SmartDashboard.putNumber("left jerk", left.getSegment().jerk);
			SmartDashboard.putNumber("left pos", left.getSegment().position);
			SmartDashboard.putNumber("left velocity", left.getSegment().velocity);
			SmartDashboard.putNumber("left x", left.getSegment().x);
			SmartDashboard.putNumber("left y", left.getSegment().y);
			
			SmartDashboard.putNumber("right accel", right.getSegment().acceleration);
			SmartDashboard.putNumber("right time delta", right.getSegment().dt);
			SmartDashboard.putNumber("right heading", right.getSegment().heading);
			SmartDashboard.putNumber("right jerk", right.getSegment().jerk);
			SmartDashboard.putNumber("right pos", right.getSegment().position);
			SmartDashboard.putNumber("right velocity", right.getSegment().velocity);
			SmartDashboard.putNumber("right x", right.getSegment().x);
			SmartDashboard.putNumber("right y", right.getSegment().y);
			 double distance_covered = ((double)(RobotMap.distance.getLeftRaw() - 0) / RobotMap.encoderPPR)
		                * .5;
			SmartDashboard.putNumber("distance covered", distance_covered);
		double l = left.calculate(RobotMap.distance.getLeftRaw());
		double r = right.calculate(RobotMap.distance.getRightRaw());

		double gyro_heading = (-RobotMap.direction.angle());// Assuming the gyro is giving a value in degrees
		SmartDashboard.putNumber("gyro heading", gyro_heading);
		double desired_heading = Pathfinder.r2d(left.getHeading()); // Should also be in degrees
		SmartDashboard.putNumber("desired Heading", desired_heading);

		double angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading);
		SmartDashboard.putNumber("angle difference", angleDifference);
		double turn = 0.8 * (-1.0 / 80.0) * angleDifference;
		 //turn = 0;

		SmartDashboard.putNumber("left output", (l + turn));
		SmartDashboard.putNumber("right output", (r - turn));
		SmartDashboard.putNumber("turn ", turn);
		RobotMap.drivetrain.drive((l + turn), (r - turn));

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return left.isFinished() && right.isFinished();
	}

	// Called once after isFinished returns true
	protected void end() {
		RobotMap.drivetrain.drive(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
