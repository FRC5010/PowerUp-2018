package org.usfirst.frc.team5010.robot.subsystems;

import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DistanceSensor extends Subsystem {

	public DistanceSensor() {
		RobotMap.leftEncoder.reset();
	}

	public DistanceSensor(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	public double getDistance() {
		return RobotMap.leftEncoder.getDistance();
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
