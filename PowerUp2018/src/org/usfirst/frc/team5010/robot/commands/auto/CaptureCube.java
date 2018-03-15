package org.usfirst.frc.team5010.robot.commands.auto;

import org.usfirst.frc.team5010.robot.commands.CloseIntake;
import org.usfirst.frc.team5010.robot.commands.OpenIntake;
import org.usfirst.frc.team5010.robot.commands.ReleaseIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CaptureCube extends CommandGroup {

    public CaptureCube() {
        addParallel(new OpenIntake());
        addSequential(new DriveUntilDistance(10, 0));
        addParallel(new CloseIntake());
        addSequential(new ReleaseIntake());
    }
}
