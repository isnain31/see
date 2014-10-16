import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPortListener;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;
import lejos.nxt.*;
//import lejos.nxt.addon.RCXLightSensor;
import lejos.nxt.LightSensor;

public class MotionModel {
	DifferentialPilot pilot;
	public NXTRegulatedMotor leftMotor;
	public NXTRegulatedMotor rightMotor;
	public NXTRegulatedMotor armMotor;
	public LightSensor light; 
	private boolean eventLoop;
	
	public MotionModel(){
		this.light = new LightSensor(SensorPort.S2);
		
		this.leftMotor=Motor.A;
		this.rightMotor=Motor.B;
		this.armMotor=Motor.C;
		this.eventLoop=true;
		while (this.eventLoop) {
		//	LCD.drawInt(light.getLightValue(), 4, 0, 0);
		    if(light.getLightValue()>45){
		      simpleRun();
		    }
		}
		
	}
	
	
	public void run() {
		pilot.setTravelSpeed(30);
		pilot.rotate(90);
		pilot.travel(20);
		pilot.rotate(-90);
	}
	
	public void simpleRun(){
		this.eventLoop=false;
		//LCD.drawInt(this.armMotor.getTachoCount(),0,0);
		this.leftMotor.rotate(270);
		Delay.msDelay(500);
		this.drawPoint();
		
		this.leftMotor.setSpeed(360);
		this.rightMotor.setSpeed(360);
		this.leftMotor.forward();
		this.rightMotor.forward();
		Delay.msDelay(2000);
		if(this.leftMotor.isMoving() && this.rightMotor.isMoving()){
			this.leftMotor.stop(true);
			this.rightMotor.stop();
			this.drawPoint();
		}
		
		this.rightMotor.rotate(270);
		Delay.msDelay(500);
		this.drawPoint();
		
		Button.waitForAnyPress();		
	}
	
	private void drawPoint(){
		this.armMotor.setSpeed(180);
		this.armMotor.rotateTo(-100);
		this.armMotor.rotateTo(0);
		
	}
	public static void main (String[] args) {
		MotionModel motionModel=new MotionModel();
		//motionModel.pilot=new DifferentialPilot(2.25f, 5.5f, Motor.A, Motor.B);
		//motionModel.run();
		//motionModel.simpleRun();
	}
}