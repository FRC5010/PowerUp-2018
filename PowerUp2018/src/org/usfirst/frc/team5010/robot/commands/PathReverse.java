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
public class PathReverse extends Command {

	private static final double max_velocity = 17.89;

	EncoderFollower left, right;
	Trajectory.Config config;
	Trajectory trajectory;
	TankModifier modifier;
	Waypoint[] points;

	public PathReverse(Trajectory traj) {
		trajectory = traj;
		modifier = new TankModifier(trajectory).modify(2.02);
		left = new EncoderFollower(modifier.getRightTrajectory());
		right = new EncoderFollower(modifier.getLeftTrajectory());
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		RobotMap.distance.reset();
		RobotMap.gyro.reset();
		// Encoder Position is the current, cumulative position of your encoder. If
		// you're using an SRX, this will be the
		// 'getEncPosition' function.
		// 1000 is the amount of encoder ticks per full revolution
		// Wheel Diameter is the diameter of your wheels (or pulley for a track system)
		// in meters
		left.configureEncoder(-RobotMap.distance.getLeftRaw(), RobotMap.encoderPPR, .5); // opposite for reverse
		right.configureEncoder(-RobotMap.distance.getRightRaw(), RobotMap.encoderPPR, .5);

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

		double l = -left.calculate(-RobotMap.distance.getLeftRaw());
		double r = -right.calculate(-RobotMap.distance.getRightRaw());

		double gyro_heading = (-RobotMap.direction.angle());// Assuming the gyro is giving a value in degrees
		SmartDashboard.putNumber("gyro heading", gyro_heading);
		double desired_heading = Pathfinder.r2d(left.getHeading()); // Should also be in degrees
		SmartDashboard.putNumber("desired Heading", desired_heading);

		double angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading);
		SmartDashboard.putNumber("angle difference", angleDifference);
		double turn = 0.8 * (-1.0 / 80.0) * angleDifference;

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
