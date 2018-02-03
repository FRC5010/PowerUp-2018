/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5010.robot;

import org.usfirst.frc.team5010.robot.subsystems.BackRiser;
import org.usfirst.frc.team5010.robot.subsystems.CubeIntake;
//subsystems
import org.usfirst.frc.team5010.robot.subsystems.DirectionSensor;
import org.usfirst.frc.team5010.robot.subsystems.DistanceSensor;
import org.usfirst.frc.team5010.robot.subsystems.DriveTrainMain;
import org.usfirst.frc.team5010.robot.subsystems.FourBar;
import org.usfirst.frc.team5010.robot.subsystems.FrontRiser;
import org.usfirst.frc.team5010.robot.subsystems.Shift;
import org.usfirst.frc.team5010.robot.subsystems.UltrasonicSensor;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

//components
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
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
	
	public static TalonSRX rightMotor1;
	public static TalonSRX rightMotor2;
	public static TalonSRX rightMotor3;
	public static TalonSRX leftMotor1; 
	public static TalonSRX leftMotor2;
	public static TalonSRX leftMotor3;
	
	public static SpeedController driveMotorLeft;
	public static SpeedController driveMotorRight;
	public static SpeedController driveMotor2017Left;
	public static SpeedController driveMotor2017Right;
	public static SpeedController driveMotor2018Left;
	public static SpeedController driveMotor2018Right;
	public static  AnalogInput ultrasound;
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
	public static UltrasonicSensor range;
	public static Shift shift;
	public static Solenoid intake;
	public static FrontRiser frontLifter;
	public static BackRiser backLifter;
	public static FourBar fourbar;
	public static CubeIntake cubeIntake;
	
	//Make sure front and back arms are same length, or change above code
	public static void init() {
		// Drive Train components
		rightMotor1 = new TalonSRX(4);
		rightMotor2 = new TalonSRX(5);
		rightMotor3 = new TalonSRX(6);
		leftMotor1 = new TalonSRX(1);
		leftMotor2 = new TalonSRX(2);
		leftMotor3 = new TalonSRX(3);
		
		rightMotor2.set(com.ctre.phoenix.motorcontrol.ControlMode.Follower, 4);
		rightMotor3.set(com.ctre.phoenix.motorcontrol.ControlMode.Follower, 4);
		
		leftMotor2.set(com.ctre.phoenix.motorcontrol.ControlMode.Follower, 1);
		leftMotor3.set(com.ctre.phoenix.motorcontrol.ControlMode.Follower, 1);
		
//		driveMotor2018Left = new Victor(0);
//		driveMotor2018Right = new Victor(1);
		
		// Set this to the correct drive motor
		driveMotorLeft = driveMotor2018Left;
		driveMotorRight = driveMotor2018Right;
		
		// Lifter motors
		backriser = new Victor(0);
		frontriser = new Victor(1);

		intakeMotorLeft = new Victor(7);
		intakeMotorRight = new Victor(8);

		// Solenoids
		intake = new Solenoid(2);		
		
		//Sensor components
		gyro = new ADXRS450_Gyro();
		encoder = new Encoder(0, 1);
		ultrasound = new AnalogInput(0); 
		frontRiserPot = new AnalogPotentiometer(1);
		backRiserPot = new AnalogPotentiometer(2);
		
		//subsystems
		range = new UltrasonicSensor();
		direction = new DirectionSensor();
		distance = new DistanceSensor();
		drivetrain = new DriveTrainMain();
		frontLifter = new FrontRiser("front");
		backLifter = new BackRiser("back");
		fourbar = new FourBar();
		cubeIntake = new CubeIntake();
	}
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;

	
}
