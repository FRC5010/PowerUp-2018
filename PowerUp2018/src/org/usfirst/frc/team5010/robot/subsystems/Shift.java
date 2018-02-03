package org.usfirst.frc.team5010.robot.subsystems;

import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

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
		EvoShift.set(true);
	}
	public void shiftDown() {
		EvoShift.set(false);
	}
	public void stop() {
		EvoShift.set(false);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

