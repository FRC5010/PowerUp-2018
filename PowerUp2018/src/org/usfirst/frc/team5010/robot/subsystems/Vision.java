package org.usfirst.frc.team5010.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;

/*
 *
 */

public class Vision extends Subsystem {

	NetworkTableInstance inst;
	NetworkTable table;

	double boxX;

	public Vision() {
		inst = NetworkTableInstance.getDefault();
		table = inst.getTable("table");
	}

	public double getBoxX() {
		table = inst.getTable("table");
		return table.getEntry("BoxX").getDouble(0) * 2 - 1;
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
