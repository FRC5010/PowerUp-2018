package org.usfirst.frc.team5010.robot.jetson_autonomous;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.networktables.ITable;
import edu.wpi.first.wpilibj.networktables.ITableListener;

public class ImageDataIO implements ITableListener, Runnable { 
    //member variables
    private double x;
    private double y;
    private double siz;
    private double reserved;
    private boolean isUpdating;
    private NetworkTable table;
    
    //method that will initialize a new network table and the member variables, not unalike a constructor, but more suited to being multithreaded
    public static void run() {
        NetworkTable.setClientMode():
        NetworkTable.setIPAddress("10.1.90.2");
        table = new NetworkTable();
        
        x = Float.NaN;
        y = Float.NaN;
        siz = Float.NaN;
        reserved = Float.NaN;
        isUpdating = false;
    }
    
    //callback for when any value in table changes
    //all values are synchronized as to make sure they are threadsafe, and the boolean flag isUpdating is set during the operation
    @Override
    public void valueChanged(ITable table, String s, Object o, boolean b) {
        synchronized isUpdating = true;
        
        if (b == false) {
            if (s.equals("BoxX")) {
                synchronized x = (double)o;
            } else if (s.equals("BoxY")) {
                synchronized y = (double)o;
            } else if (s.equals("BoxSize") {
                synchronized siz = (double)o;
            } else if (s.equals("BoxReseved")) {
                synchronized reserved = (double)o;
            } else {
                synchronized isUpdating = false;
                throw new Exception("Erroneous update to data table.\n");
            }
        }
        
        synchronized isUpdating = false;
    }
    
    //Getters
    //Each value in the table is passed by reference, is set by the getter, and a boolean denoting whether or not the value was obtained during an update is returned
    public NetworkTable getTable() {
        return table;
    }
    
    public boolean getUpdateStatus() {
        return isUpdating;
    }
    
    public boolean getX(DoublePointer d) {
        boolean b = getUpdateStatus();
        d.val = x;
        return b;
    }
    public boolean getY(DoublePointer d) {
        boolean b = getUpdateStatus();
        d.val = y;
        return b;
    }
    public boolean getSize(DoublePointer d) {
        boolean b = getUpdateStatus();
        d.val = siz;
        return b;
    }
    public boolean getReserved(DoublePointer d) {
        boolean b = getUpdateStatus();
        d.val = reserved;
        return b;
    }
    
    //This getter is the recommended getter, which obtains the values x, y, size, and reserved
    public boolean getValues(float[] values) {
        DoublePointer d = new DoublePointer();
        boolean obtainDuringUpdate = false;
        
        obtainDuringUpdate = obtainDuringUpdate || getX(d);
        values[0] = d.val;
        obtainDuringUpdate = obtainDuringUpdate || getY(d);
        values[1] = d.val;
        obtainDuringUpdate = obtainDuringUpdate || getSize(d);
        values[2] = d.val;
        obtainDuringUpdate = obtainDuringUpdate || getReserved(d);
        values[3] = d.val;
        
        return obtainDuringUpdate;
    }
}
