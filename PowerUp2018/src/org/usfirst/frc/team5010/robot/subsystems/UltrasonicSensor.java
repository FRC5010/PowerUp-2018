package org.usfirst.frc.team5010.robot.subsystems;

import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class UltrasonicSensor extends Subsystem {
	public final AnalogInput frontUltrasound;
	public final AnalogInput backUltrasound;
	
	public UltrasonicSensor() {
		this.frontUltrasound = RobotMap.frontUltrasound;
		this.backUltrasound = RobotMap.backUltrasound;
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
    public void initDefaultCommand() {
        //Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public double getFrontUltrasound() {
    	double value = frontUltrasound.getValue();
    	SmartDashboard.putNumber("front ultrasonic range", value);
    	return value;
    }
    
    public double getFrontDistance() {
    	double value = frontUltrasound.getValue()/8.425 ;
 //   	SmartDashboard.putNumber("front distance", value);
    	return value;
    }
    
    public double getBackUltrasound() {
    	double value = backUltrasound.getValue();
    	SmartDashboard.putNumber("back ultrasonic range", value);
    	return value;
    }
    
    public double getBackDistance() {
    	double value = backUltrasound.getValue()/7.583;
 //   	SmartDashboard.putNumber("back distance", value);
    	return value;
    }
}

