package org.usfirst.frc.team5010.robot.subsystems;

import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class FourBar extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public void setHeight(double height) {
		RobotMap.frontLifter.setHeight(height/2);
		RobotMap.backLifter.setHeight(height/2);
	}

	// TODO: getHeight - combine the height of both frontLifter and backLifter
	public double getHeight() {
		double potValue = RobotMap.frontLifter.getHeight()+RobotMap.backLifter.getHeight();
		return potValue;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

