package hu.bme.mit.train.controller;

import hu.bme.mit.train.interfaces.TrainController;

public class TrainControllerImpl implements TrainController {
	public int neededhotfix=0;
	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;
	private Thread thread;

	public TrainControllerImpl() {
		thread = new Thread() {
			public void run() {
				thread.run();
				try {
					followSpeed();
					thread.sleep(5000);
				} catch (InterruptedException interruptedException) {
					interruptedException.printStackTrace();
				}
			}
		};
	}

	@Override
	public void followSpeed() {
		if (referenceSpeed < 0) {
			referenceSpeed = 0;
		} else {
		    if(referenceSpeed+step > 0) {
                referenceSpeed += step;
            } else {
		        referenceSpeed = 0;
            }
		}

		enforceSpeedLimit();
	}

	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();

	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;
	}

}
