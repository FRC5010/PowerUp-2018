package org.usfirst.frc.team5010.robot.commands;

import org.usfirst.frc.team5010.robot.commands.auto.AutoBackScaleCube;
import org.usfirst.frc.team5010.robot.commands.auto.DriveForDistance;
import org.usfirst.frc.team5010.robot.commands.auto.DriveUntilDistance;
import org.usfirst.frc.team5010.robot.commands.auto.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonScale extends CommandGroup {

    public AutonScale() {
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
//    	addSequential(new DriveForDistance(220));
//    	addSequential(new TurnToAngle(90));
//    	addSequential(new DriveForDistance(200, 90));
//    	addSequential(new DriveUntilDistance(70, 90));
//    	addSequential(new TurnToAngle(180));
//    	addSequential(new DriveUntilDistance(51, 0));
//    	addSequential(new AutoBackScaleCube());
////    	
    	addSequential(new DriveForDistance(30, 0));
    	addSequential(new DriveForDistance(100, 90));
    }
}
