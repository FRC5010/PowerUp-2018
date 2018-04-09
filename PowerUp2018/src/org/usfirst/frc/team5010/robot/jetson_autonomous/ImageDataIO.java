package org.usfirst.frc.team5010.robot.jetson_autonomous;

import java.util.function.Consumer;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.EntryNotification;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class ImageDataIO implements Runnable { 
    //member variables
    private static double x;
    private static double y;
    private static double siz;
    private static double reserved;
    private static boolean isUpdating;
    private static NetworkTable table;
    private static NetworkTableInstance instance;
    
    //method that will initialize a new network table and the member variables, not unalike a constructor, but more suited to being multithreaded
    public void run() {
        Consumer<EntryNotification> listener = (x) -> {valueChanged(x.name, x.value, true);};
        instance = NetworkTableInstance.getDefault();
        for (String s : new String[] {"BoxX", "BoxY", "BoxSize", "BoxReserved"})
        	instance.addEntryListener(s, listener, EntryListenerFlags.kUpdate);
        
        
        x = Float.NaN;
        y = Float.NaN;
        siz = Float.NaN;
        reserved = Float.NaN;
        isUpdating = false;
    }
    
    //callback for when any value in table changes
    //all values are synchronized as to make sure they are threadsafe, and the boolean flag isUpdating is set during the operation
    public synchronized static void valueChanged(String s, Object o, boolean b)  {
        isUpdating = true;
        
        if (b == false) {
            if (s.equals("BoxX")) {
                x = ((double)o);
            } else if (s.equals("BoxY")) {
            	y = ((double)o);
            } else if (s.equals("BoxSize")) {
                siz = (double)o;
            } else if (s.equals("BoxReseved")) {
                reserved = (double)o;
            } else {
                isUpdating = false;
                throw new IllegalArgumentException("Erroneous update to data table.\n");
            }
        }
        
        isUpdating = false;
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
    public boolean getValues(double[] values) {
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
