package org.usfirst.frc.team5010.robot.commands.auto;

import org.usfirst.frc.team5010.robot.RobotMap;
import org.usfirst.frc.team5010.robot.commands.LowerArms;
import org.usfirst.frc.team5010.robot.commands.PathForward;
import org.usfirst.frc.team5010.robot.commands.PathReverse;
import org.usfirst.frc.team5010.robot.commands.RaiseArms;
import org.usfirst.frc.team5010.robot.commands.ReleaseIntake;
import org.usfirst.frc.team5010.robot.commands.SetUpperHeight;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class FieldMovement extends CommandGroup {
	public FieldMovement(String start, String objective) {
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		SmartDashboard.putString("Game data", gameData);
		if (gameData != null && gameData.isEmpty() && gameData.length() != 3) {
			SmartDashboard.putString("Auto Selection", "No game data");
			return;
		}

		addParallel(new ReleaseIntake());

		if (start.compareTo("Left") == 0) {
			if (gameData.charAt(1) == 'L') {
				SmartDashboard.putString("Auto Selection", "Left Start; Left Scale");
				addSequential(new PathReverse(RobotMap.lStartLScaleTraj));
				addParallel(new TurnToAngle(90));
				addSequential(new RaiseArms());
				addSequential(new LowerArms());
				
			} else if (gameData.charAt(1) == 'R') {
				if (objective.compareTo("cross") == 0) {
					SmartDashboard.putString("Auto Selection", "Left Start; Right Scale");
					addSequential(new PathReverse(RobotMap.lStartRScaleTraj1));
					addSequential(new PathReverse(RobotMap.lStartRScaleTraj2));
					addParallel(new TurnToAngle(0));
					addSequential(new RaiseArms());
					addSequential(new LowerArms());

				} else if (objective.compareTo("switch") == 0) {
					if (gameData.charAt(0) == 'L') {
						SmartDashboard.putString("Auto Selection", "Left Start; Left Switch");
						addSequential(new PathReverse(RobotMap.lStartLSwitchTraj));
						addParallel(new TurnToAngle(90));
						addSequential(new SetUpperHeight(true));
						addSequential(new SetUpperHeight(true));
						addParallel(new SetUpperHeight(false));
						// addSequential(new PathForward(RobotMap.LSwitchSidetoLSwitchCubeTraj1));
						// addSequential(new PathReverse(RobotMap.LSwitchSidetoLSwitchCubeTraj2));
						// addSequential(new PathForward(RobotMap.LSwitchSidetoLSwitchCubeTraj3));
						// addSequential(new CaptureCube());
					} else if(gameData.charAt(0) == 'R'){
						SmartDashboard.putString("Auto Selection", "Left Start; Auto Line");
						addSequential(new PathReverse(RobotMap.autoLineTraj));
					}
				}

			} else {
				SmartDashboard.putString("Auto Selection", "Left Start; Bad game data");
			}
		} else if (start.compareTo("Middle") == 0) {
			if (gameData.charAt(0) == 'L') {
				SmartDashboard.putString("Auto Selection", "Middle Start; Left Switch");
				addSequential(new PathReverse(RobotMap.mStartLSwitchTraj));
				addParallel(new TurnToAngle(0));
				addSequential(new SetUpperHeight(true));
				addSequential(new SetUpperHeight(true));
				addSequential(new SetUpperHeight(false));
		
			} else if (gameData.charAt(0) == 'R') {
				addSequential(new PathReverse(RobotMap.mStartRSwitchTraj));
				addParallel(new TurnToAngle(0));
				addSequential(new SetUpperHeight(true));
				addSequential(new SetUpperHeight(true));
				addSequential(new SetUpperHeight(false));

				SmartDashboard.putString("Auto Selection", "Middle Start; Right Switch");

			} else {
				SmartDashboard.putString("Auto Selection", "Middle Start; Bad game data");
			}

		} else if (start.compareTo("Right") == 0) {
			if (gameData.charAt(1) == 'L') {
				if (objective.compareTo("cross") == 0) {
					SmartDashboard.putString("Auto Selection", "Right Start; Left Scale");
					addSequential(new PathReverse(RobotMap.rStartLScaleTraj1));
					addSequential(new PathReverse(RobotMap.rStartLScaleTraj2));
					addParallel(new TurnToAngle(0));
					addSequential(new RaiseArms());
					addSequential(new LowerArms());
				} else if (objective.compareTo("switch") == 0) {
					if (gameData.charAt(0) == 'R') {
						SmartDashboard.putString("Auto Selection", "Right Start; Right Switch");
						addSequential(new PathReverse(RobotMap.rStartRSwitchTraj));
						addParallel(new TurnToAngle(-90));
						addSequential(new SetUpperHeight(true));
						addSequential(new SetUpperHeight(true));
						addParallel(new SetUpperHeight(false));
					} else if (gameData.charAt(0) == 'L') {
						SmartDashboard.putString("Auto Selection", "Right Start: Auto Line");
						addSequential(new PathReverse(RobotMap.autoLineTraj));
					}
				}
			} else if (gameData.charAt(1) == 'R') {
				SmartDashboard.putString("Auto Selection", "Right Start; Right Scale");
				addSequential(new PathReverse(RobotMap.rStartRScaleTraj));
				addParallel(new TurnToAngle(-90));
				addSequential(new RaiseArms());
				addSequential(new LowerArms());
			} else {
				SmartDashboard.putString("Auto Selection", "Right Start; Bad game data");
			}
		} else {
			SmartDashboard.putString("Auto Selection", "Bad sendable chooser");
		}

	}
}
