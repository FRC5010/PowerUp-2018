package org.usfirst.frc.team5010.robot.subsystems;

import org.usfirst.frc.team5010.robot.RobotMap;
import org.usfirst.frc.team5010.robot.commands.TankDriveForward;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrainMain extends Subsystem {

	SpeedController rightMotor = RobotMap.driveMotorRight;
	SpeedController leftMotor = RobotMap.driveMotorLeft;

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new TankDriveForward());
	}

	public void drive(double leftPower, double rightPower) {
		rightMotor.set(-rightPower);
		leftMotor.set(leftPower);
	}

	public void spin(double power) {
		rightMotor.set(power);
		leftMotor.set(power);
	}

	public void stop() {
		rightMotor.set(-0.3);
		leftMotor.set(-0.3);

	}
}
