package org.usfirst.frc.team5010.robot.commands;

import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RaiseArms extends CommandGroup {

    public RaiseArms() {
       addSequential(new SetUpperHeight(true));
       addSequential(new SetLowerHeight(RobotMap.lowerLifter.MAX_ANGLE + (RobotMap.lowerLifter.ANGLE_RANGE * .7)));
       addSequential(new SetUpperHeight(true));
    }
}
