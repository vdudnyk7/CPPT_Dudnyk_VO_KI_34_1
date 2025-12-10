package KI304.Dudnyk.Lab2;

import java.io.*;

/**
 * Class <code>Airplane</code> implements airplane with its subsystems
 */
public class Airplane {
    private Engine engine;
    private Cockpit cockpit;
    private Wing wing;
    private PrintWriter fout;

    /**
     * Constructor
     * 
     * @throws FileNotFoundException
     */
    public Airplane() throws FileNotFoundException {
        engine = new Engine();
        cockpit = new Cockpit();
        wing = new Wing();
        fout = new PrintWriter(new File("AirplaneLog.txt"));
    }

    /**
     * Constructor
     * 
     * @param seats number of seats
     * @param span  wing span
     * @throws FileNotFoundException
     */
    public Airplane(int seats, double span) throws FileNotFoundException {
        this();
        engine = new Engine(); // початкова тяга = 0
        cockpit = new Cockpit(seats);
        wing = new Wing(span);
        fout = new PrintWriter(new File("AirplaneLog.txt"));
    }

    /** Start engine */
    public void startEngine() {
        engine.start();
        engine.setThrust(100); // мінімальна тяга для запуску
        fout.println("Engine started with thrust " + engine.getThrust());
        fout.flush();
    }

    /** Stop engine */
    public void stopEngine() {
        engine.stop();
        fout.println("Engine stopped");
        fout.flush();
    }

    /** Take off */
    public void takeOff() {
        engine.increaseThrust(50); // збільшення тяги для зльоту
        cockpit.setAltitude(1000);
        wing.setFlapsExtended(true);
        fout.println("Airplane took off");
        fout.flush();
    }

    /** Land */
    public void land() {
        engine.decreaseThrust(30);
        cockpit.setAltitude(0);
        wing.setFlapsExtended(false);
        engine.stop();
        fout.println("Airplane landed");
        fout.flush();
    }

    /** Change heading */
    public void changeHeading(int heading) {
        cockpit.setHeading(heading);
        fout.println("Heading changed to " + cockpit.getHeading());
        fout.flush();
    }

    /** Set altitude */
    public void setAltitude(int altitude) {
        cockpit.setAltitude(altitude);
        fout.println("Altitude set to " + cockpit.getAltitude());
        fout.flush();
    }

    /** Perform maintenance */
    public void performMaintenance() {
        engine.maintenance();
        fout.println("Engine maintenance done");
        fout.flush();
    }

    /** Get status */
    public String getStatus() {
        String s = "Running=" + engine.isRunning() +
                ", thrust=" + engine.getThrust() +
                ", altitude=" + cockpit.getAltitude() +
                ", heading=" + cockpit.getHeading() +
                ", seats=" + cockpit.getSeats();
        fout.println("Status requested: " + s);
        fout.flush();
        return s;
    }

    /** Dispose resources */
    public void dispose() {
        fout.close();
    }
}

/** Engine subsystem */
class Engine {
    private boolean running;
    private int thrust;

    public Engine() {
        this.thrust = 0;
        this.running = false;
    }

    public void start() {
        running = true;
    }

    public void stop() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    public int getThrust() {
        return thrust;
    }

    public void setThrust(int t) {
        thrust = t;
    }

    public void increaseThrust(int d) {
        thrust += d;
    }

    public void decreaseThrust(int d) {
        thrust = Math.max(0, thrust - d);
    }

    public void maintenance() {
        thrust = 0;
    }
}

/** Cockpit subsystem */
class Cockpit {
    private int heading;
    private int altitude;
    private int seats;

    public Cockpit() {
        this(2);
    }

    public Cockpit(int seats) {
        this.seats = seats;
        this.heading = 0;
        this.altitude = 0;
    }

    public int getHeading() {
        return heading;
    }

    public void setHeading(int h) {
        heading = ((h % 360) + 360) % 360;
    }

    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int a) {
        altitude = Math.max(0, a);
    }

    public int getSeats() {
        return seats;
    }
}

/** Wing subsystem */
class Wing {
    private double span;
    private boolean flapsExtended;

    public Wing() {
        this(10.0);
    }

    public Wing(double span) {
        this.span = span;
        this.flapsExtended = false;
    }

    public boolean isFlapsExtended() {
        return flapsExtended;
    }

    public void setFlapsExtended(boolean f) {
        flapsExtended = f;
    }
}
