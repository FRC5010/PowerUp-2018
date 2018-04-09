package org.usfirst.frc.team5010.robot.jetson_autonomous;

public class MovementCalculator {
	
	
    //Member variables 
    private ImageDataIO IDTable;
    private float[] data;
    
    //boolean denoting whether the more accurate or the simple method is used
    private boolean simple = false;

	
    //Constructor 
    public void MovementCalculator(ImageDataIO i) {
        IDTable = i;
        data = new float[4];
    }
    
    //just to save space, squares a number 
    private double sqr(double d) {return d * d;}
    
    //Sigmoid logistic function to squash values to (0, 1)
    private double sigmoid(double d) {
        double d0 = Math.exp(d);
        return d0 / (d0 + 1);
    }

    //Based on camera geometry detailed in a lecture by professor Silvio Silvarese at Stanford 
	//The Z plane is forward and backward, the X plane is left and right, and the Y plane is up and down
    public void calculateRelativeXYZPosition(DoublePointer X, DoublePointer Y, DoublePointer Z, double cameraX, double cameraY) {
        double d = CameraConstants.ANGLED_HEIGHT * Math.sqrt(sqr(cameraX / cameraY) + sqr(CameraConstants.FOCAL_LENGTH / cameraY) + 1);
        
        double d0 = d * sin(CameraConstants.THETA_Y_XZ);
        
        double theta_x_yz = Math.asin((cameraX - CameraConstants.CAMERA_WIDTH / 2) / CameraConstants.CAMERA_WIDTH) * CameraConstants.CAMERA_ANGLE_X_YZ_INTERVAL_SIZE + CameraConstants.CAMERA_ANGLE_X_YZ_OFFSET;
        
        X.val = d0 * Math.sin(theta_x_yz);
        Y.val = CameraConstants.HEIGHT;
        Z.val = d0 * Math.cos(theta_x_yz);
    }
    
    //Computes next vectorized trajectory 
    public void computeNextValues(DoublePointer theta, DoublePointer magnitude, double cameraX, double cameraY, double cameraSize, double cameraReserved); {
        //Keep obtaining values until they are recieved in a manner such that they are not mid-update
        while (!getValues(data));
        
        if (simple) {
            theta.val = CameraConstants.CAMERA_ANGLE_X_YZ_INTERVAL_SIZE * (x * 2 - 1);
            magnitude.val = 1 - cameraSize;
        } else {

            DoublePointer x, y, z;

            x = new DoublePointer();
            y = new DoublePointer();
            z = new DoublePointer();
            calculateRelativeXYZPosition(x, y, z, cameraX, cameraY, cameraSize, cameraReserved);

            theta.val = z.val == 0.0 ? (x.val < 0.0 ? -Math.PI / 2 : Math.PI / 2) :  Math.atan(x.val / z.val);
            theta.val *= CameraConstants.CAMERA_ANGLE_X_YZ_INTERVAL_SIZE / (Math.PI / 2);
            magnitude.val = ( sigmoid( Math.sqrt( sqr(x.val) + sqr(z.val))) + 1 - cameraSize) / 2;
        }
    }
}
