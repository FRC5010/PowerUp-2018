package org.usfirst.frc.team5010.robot.commands.auto;

import org.usfirst.frc.team5010.robot.RobotMap;
import org.usfirst.frc.team5010.robot.commands.LowerArms;
import org.usfirst.frc.team5010.robot.commands.PathReverse;
import org.usfirst.frc.team5010.robot.commands.RaiseArms;
import org.usfirst.frc.team5010.robot.commands.SetUpperHeight;

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
				addSequential(new RaiseArms());
				addSequential(new LowerArms());
			} else if (gameData.charAt(1) == 'R') {
				if(objective.compareTo("cross") == 0) {
					SmartDashboard.putString("Auto Selection", "Left Start; Auto Line");
					addSequential(new DriveForDistance(120));
				}else if(objective.compareTo("switch") == 0) {
					SmartDashboard.putString("Auto Selection", "Left Start; Left Switch");
					addParallel(new SetUpperHeight(true));
					addSequential(new PathReverse(RobotMap.lStartLSwitchTraj));
					addSequential(new SetUpperHeight(true));
				}

			} else {
				SmartDashboard.putString("Auto Selection", "Left Start; Bad game data");
			}
		} else if (start.compareTo("Middle") == 0) {
			if (gameData.charAt(0) == 'L') {
				SmartDashboard.putString("Auto Selection", "Middle Start; Left Switch");
				addParallel(new SetUpperHeight(true));
				addSequential(new PathReverse(RobotMap.mStartLSwitchTraj));
				addSequential(new SetUpperHeight(true));
			
			} else if (gameData.charAt(0) == 'R') {
				addParallel(new SetUpperHeight(true));
				addSequential(new PathReverse(RobotMap.mStartRSwitchTraj));
				addSequential(new SetUpperHeight(true));
			
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
