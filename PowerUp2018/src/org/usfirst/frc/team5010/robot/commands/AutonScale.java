package org.usfirst.frc.team5010.robot.commands;

import org.usfirst.frc.team5010.robot.commands.auto.DriveForDistance;
import org.usfirst.frc.team5010.robot.commands.auto.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonScale extends CommandGroup {

    public AutonScale() {
        
    	addSequential(new ResetGyro());
    	addParallel(new ReleaseIntake());
    	addSequential(new DriveForDistance(164, 0));
    	addSequential(new TurnToAngle(90,2.5));
    	addSequential(new DriveForDistance(100, 90));
    	
//    	addSequential(new DriveForDistance(200));
//    	
//    	addSequential(new DriveForDistance(30, 30));
//    	addSequential(new DriveForDistance(30, 60));
//    	addSequential(new DriveForDistance(30, 90));
// 
//    	addSequential(new DriveForDistance(120, 90));
//    	//addSequential(new ShiftDown());
//    	addSequential(new DriveUntilDistance(75, 90));
//    	//addSequential(new TurnToAngle(180));
//    	addSequential(new DriveUntilDistance(50, 0));
//    	addSequential(new AutoBackScaleCube());
//   	
//    	addSequential(new DriveForDistance(30, 0));
//    	addSequential(new DriveForDistance(100, 90));
    }
}
