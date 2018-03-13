package org.usfirst.frc.team5010.robot.commands.auto;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class FieldMovement extends CommandGroup {
	public FieldMovement(String start) {
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(gameData != null && gameData.isEmpty() && gameData.length() != 3) {
			SmartDashboard.putString("Auto Selection", "No game data");
			return;
		}
		
		if (start.compareTo("Left") == 0) {
			if (gameData.charAt(1) == 'L') {

			} else if (gameData.charAt(1) == 'R') {

			} else {
				SmartDashboard.putString("Auto Selection", "Left Start; Bad game data");
			}
		} else if (start.compareTo("Middle") == 0) {
			if (gameData.charAt(0) == 'L') {

			} else if (gameData.charAt(0) == 'R') {

			} else {
				SmartDashboard.putString("Auto Selection", "Middle Start; Bad game data");
			}

		} else if (start.compareTo("Right") == 0) {
			if (gameData.charAt(1) == 'L') {

			} else if (gameData.charAt(1) == 'R') {

			} else {
				SmartDashboard.putString("Auto Selection", "Middle Start; Bad game data");
			}
		} else {
			SmartDashboard.putString("Auto Selection", "Bad sendable chooser");
		}

	}
}
