package org.usfirst.frc.team5010.robot.commands;

import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDCommand;

public class ScaleHeight extends PIDCommand {

	public ScaleHeight() {
		super(0.1, 0, 0.2);
        getPIDController().setInputRange(0, 50);
        getPIDController().setOutputRange(0 , 50);
        getPIDController().setAbsoluteTolerance(0.5);
        setSetpoint(50);
        // TODO Adjust for real scale height
	}

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		if (output>0.0) {
			RobotMap.fourbar.setHeight(RobotMap.fourbar.getHeight()+0.1);
		}
		// Note that this is same code in SwitchHeight.java
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
