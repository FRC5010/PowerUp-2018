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
		if (gameData.charAt(0) == 'L') {
			turn = new TurnToAngle(-45);
			turn2 = new TurnToAngle(0);
			// Put left auto code here
		} else {
			// Put right auto code here
			turn = new TurnToAngle(45);
			turn2 = new TurnToAngle(0);
		}
		DriveForDistance driveForward = new DriveForDistance();
		driveForward.setPoint(30);

		DriveForDistance driveForward2 = new DriveForDistance();
		driveForward2.setPoint(68);

		DriveUntilDistance driveForward3 = new DriveUntilDistance();
		driveForward3.setPoint(7);

		addSequential(driveForward);
		addSequential(turn);
		addSequential(driveForward2);
		addSequential(turn2);
		addSequential(driveForward3);
	}
}
