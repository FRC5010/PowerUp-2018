package org.usfirst.frc.team5010.robot.subsystems;

import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *@author THE GLORIOUS JACKSON LEE
 *@since 1/29/2018
 */
public class Shift extends Subsystem {
	 Solenoid EvoShift = null;
	
	public Shift () {
		EvoShift = RobotMap.shiftSolenoid;
	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public void shiftUp() {
		SmartDashboard.setDefaultBoolean("High Gear Active", true);
		EvoShift.set(false);
	}
	public void shiftDown() {
		SmartDashboard.setDefaultBoolean("High Gear Active", false);
		EvoShift.set(true);
	}
	public void stop() {
		SmartDashboard.setDefaultBoolean("High Gear Active", false);
		EvoShift.set(false);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

