package hu.bme.mit.train.system;

import hu.bme.mit.train.controller.TrainControllerImpl;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainTachograph;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.sensor.TrainSensorImpl;
import hu.bme.mit.train.user.TrainUserImpl;
import hu.bme.mit.tachograph.TachographImpl;
import org.junit.Test;

import java.time.LocalDateTime;

public class TrainSystem {

	private TrainController controller = new TrainControllerImpl();
	private TrainUser user = new TrainUserImpl(controller);
	private TrainSensor sensor = new TrainSensorImpl(controller, user);
	private TrainTachograph tachograph=new TachographImpl();

	public TrainController getController() {
		return controller;
	}

	public TrainSensor getSensor() {
		return sensor;
	}

	public TrainUser getUser() {
		return user;
	}

	public void log()
	{
		tachograph.Tachographadd(LocalDateTime.now(),controller.getReferenceSpeed(),user.getJoystickPosition());
	}
	public TrainTachograph getTachograph()
	{
		return tachograph;
	}



}
