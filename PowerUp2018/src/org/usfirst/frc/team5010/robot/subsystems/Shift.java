package org.usfirst.frc.team5010.robot.subsystems;

import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *@author THE GLORIOUS JACKSON LEE
 *@since 1/29/2018
 */
public class Shift extends Subsystem {
	 DoubleSolenoid EvoShift = null;
	
	public Shift () {
		EvoShift = RobotMap.shift;
	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public void shiftUp() {
		EvoShift.set(DoubleSolenoid.Value.kForward);
	}
	public void shiftDown() {
		EvoShift.set(DoubleSolenoid.Value.kReverse);
	}
	public void stop() {
		EvoShift.set(DoubleSolenoid.Value.kOff);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

