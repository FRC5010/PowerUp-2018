package org.usfirst.frc.team5010.robot.subsystems;

import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class FourBarLifter extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	/** setHeight - determine what height to set each of the separate lifters */
	public void setHeight(double height) {
		SmartDashboard.putNumber("FourBarLifter Target Height:", height);
		RobotMap.frontLifter.setHeight(height/2);
		RobotMap.backLifter.setHeight(height/2);
	}

	/** getHeight - combine the height of both frontLifter and backLifter */
	public double getHeight() {
		double height = RobotMap.frontLifter.getHeight()+RobotMap.backLifter.getHeight();
		SmartDashboard.putNumber("FourBarLifter Current Height:", height);
		return height;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

