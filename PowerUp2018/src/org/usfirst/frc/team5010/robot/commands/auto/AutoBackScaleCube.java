package org.usfirst.frc.team5010.robot.commands.auto;

import org.usfirst.frc.team5010.robot.commands.SetLowerHeight;
import org.usfirst.frc.team5010.robot.commands.SetUpperHeight;
import org.usfirst.frc.team5010.robot.subsystems.LowerRiser;
import org.usfirst.frc.team5010.robot.subsystems.UpperRiser;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoBackScaleCube extends CommandGroup {

    public AutoBackScaleCube() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	addSequential(new SetUpperHeight(30));
    	addSequential(new SetLowerHeight(LowerRiser.MAX_HEIGHT - 6));
    	addSequential(new SetUpperHeight(UpperRiser.MAX_HEIGHT));
    	addSequential(new SetLowerHeight(LowerRiser.MIN_HEIGHT));
    	addSequential(new SetUpperHeight(UpperRiser.MIN_HEIGHT));
    }
}
