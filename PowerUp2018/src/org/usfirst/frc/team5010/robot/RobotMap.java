/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5010.robot;

import java.io.File;

import org.usfirst.frc.team5010.robot.subsystems.CubeIntake;
//subsystems
import org.usfirst.frc.team5010.robot.subsystems.DirectionSensor;
import org.usfirst.frc.team5010.robot.subsystems.DistanceSensor;
import org.usfirst.frc.team5010.robot.subsystems.DriveTrainMain;
import org.usfirst.frc.team5010.robot.subsystems.FourBarLifter;
import org.usfirst.frc.team5010.robot.subsystems.LowerRiser;
import org.usfirst.frc.team5010.robot.subsystems.Shift;
import org.usfirst.frc.team5010.robot.subsystems.UltrasonicSensor;
import org.usfirst.frc.team5010.robot.subsystems.UpperRiser;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static boolean regenAllTrajectories = false;

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

	public static SpeedController lowerRiserMotor;
	public static SpeedController upperRiserMotor;
	public static SpeedController leftIntakeMotor;
	public static SpeedController rightIntakeMotor;

	public static DoubleSolenoid intake;
	public static Solenoid shiftSolenoid;
	public static CameraServer camera;

	public static Gyro gyro;
	public static Encoder rightEncoder;
	public static Encoder leftEncoder;

	public static AnalogPotentiometer lowerRiserPotent;
	public static AnalogPotentiometer upperRiserPotent;
	public static AnalogInput frontUltrasound;
	public static AnalogInput backUltrasound;

	public static DirectionSensor direction;
	public static DistanceSensor distance;

	public static DriveTrainMain drivetrain;
	public static UltrasonicSensor range;
	public static Shift shift;

	public static UpperRiser upperLifter;
	public static LowerRiser lowerLifter;
	public static FourBarLifter fourbar;
	public static CubeIntake cubeIntake;

	static Trajectory.Config config;
	
	public static Trajectory trajectory;
	public static Trajectory mStartLSwitchTraj;
	public static Trajectory mStartRSwitchTraj;
	public static Trajectory lStartLScaleTraj;
	public static Trajectory lStartLSwitchTraj;

	static Waypoint[] points;
	static Waypoint[] mStartLSwitchPoints;
	static Waypoint[] mStartRSwitchPoints;
	static Waypoint[] lStartLScalePoints;
	static Waypoint[] lStartLSwitchPoints;

	// //Height of grabber above ground = height variables from front and back
	// risers + height of frontbar above ground
	public static double upperarmLength = 29;
	public static double lowerarmLength = 22;
	public static double upperarmheight = 19;
	public static double lowerarmheight = 28.5;
	// public static double baseheight = 12;

	// another motor backwards somewhere
	public static void initPractice() {
		lowerRiserMotor = new Spark(0);
		upperRiserMotor = new Spark(1);
		leftIntakeMotor = new Spark(2);
		rightIntakeMotor = new Spark(3);
	}

	public static void initComp() {
		lowerRiserMotor = new Victor(0);
		upperRiserMotor = new Victor(1);
		leftIntakeMotor = new Victor(2);
		rightIntakeMotor = new Victor(3);
	}

	public static void waypoints() {
		config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 5.45, .4, 60);
		
		points = new Waypoint[] { 
				new Waypoint(0, 0, 0), 
				new Waypoint(3, 3, Pathfinder.d2r(90)),
				new Waypoint(0, 6, Pathfinder.d2r(180)), 
				new Waypoint(-3, 3, Pathfinder.d2r(270)),
				new Waypoint(0, 0, Pathfinder.d2r(0))};

		trajectory = generateTrajectory("test.csv", points, config, false);
		
		mStartLSwitchPoints = new Waypoint[] { 
				new Waypoint(1.58, 14, 0), 
				new Waypoint(10, 18.5, 0)};
				
		mStartLSwitchTraj = generateTrajectory("mStartLSwitchTraj.csv", mStartLSwitchPoints, config, false);
		
		mStartRSwitchPoints = new Waypoint[] {
				new Waypoint(1.58, 14, 0),
				new Waypoint(10, 9.5, 0)};
	
		mStartRSwitchTraj = generateTrajectory("mStartRSwitchTraj.csv", mStartRSwitchPoints, config, false);
		
		lStartLScalePoints = new Waypoint[] {
				new Waypoint(1.58, 23, 0),
				new Waypoint(16, 22, Pathfinder.d2r(-15)),
				new Waypoint(23 ,20.5, 0)};
		
	
		lStartLScaleTraj = generateTrajectory("lStartLScaleTraj.csv", lStartLScalePoints, config, false);
		
		lStartLSwitchPoints = new Waypoint[] {
				new Waypoint(1.58, 23, 0),
				new Waypoint(10, 23.5, Pathfinder.d2r(20)),
				new Waypoint(14 ,21, Pathfinder.d2r(-90))};
		
		lStartLSwitchTraj = generateTrajectory("lStartLSwitchTraj.csv", lStartLSwitchPoints, config, false);
		
		
		
	}

	//returns trajectory given by file name. if none found creates and stores new trajectory.
	public static Trajectory generateTrajectory(String name, Waypoint[] points,
			Trajectory.Config config, boolean regen) {
		Trajectory trajectory;
		File dir = new File("/Trajectories/" + name);
		if (dir.exists() && !regen && !regenAllTrajectories) {
			System.out.println("Reading " + name);
			trajectory = Pathfinder.readFromCSV(dir);
		} else {
			System.out.println("Generating " + name);
			trajectory = Pathfinder.generate(points, config);
			Pathfinder.writeToCSV(dir, trajectory);
		}
		return trajectory;
	}

	public static void init() {
		initPractice();
		waypoints();

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

		// Solenoids
		intake = new DoubleSolenoid(2, 3);
		shiftSolenoid = new Solenoid(1);

		// Sensor components
		gyro = new ADXRS450_Gyro();
		rightEncoder = new Encoder(2, 3);
		leftEncoder = new Encoder(0, 1);

		lowerRiserPotent = new AnalogPotentiometer(0, 270, 0);
		upperRiserPotent = new AnalogPotentiometer(1, 270, 0);

		frontUltrasound = new AnalogInput(2);
		backUltrasound = new AnalogInput(3);

		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(960, 540);
		camera.setFPS(30);

		// subsystems
		range = new UltrasonicSensor();
		direction = new DirectionSensor();
		distance = new DistanceSensor();
		drivetrain = new DriveTrainMain();
		upperLifter = new UpperRiser();
		lowerLifter = new LowerRiser();
		fourbar = new FourBarLifter();
		cubeIntake = new CubeIntake();
		shift = new Shift();

		LowerRiser.calibratePot();
		UpperRiser.calibratePot();

	}
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;

}
