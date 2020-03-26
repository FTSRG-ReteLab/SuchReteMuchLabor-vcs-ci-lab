package hu.bme.mit.train.system;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.system.TrainSystem;

public class TrainSystemTest {

	TrainSystem system;
	TrainController controller;
	TrainSensor sensor;
	TrainUser user;
	
	@Before
	public void before() {
		system = new TrainSystem();
		controller = system.getController();
		sensor = system.getSensor();
		user = system.getUser();

		sensor.overrideSpeedLimit(50);
	}
	
	@Test
	public void OverridingJoystickPosition_IncreasesReferenceSpeed() {
		sensor.overrideSpeedLimit(10);

		Assert.assertEquals(0, controller.getReferenceSpeed());
		
		user.overrideJoystickPosition(5);

		controller.followSpeed();
		Assert.assertEquals(5, controller.getReferenceSpeed());
		controller.followSpeed();
		Assert.assertEquals(10, controller.getReferenceSpeed());
		controller.followSpeed();
		Assert.assertEquals(10, controller.getReferenceSpeed());
	}

	@Test
	public void OverridingJoystickPositionToNegative_SetsReferenceSpeedToZero() {
		user.overrideJoystickPosition(4);
		controller.followSpeed();
		user.overrideJoystickPosition(-5);
		controller.followSpeed();
		Assert.assertEquals(0, controller.getReferenceSpeed());
	}

	@Test
	public void testLog() {
		system.log();
		Assert.assertTrue(0<system.getTachograph().getTable().size());
	}
	@Test
	public void testRun()
	{
		sensor.overrideSpeedLimit(60);
		Assert.assertEquals(60, sensor.getSpeedLimit());

		user.overrideJoystickPosition(30);
		Assert.assertEquals(30,user.getJoystickPosition());

		for(int iteration=0;iteration<7;iteration++)
		{
			controller.followSpeed();
			if(iteration==0) Assert.assertEquals(30, controller.getReferenceSpeed());
			if(iteration==1) Assert.assertEquals(60, controller.getReferenceSpeed());
			if(iteration==2) { Assert.assertEquals(60, controller.getReferenceSpeed()); user.overrideJoystickPosition(0); }
			if(iteration==3) Assert.assertEquals(60, controller.getReferenceSpeed());
			if(iteration==4) { Assert.assertEquals(60, controller.getReferenceSpeed()); user.overrideJoystickPosition(-30);}
			if(iteration==5) { Assert.assertEquals(30, controller.getReferenceSpeed()); user.overrideJoystickPosition(0);}
			if(iteration==6)  Assert.assertEquals(30, controller.getReferenceSpeed());
		}

	}

	
}
