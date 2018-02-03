package org.usfirst.frc.team5010.robot.commands.auto;

import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDCommand;

public class SwitchHeight extends PIDCommand {

	public SwitchHeight() {
		super(0.1, 0, 0.2);
        getPIDController().setInputRange(0, 50);
        getPIDController().setOutputRange(0 , 50);
        getPIDController().setAbsoluteTolerance(0.5);
        setSetpoint(20);
        // TODO Adjust for real switch height
	}

	public void initialize() {
		
	}
	
	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return RobotMap.fourbar.getHeight();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		if (output>0.0) {
			RobotMap.fourbar.setHeight(RobotMap.fourbar.getHeight()+0.1);
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
