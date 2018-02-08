package org.usfirst.frc.team5010.robot.commands.auto;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class FieldMovement extends CommandGroup {
	public FieldMovement() {
		TurnToAngle turn2;
		TurnToAngle turn;
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		TurnToAngle turnRight = new TurnToAngle(90);
		turnRight.setSetpointRelative(90);

		TurnToAngle turnLeft = new TurnToAngle(270);
		turnRight.setSetpointRelative(270);
		
		DriveForDistance driveForward = new DriveForDistance();
		driveForward.setPoint(30);

		DriveForDistance driveForward2 = new DriveForDistance();
		driveForward2.setPoint(68);

		DriveUntilDistance driveForward3 = new DriveUntilDistance();
		driveForward3.setPoint(7);
		
		Stop stop1 = new Stop();

		if (gameData.charAt(0) == 'L') {
			driveForward.setAngle(0);
			driveForward3.setPoint(30);
			//Assuming left is negative
			driveForward.setAngle(-90);
			driveForward.setPoint(100);
			driveForward.setAngle(0);
			driveForward.setPoint(30);
			// Put left auto code here
		} else {
			// Put right auto code here
			driveForward.setAngle(0);
			driveForward3.setPoint(30);
			//Assuming left is negative
			driveForward.setAngle(90);
			driveForward.setPoint(100);
			driveForward.setAngle(0);
			driveForward.setPoint(30);
		}
		
		addSequential(driveForward);
		addSequential(driveForward2);
		//addSequential(driveForward3);
		addSequential(stop1);

	}
}
