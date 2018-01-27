package org.usfirst.frc.team5010.robot.subsystems;

import org.usfirst.frc.team5010.robot.Robot;
import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Solenoid;


public class CubeIntake extends Subsystem {
	private Solenoid IntakePiston = null;
	private double deadZone = .15;
	public CubeIntake() {
		// TODO Auto-generated constructor stub
		IntakePiston = new Solenoid(0);
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	public void devourCube() {
		if(Robot.oi.joyCODriver.getRawAxis(2)>0) {
			RobotMap.intakeMotorLeft.set(scaleInputs(Robot.oi.joyCODriver.getRawAxis(2)));
			RobotMap.intakeMotorRight.set(-scaleInputs(Robot.oi.joyCODriver.getRawAxis(2)));
		}
	}
	
	public void spitCube() {
		if(Robot.oi.joyCODriver.getRawAxis(3)>0) {
			RobotMap.intakeMotorLeft.set(-scaleInputs(Robot.oi.joyCODriver.getRawAxis(3)));
			RobotMap.intakeMotorRight.set(scaleInputs(Robot.oi.joyCODriver.getRawAxis(3)));
		}		
	}
	
	public double scaleInputs(double input){
	    if(Math.abs(input) < deadZone){
	    	input = 0;
	    }
	    else if (input > 0){
			input = (input - deadZone) * 1/(1 - deadZone);
		}else if (input < 0){
			input = (input + deadZone) * 1/(1 - deadZone);	
		}
		
		return input;
	}
	
		// TODO uncomment GearHolderPiston code when Pneumatic Control available.

		public void closeIntake() {
			// TODO uncomment GearHolderPiston code when Pneumatic Control available.
			IntakePiston.set(false);
			System.out.println("Intake is closing");
		}

		public void openIntake() {
			// TODO uncomment GearHolderPiston code when Pneumatic Control available.
			IntakePiston.set(true);
			System.out.println("Intake is opening");
		}

		public void stop() {
			IntakePiston.set(false);
		}
	}
