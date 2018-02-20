package org.usfirst.frc.team5010.robot.subsystems;

import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class FourBarLifter extends Subsystem {
	Potentiometer backpotentMeter;
	Potentiometer frontpotentMeter;

	/**
	 * Default constructor.
	 */
	public FourBarLifter() {
		this.backpotentMeter = RobotMap.lowerRiserPotent;
		this.frontpotentMeter = RobotMap.upperRiserPotent;
		
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	/** setHeight - determine what height to set each of the separate lifters */
	public void setHeight(double height) {
		SmartDashboard.putNumber("FourBarLifter Target Height:", height);
		if(height <= UpperRiser.MAX_HEIGHT) {
			RobotMap.lowerLifter.setHeight(0);
			RobotMap.upperLifter.setHeight(height);
		}else {
			RobotMap.lowerLifter.setHeight(LowerRiser.MAX_HEIGHT);
			RobotMap.upperLifter.setHeight(height - LowerRiser.MAX_HEIGHT);
		}
	}

	/** getHeight - combine the height of both frontLifter and backLifter */
	public double getHeight() {
		double height = RobotMap.upperLifter.getHeight()+RobotMap.lowerLifter.getHeight();
		SmartDashboard.putNumber("FourBarLifter Current Height:", height);
		return height;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());

    }
}

