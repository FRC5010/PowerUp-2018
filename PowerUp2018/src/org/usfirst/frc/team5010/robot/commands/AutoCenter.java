package org.usfirst.frc.team5010.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCenter extends CommandGroup {

	public AutoCenter() {
		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Cow ommand2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
		TurnToAngle turn2;
		TurnToAngle turn;
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		DriveForDistance driveForward = new DriveForDistance();
		driveForward.setPoint(30);

		DriveForDistance driveForward2 = new DriveForDistance();
		driveForward2.setPoint(68);

		DriveUntilDistance driveForward3 = new DriveUntilDistance();
		driveForward3.setPoint(7);
		
		Stop stop1 = new Stop();

		if (gameData.charAt(0) == 'L') {
			driveForward2.setAngle(-45);
			driveForward3.setAngle(0);
			// Put left auto code here
		} else {
			// Put right auto code here
			driveForward2.setAngle(45);
			driveForward3.setAngle(0);
		}
		
		addSequential(driveForward);
		addSequential(driveForward2);
		//addSequential(driveForward3);
		addSequential(stop1);
	}
}
