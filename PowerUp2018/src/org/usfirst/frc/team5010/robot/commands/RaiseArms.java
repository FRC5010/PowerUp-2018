package org.usfirst.frc.team5010.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RaiseArms extends CommandGroup {

    public RaiseArms() {
       addSequential(new SetUpperHeight(true));
       addSequential(new SetLowerHeight(true));
       addSequential(new SetUpperHeight(true));
    }
}
