package org.usfirst.frc.team5010.robot.commands.auto;

import org.usfirst.frc.team5010.robot.RobotMap;
import org.usfirst.frc.team5010.robot.commands.AutonScaleScore;
import org.usfirst.frc.team5010.robot.commands.LowerArms;
import org.usfirst.frc.team5010.robot.commands.PathForward;
import org.usfirst.frc.team5010.robot.commands.PathReverse;
import org.usfirst.frc.team5010.robot.commands.RaiseArms;
import org.usfirst.frc.team5010.robot.commands.ReleaseCubeIntake;
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

		addParallel(new SetUpperHeight(true));

		if (start.compareTo("Left") == 0) {
			if (gameData.charAt(1) == 'L') {
				if (objective.compareTo("outofway") != 0) {
					SmartDashboard.putString("Auto Selection", "Left Start; Left Scale");
					addSequential(new PathForward(RobotMap.lStartLScaleTraj));
					addSequential(new TurnToAngle(0));
					addSequential(new AutonScaleScore());
				} else if (gameData.charAt(0) == 'L') {
					SmartDashboard.putString("Auto Selection", "Left Start; Left Switch");
					addSequential(new PathForward(RobotMap.lStartLSwitchTraj));
					addSequential(new TurnToAngle(90));
					addParallel(new SetUpperHeight(RobotMap.upperLifter.MIN_ANGLE
							+ (RobotMap.upperLifter.MAX_ANGLE - RobotMap.upperLifter.MIN_ANGLE) / 1.5));
					addSequential(new ReleaseCubeIntake());
				} else {
					addSequential(new PathForward(RobotMap.autoLineTraj));
				}
			} else if (gameData.charAt(1) == 'R') {
				if (objective.compareTo("cross") == 0 && objective.compareTo("outofway") != 0) {
					SmartDashboard.putString("Auto Selection", "Left Start; Right Scale");
					addSequential(new PathForward(RobotMap.lStartRScaleTraj1));
					addSequential(new TurnToAngle(90));
					addSequential(new PathForward(RobotMap.lStartRScaleTraj2));
					addSequential(new TurnToAngle(0));
					addSequential(new AutonScaleScore());

				} else if (objective.compareTo("switch") == 0) {
					if (gameData.charAt(0) == 'L') {
						SmartDashboard.putString("Auto Selection", "Left Start; Left Switch");
						addSequential(new PathForward(RobotMap.lStartLSwitchTraj));
						addSequential(new TurnToAngle(90));
						addParallel(new SetUpperHeight(RobotMap.upperLifter.MIN_ANGLE
								+ (RobotMap.upperLifter.MAX_ANGLE - RobotMap.upperLifter.MIN_ANGLE) / 1.5));
						addSequential(new ReleaseCubeIntake());
					} else {
						SmartDashboard.putString("Auto Selection", "Left Start; Auto Line");
						addSequential(new PathForward(RobotMap.autoLineTraj));
					
					}

				} else {
					SmartDashboard.putString("Auto Selection", "Left Start; Auto Line");
					addSequential(new PathForward(RobotMap.autoLineTraj));
				}

			} else {
				SmartDashboard.putString("Auto Selection", "Left Start; Bad game data");
			}
		} else if (start.compareTo("Middle") == 0) {
			if (gameData.charAt(0) == 'L') {
				SmartDashboard.putString("Auto Selection", "Middle Start; Left Switch");
				addSequential(new PathForward(RobotMap.mStartLSwitchTraj));
				addSequential(new TurnToAngle(0));
				addParallel(new SetUpperHeight(RobotMap.upperLifter.MIN_ANGLE
						+ (RobotMap.upperLifter.MAX_ANGLE - RobotMap.upperLifter.MIN_ANGLE) / 1.5));
				addSequential(new ReleaseCubeIntake());

			} else if (gameData.charAt(0) == 'R') {
				SmartDashboard.putString("Auto Selection", "Middle Start; Right Switch");
				addSequential(new PathForward(RobotMap.mStartRSwitchTraj));
				addSequential(new TurnToAngle(0));
				addParallel(new SetUpperHeight(RobotMap.upperLifter.MIN_ANGLE
						+ (RobotMap.upperLifter.MAX_ANGLE - RobotMap.upperLifter.MIN_ANGLE) / 1.5));
				addSequential(new ReleaseCubeIntake());
			} else {
				SmartDashboard.putString("Auto Selection", "Middle Start; Bad game data");
			}

		} else if (start.compareTo("Right") == 0) {
			if (gameData.charAt(1) == 'L') {
				if (objective.compareTo("cross") == 0) {
					SmartDashboard.putString("Auto Selection", "Right Start; Left Scale");
					addSequential(new PathForward(RobotMap.rStartLScaleTraj1));
					addSequential(new TurnToAngle(-90));
					addSequential(new PathForward(RobotMap.rStartLScaleTraj2));
					addSequential(new TurnToAngle(0));
					addSequential(new AutonScaleScore());

				} else if (gameData.charAt(0) == 'R') {
					if (objective.compareTo("switch") == 0 && objective.compareTo("outofway") != 0) {
						SmartDashboard.putString("Auto Selection", "Right Start; Right Switch");
						addSequential(new PathForward(RobotMap.rStartRSwitchTraj));
						addSequential(new TurnToAngle(0));
						addParallel(new SetUpperHeight(RobotMap.upperLifter.MIN_ANGLE
								+ (RobotMap.upperLifter.MAX_ANGLE - RobotMap.upperLifter.MIN_ANGLE) / 1.5));
						addSequential(new ReleaseCubeIntake());
					}
				} else if (gameData.charAt(0) == 'L') {
					SmartDashboard.putString("Auto Selection", "Right Start: Auto Line");
					addSequential(new PathForward(RobotMap.autoLineTraj));

				}
			} else if (gameData.charAt(1) == 'R') {
				if (objective.compareTo("outofway") != 0) {
					SmartDashboard.putString("Auto Selection", "Right Start; Right Scale");
					addSequential(new PathForward(RobotMap.rStartRScaleTraj));
					addSequential(new TurnToAngle(0));
					addSequential(new AutonScaleScore());
				} else if (gameData.charAt(0) == 'R') {
					SmartDashboard.putString("Auto Selection", "Right Start; Right Switch");
					addSequential(new PathForward(RobotMap.rStartRSwitchTraj));
					addSequential(new TurnToAngle(0));
					addParallel(new SetUpperHeight(RobotMap.upperLifter.MIN_ANGLE
							+ (RobotMap.upperLifter.MAX_ANGLE - RobotMap.upperLifter.MIN_ANGLE) / 1.5));
					addSequential(new ReleaseCubeIntake());
				} else {
					addSequential(new PathForward(RobotMap.autoLineTraj));
				}
			} else {
				SmartDashboard.putString("Auto Selection", "Right Start; Bad game data");
			}
		} else {
			SmartDashboard.putString("Auto Selection", "Bad sendable chooser");
		}

	}
}
