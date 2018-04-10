/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5010.robot;

import java.io.File;

import org.usfirst.frc.team5010.robot.jetson_autonomous.ImageDataIO;
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
import org.usfirst.frc.team5010.robot.subsystems.Vision;

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
	public static int encoderPPR;
	
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
	public static Vision vision;
	public static ImageDataIO visionIO;
	
	static Trajectory.Config config;
	
	public static Trajectory trajectory;
	
	public static Trajectory autoLineTraj;
	
	//middles start
	public static Trajectory mStartLSwitchTraj;
	public static Trajectory mStartRSwitchTraj;
	
	//left start
	public static Trajectory lStartLScaleTraj;
	public static Trajectory lStartRScaleTraj1;
	public static Trajectory lStartRScaleTraj2;
	public static Trajectory lStartLSwitchTraj;
	public static Trajectory LSwitchSidetoLSwitchCubeTraj1;
	public static Trajectory LSwitchSidetoLSwitchCubeTraj2;
	public static Trajectory LSwitchSidetoLSwitchCubeTraj3;
	
	//right start
	public static Trajectory rStartRScaleTraj;
	public static Trajectory rStartLScaleTraj1;
	public static Trajectory rStartLScaleTraj2;
	public static Trajectory rStartRSwitchTraj;
	
	
	static Waypoint[] points;
	
	static Waypoint[] autoLinePoints;
	
	//middle start
	static Waypoint[] mStartLSwitchPoints;
	static Waypoint[] mStartRSwitchPoints;
	
	//left start
	static Waypoint[] lStartLScalePoints;
	static Waypoint[] lStartRScalePoints1;
	static Waypoint[] lStartRScalePoints2;
	static Waypoint[] lStartLSwitchPoints;
	static Waypoint[] LSwitchSidetoLSwitchCubePoints1;
	static Waypoint[] LSwitchSidetoLSwitchCubePoints2;
	static Waypoint[] LSwitchSidetoLSwitchCubePoints3;
	
	//right start
	static Waypoint[] rStartRScalePoints;
	static Waypoint[] rStartLScalePoints1;
	static Waypoint[] rStartLScalePoints2;
	static Waypoint[] rStartRSwitchPoints;
	
	
	// //Height of grabber above ground = height variables from front and back
	// risers + height of frontbar above ground
	public static double upperarmLength = 29;
	public static double lowerarmLength = 22;
	public static double upperarmheight = 19;
	public static double lowerarmheight = 28.5;
	// public static double baseheight = 12;

	
	
	public static void initPractice() {
		lowerRiserMotor = new Spark(0);
		upperRiserMotor = new Spark(1);
		leftIntakeMotor = new Spark(2);
		rightIntakeMotor = new Spark(3);
		
		rightEncoder = new Encoder(0, 1);  //0, 1
		leftEncoder = new Encoder(2, 3);   // 2, 3
		
		encoderPPR = 20;
	}
	
	public static void initComp() {
		
		lowerRiserMotor = new Victor(0);
		upperRiserMotor = new Victor(1);
		leftIntakeMotor = new Victor(2);
		rightIntakeMotor = new Victor(3);
		lowerRiserMotor.setInverted(true);
		
		rightEncoder = new Encoder(0, 1);
		leftEncoder = new Encoder(2, 3);
		
		rightEncoder.setReverseDirection(true);
		leftEncoder.setReverseDirection(true);
		
		encoderPPR = 480;
		
	}

	public static void waypoints() {
		config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 5.45, .6, 60);
		
		//INDEPENDENT OF START TRAJECTORIES
		points = new Waypoint[] { 
				new Waypoint(0, 0, 0), 
				new Waypoint(3, 3, Pathfinder.d2r(90)),
				new Waypoint(0, 6, Pathfinder.d2r(180)), 
				new Waypoint(-3, 3, Pathfinder.d2r(270)),
				new Waypoint(0, 0, Pathfinder.d2r(0))};
		trajectory = generateTrajectory("test.csv", points, config, false);
		
		autoLinePoints = new Waypoint[] {
				new Waypoint(0,0,0),
				new Waypoint(15, 0, 0)};
		autoLineTraj = generateTrajectory("autoLinePoints.csv", autoLinePoints, config, false);
		
		//MIDDLE START TRAJECTORIES
		
		
		mStartLSwitchPoints = new Waypoint[] { 
				new Waypoint(1.58, 12, 0), 
				new Waypoint(10.1, 18.5, 0)};
		mStartLSwitchTraj = generateTrajectory("mStartLSwitchTraj.csv", mStartLSwitchPoints, config, false);
		
		mStartRSwitchPoints = new Waypoint[] {
				new Waypoint(1.58, 12, 0),
				new Waypoint(10.1, 9.5, 0)};
		mStartRSwitchTraj = generateTrajectory("mStartRSwitchTraj.csv", mStartRSwitchPoints, config, false);
		
		
		//LEFT START TRAJECTORIES
		
		//90 d
		lStartLScalePoints = new Waypoint[] {
				new Waypoint(1.58, 23.5, 0),
				new Waypoint(12, 23.5, 0),
				new Waypoint(23 ,25, 0),
				new Waypoint(27 ,23, Pathfinder.d2r(-90))};
		lStartLScaleTraj = generateTrajectory("lStartLScaleTraj.csv", lStartLScalePoints, config, false);
		
		//s curve
//		lStartLScalePoints = new Waypoint[] {
//				new Waypoint(1.58, 23.5, 0),
//				new Waypoint(16.5, 22, Pathfinder.d2r(-15)),
//				new Waypoint(22.7 ,20.5, 0)};
//		lStartLScaleTraj = generateTrajectory("lStartLScaleTraj.csv", lStartLScalePoints, config, true);
//		
		//angle aproach
//		lStartLScalePoints = new Waypoint[] {
//				new Waypoint(1.58, 23.5, 0),
//				new Waypoint(10, 23.5, 0),
//				new Waypoint(20 ,24, Pathfinder.d2r(-45)),
//				new Waypoint(23.5 ,22, Pathfinder.d2r(-45))};
//		lStartLScaleTraj = generateTrajectory("lStartLScaleTraj.csv", lStartLScalePoints, config, true);
//		
		
		
		lStartRScalePoints1 = new Waypoint[] {
				new Waypoint(1.58, 23.5, 0),
				new Waypoint(12, 23.5, 0),
				new Waypoint(17.25, 12, Pathfinder.d2r(90))};
		
		lStartRScaleTraj1 = generateTrajectory("lStartRScaleTraj1.csv", lStartRScalePoints1, config, false);
		
		lStartRScalePoints2 = new Waypoint[] {
				new Waypoint(17.25, 12, Pathfinder.d2r(90)),
				new Waypoint(20.75, 6.5, 0)};
				
		lStartRScaleTraj2 = generateTrajectory("lStartRScaleTraj2.csv", lStartRScalePoints2, config, false);
		
		
		lStartLSwitchPoints = new Waypoint[] {
				new Waypoint(1.58, 23.5, 0),
				new Waypoint(10, 24, 0),
				new Waypoint(13 ,21.6, Pathfinder.d2r(270)),
				new Waypoint(13 ,20.8, Pathfinder.d2r(270))};
		
		lStartLSwitchTraj = generateTrajectory("lStartLSwitchTraj.csv", lStartLSwitchPoints, config, false);
		
		//forward
		LSwitchSidetoLSwitchCubePoints1 = new Waypoint[] {
				new Waypoint(13, 21, Pathfinder.d2r(270)),
				new Waypoint(10, 23.5, Pathfinder.d2r(180))};
		
		LSwitchSidetoLSwitchCubeTraj1 = generateTrajectory("LSwitchSidetoLSwitchCubeTraj1.csv", LSwitchSidetoLSwitchCubePoints1, config, false);
		
		//reverse
		LSwitchSidetoLSwitchCubePoints2 = new Waypoint[] {
				new Waypoint(10, 23.5, Pathfinder.d2r(180)),
				new Waypoint(23.50, 20.5, 0)};
		
		LSwitchSidetoLSwitchCubeTraj2 = generateTrajectory("LSwitchSidetoLSwitchCubeTraj2.csv", LSwitchSidetoLSwitchCubePoints2, config, false);
		
		//forward
		LSwitchSidetoLSwitchCubePoints3 = new Waypoint[] {
				new Waypoint(23, 20.5, 0),
				new Waypoint(19.5, 19.5, 0)};
		
		LSwitchSidetoLSwitchCubeTraj3 = generateTrajectory("LSwitchSidetoLSwistchCubeTraj3.csv", LSwitchSidetoLSwitchCubePoints3, config, false);
		
		
		//RIGHT START TRAJECTORIES
		
//		rStartRScalePoints = new Waypoint[] {
//				new Waypoint(1.58, 4.00, 0),
//				new Waypoint(16, 5.5, Pathfinder.d2r(15)),
//				new Waypoint(22.3 ,7.5, 0)};
//		rStartRScaleTraj = generateTrajectory("rStartRScaleTraj.csv", rStartRScalePoints, config, false);
		
		rStartRScalePoints = new Waypoint[] {
				new Waypoint(1.58, 3.5, 0),
				new Waypoint(12, 3.5, 0),
				new Waypoint(23 ,2, 0),
				new Waypoint(27 ,4.5, Pathfinder.d2r(90))};
		rStartRScaleTraj = generateTrajectory("rStartRScaleTraj.csv", rStartRScalePoints, config, false);
		
		
		rStartLScalePoints1 = new Waypoint[] {
				new Waypoint(1.58, 3.5, 0),
				new Waypoint(12, 3.5, 0),
				new Waypoint(17.25, 15, Pathfinder.d2r(-90))};		
		rStartLScaleTraj1 = generateTrajectory("rStartLScaleTraj1.csv", rStartLScalePoints1, config, false);
		
		rStartLScalePoints2 = new Waypoint[] {
				new Waypoint(17.25, 15, Pathfinder.d2r(-90)),
				new Waypoint(20.75, 21.75, 0)};
		rStartLScaleTraj2 = generateTrajectory("rStartLScaleTraj2.csv", rStartLScalePoints2, config, false);
		
		rStartRSwitchPoints = new Waypoint[] {
				new Waypoint(1.58, 3.5, 0),
				new Waypoint(10, 3, 0),
				new Waypoint(13, 5.4, Pathfinder.d2r(270)),
				new Waypoint(13, 6.2, Pathfinder.d2r(270))};
		rStartRSwitchTraj = generateTrajectory("rStartRSwitchTraj.csv", rStartRSwitchPoints, config, false);
		
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
		
		rightIntakeMotor.setInverted(true);
		leftIntakeMotor.setInverted(true);

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
		intake = new DoubleSolenoid(3, 2);
		shiftSolenoid = new Solenoid(1);

		// Sensor components
		gyro = new ADXRS450_Gyro();
		

		lowerRiserPotent = new AnalogPotentiometer(0, 270, 0);
		upperRiserPotent = new AnalogPotentiometer(1, 270, 0);

		frontUltrasound = new AnalogInput(2);
		backUltrasound = new AnalogInput(3);

		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(470, 270);
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
		
//		visionIO = new ImageDataIO();
//		Thread t = new Thread(visionIO);
//		t.start();
		
		LowerRiser.calibratePot();
		UpperRiser.calibratePot();

	}
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;

}
