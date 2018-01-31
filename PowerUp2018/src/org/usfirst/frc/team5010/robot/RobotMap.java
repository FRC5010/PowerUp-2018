/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5010.robot;

//subsystems
import org.usfirst.frc.team5010.robot.subsystems.DirectionSensor;
import org.usfirst.frc.team5010.robot.subsystems.DistanceSensor;
import org.usfirst.frc.team5010.robot.subsystems.DriveTrainMain;

//components
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static void smartDashboard() {
		SmartDashboard.putNumber("D", 0.04);
		SmartDashboard.putNumber("I", 0.04);
		SmartDashboard.putNumber("P", 0.06);
		
	}
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	public static SpeedController driveMotorLeft;
	public static SpeedController driveMotorRight;
	public static SpeedController driveMotor2017Left;
	public static SpeedController driveMotor2017Right;
	public static SpeedController driveMotor2018Left;
	public static SpeedController driveMotor2018Right;
	public static SpeedController backriser;
	public static SpeedController intakeMotorLeft;
	public static SpeedController intakeMotorRight;
	public static Gyro gyro;
	public static  Encoder encoder;
	public static DirectionSensor direction;
	public static DistanceSensor distance;
	public static DriveTrainMain drivetrain;
	public static SpeedController frontriser;
	public static AnalogPotentiometer backRiserPot;
	public static AnalogPotentiometer frontRiserPot;
	public static double armLength = 35;
	//Make sure front and back arms are same length, or change above code
	
	public static void init() {
		//components
		
		driveMotor2018Left = new Victor(0);
		driveMotor2018Right = new Victor(1);
		backriser = new Victor(9);
		intakeMotorLeft = new Victor(7);
		intakeMotorRight = new Victor(8);
		frontriser = new Victor(6);
		
		frontRiserPot = new AnalogPotentiometer(0);
		backRiserPot = new AnalogPotentiometer(1);
		
		driveMotorLeft = driveMotor2018Left;
		driveMotorRight = driveMotor2018Right;
		gyro = new ADXRS450_Gyro();
		encoder = new Encoder(0, 1);
		
		//subsystems
		direction = new DirectionSensor();
		distance = new DistanceSensor();
		drivetrain = new DriveTrainMain();
		
	}
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;

	
}
