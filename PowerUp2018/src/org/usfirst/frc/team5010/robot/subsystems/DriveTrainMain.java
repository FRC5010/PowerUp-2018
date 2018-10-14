package org.usfirst.frc.team5010.robot.subsystems;

import org.usfirst.frc.team5010.robot.RobotMap;
import org.usfirst.frc.team5010.robot.commands.TeleopDefault;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrainMain extends Subsystem {

//	SpeedController rightMotor = RobotMap.driveMotorRight;
//	SpeedController leftMotor = RobotMap.driveMotorLeft;
	
	TalonSRX rightMotors = RobotMap.rightMotor1;
	TalonSRX leftMotors = RobotMap.leftMotor1;
	

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new TeleopDefault());
	}

	public void drive(double leftPower, double rightPower) {
//		rightMotor.set(-rightPower);
//		leftMotor.set(leftPower);
		
		SmartDashboard.putNumber("right encoder getRaw", RobotMap.rightEncoder.getRaw());
		SmartDashboard.putNumber("left encoder getRaw", -RobotMap.leftEncoder.getRaw());
		leftMotors.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, leftPower);
		rightMotors.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, -rightPower);
		
	}

	public void spin(double power) {
//		rightMotor.set(power);
//		leftMotor.set(power);
		
		leftMotors.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, power);
		rightMotors.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, power);
	}

	public void stop() {
//		rightMotor.set(-0.3);
//		leftMotor.set(-0.3);
		
		leftMotors.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);
		rightMotors.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0);

	}
}
