package org.usfirst.frc.team5010.robot.commands;

import org.usfirst.frc.team5010.robot.commands.auto.DriveForDistance;
import org.usfirst.frc.team5010.robot.commands.auto.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonScaleScore extends CommandGroup {

    public AutonScaleScore() {
    	addSequential(new RaiseArms());
		addSequential(new DriveForDistance(6));
		addSequential(new ReleaseCubeIntake());
		addSequential(new DriveForDistance(-24));
		addSequential(new LowerArms());
		
    }
}
