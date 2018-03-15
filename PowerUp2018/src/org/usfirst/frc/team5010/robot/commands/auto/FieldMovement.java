package org.usfirst.frc.team5010.robot.commands.auto;

import org.usfirst.frc.team5010.robot.RobotMap;
import org.usfirst.frc.team5010.robot.commands.PathReverse;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class FieldMovement extends CommandGroup {
	public FieldMovement(String start, String objective) {
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if (gameData != null && gameData.isEmpty() && gameData.length() != 3) {
			SmartDashboard.putString("Auto Selection", "No game data");
			return;
		}

		if (start.compareTo("Left") == 0) {
			if (gameData.charAt(1) == 'L') {
				SmartDashboard.putString("Auto Selection", "Left Start; Left Scale");
				addSequential(new PathReverse(RobotMap.lStartLScaleTraj));
			} else if (gameData.charAt(1) == 'R') {
				if(objective.compareTo("cross") == 0) {
					SmartDashboard.putString("Auto Selection", "Left Start; Auto Line");
					addSequential(new DriveForDistance(120));
				}else if(objective.compareTo("switch") == 0) {
					SmartDashboard.putString("Auto Selection", "Left Start; Left Switch");
					addSequential(new PathReverse(RobotMap.lStartLSwitchTraj));
				}

			} else {
				SmartDashboard.putString("Auto Selection", "Left Start; Bad game data");
			}
		} else if (start.compareTo("Middle") == 0) {
			if (gameData.charAt(0) == 'L') {
				SmartDashboard.putString("Auto Selection", "Middle Start; Left Switch");
				addSequential(new PathReverse(RobotMap.mStartLSwitchTraj));

			} else if (gameData.charAt(0) == 'R') {
				addSequential(new PathReverse(RobotMap.mStartRSwitchTraj));
				SmartDashboard.putString("Auto Selection", "Middle Start; Right Switch");

			} else {
				SmartDashboard.putString("Auto Selection", "Middle Start; Bad game data");
			}

		} else if (start.compareTo("Right") == 0) {
			if (gameData.charAt(1) == 'L') {
				addSequential(new DriveForDistance(120));
				SmartDashboard.putString("Auto Selection", "Auto Line");
			} else if (gameData.charAt(1) == 'R') {

			} else {
				SmartDashboard.putString("Auto Selection", "Right Start; Bad game data");
			}
		} else {
			SmartDashboard.putString("Auto Selection", "Bad sendable chooser");
		}

	}
}
