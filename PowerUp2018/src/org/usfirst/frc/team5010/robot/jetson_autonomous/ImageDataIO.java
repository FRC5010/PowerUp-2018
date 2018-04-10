package org.usfirst.frc.team5010.robot.jetson_autonomous;

import java.util.Random;
import java.util.function.Consumer;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.EntryNotification;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ImageDataIO implements Runnable { 
    //member variables
    private static double x;
    private static double y;
    private static double siz;
    private static double reserved;
    private static NetworkTable table;
    private static NetworkTableInstance instance;
    private static NetworkTableEntry xEntry;
    private static NetworkTableEntry yEntry;
    private static NetworkTableEntry sizEntry;
    private static NetworkTableEntry resEntry;
	private static Random rand = new Random(1234567);
    
    //method that will initialize a new network table and the member variables, not unalike a constructor, but more suited to being multithreaded
    public void run() {
        Consumer<EntryNotification> listener = (x) -> {valueChanged(x.name, x.value.getValue(), true);};
        instance = NetworkTableInstance.getDefault();
        table = instance.getTable("table");
        instance.startClientTeam(5010);
        xEntry = table.getEntry("BoxX");
        xEntry.addListener(listener, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);
        yEntry = table.getEntry("BoxY");
        yEntry.addListener(listener, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);
        sizEntry = table.getEntry("BoxSize");
        sizEntry.addListener(listener, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);
        resEntry = table.getEntry("BoxReserved");
        resEntry.addListener(listener, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);
//        for (String s : new String[] {"BoxX", "BoxY", "BoxSize", "BoxReserved"}) {
//        	table.addEntryListener(
//        		(table, key, entry, value, flags) -> {valueChanged(entry.getValue().getString(), value, true);}, 
//        		EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);
//        }
        
        x = Double.NaN;
        y = Double.NaN;
        siz = Double.NaN;
        reserved = Double.NaN;
    }
    
    //callback for when any value in table changes
    //all values are synchronized as to make sure they are threadsafe, and the boolean flag isUpdating is set during the operation
    public synchronized static void valueChanged(String s, Object o, boolean b)  {
    	SmartDashboard.putString("NT Entry", s);
    	SmartDashboard.putNumber("NT Value", (double)o);
        if (b == false) {
            if (s.equals("BoxX")) {
                x = ((double)o);
            	SmartDashboard.putNumber("NT X", x);
            } else if (s.equals("BoxY")) {
            	y = ((double)o);
            	SmartDashboard.putNumber("NT Y", y);
            } else if (s.equals("BoxSize")) {
                siz = (double)o;
            	SmartDashboard.putNumber("NT Size", siz);
            } else if (s.equals("BoxReseved")) {
                reserved = (double)o;
            	SmartDashboard.putNumber("NT Reserved", reserved);
            } else {
                throw new IllegalArgumentException("Erroneous update to data table.\n");
            }
        }
    }
    
    //Getters
    //Each value in the table is passed by reference, is set by the getter, and a boolean denoting whether or not the value was obtained during an update is returned
    public NetworkTable getTable() {
        return table;
    }
    
    public synchronized double getX() {
        return x;
    }
    public synchronized double getY() {
        return y;
    }
    public synchronized double getSize() {
        return siz;
    }
    public synchronized double getReserved() {
        return reserved;
    }
    
  //This getter is the recommended getter, which obtains the values x, y, size, and reserved
    public synchronized static void getValues(double[] values) {
    	x = rand.nextDouble();
    	y = rand.nextDouble();
    	siz = rand.nextDouble();
    	reserved = rand.nextDouble();
    	values[0] = x;
        values[1] = y;
        values[2] = siz;
        values[3] = reserved;
    	SmartDashboard.putNumber("GV X", x);
    	SmartDashboard.putNumber("GV Y", y);
    	SmartDashboard.putNumber("GV Size", siz);
    	SmartDashboard.putNumber("GV Reserved", reserved);
    }
}
