package org.usfirst.frc.team5010.robot.subsystems;

import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.command.Subsystem;

/*
 *
 */

public class Vision extends Subsystem {
	public static final String FRONT_CAMERA = "front camera";
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private UsbCamera camera0 = null;
	public void startVision() {
		if (null == camera0) {
			camera0 = RobotMap.camera.startAutomaticCapture();
			camera0.setResolution(640,  360);
			camera0.setFPS(60);
			camera0.setExposureManual(0);
		}
		RobotMap.camera.getVideo(FRONT_CAMERA).setEnabled(true);
	}
	public void stopVision() {
		if (null != camera0) {
			RobotMap.camera.getVideo(FRONT_CAMERA).setEnabled(false);
		}
	}
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
