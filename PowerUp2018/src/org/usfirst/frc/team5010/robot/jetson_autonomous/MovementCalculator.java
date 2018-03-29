package org.usfirst.frc.team5010.robot.jetson_autonomous;

public class MovementCalculator {
    private ImageDataIO IDTable;
    private float[] data;
    
    public void MovementCalculator(ImageDataIO i) {
        IDTable = i;
        data = new float[4];
    }
    
    private double sqr(double d) {return d * d;}
    
    //The Z plane is forward and backward, the X plane is left and right, and the Y plane is up and down
    public void calculateRelativeXYZPosition(DoublePointer X, DoublePointer Y, DoublePointer Z, double cameraX, double cameraY) {
        double d = CameraConstants.HEIGHT * Math.sqrt(sqr(cameraX / cameraY) + sqr(CameraConstants.FOCAL_LENGTH / cameraY) + 1);
        
        double d0 = d * sin(CameraConstants.THETA_Y_XZ);
        
        double theta_x_yz = Math.asin((cameraX - CameraConstants.CAMERA_WIDTH / 2) / CameraConstants.CAMERA_WIDTH) * CameraConstants.CAMERA_ANGLE_X_YZ_INTERVAL_SIZE + CameraConstants.CAMERA_ANGLE_X_YZ_OFFSET;
        
        X.val = d0 * Math.sin(theta_x_yz);
        Y.val = CameraConstants.HEIGHT;
        Z.val = d0 * Math.cos(theta_x_yz);
    }
    
    public void computeNextValues() {
        //Keep obtaining values until they are recieved in a manner such that they are not mid-update
        while (!getValues(data));
        
        DoublePointer x, y, z;
        
    }
}
