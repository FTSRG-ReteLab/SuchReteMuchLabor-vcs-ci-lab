package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.user.TrainUserImpl;
import hu.bme.mit.train.controller.TrainControllerImpl;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TrainSensorImplTest{
    private TrainSensor sensor;
    private TrainController trainController=new TrainControllerImpl();
    private TrainUser user=new TrainUserImpl(trainController);


    @Before
    public void before()
    {
        sensor = new TrainSensorImpl(trainController,user);
    }
    @Test
    public void NoAbsoluteAlarmTest()
    {
        sensor.overrideSpeedLimit(1);
        Assert.assertFalse(user.getAlarmFlag());

        sensor.overrideSpeedLimit(499);
        trainController.setJoystickPosition(499);
        trainController.followSpeed();
        user.setAlarmState(false);
        sensor.overrideSpeedLimit(499);
        Assert.assertFalse(user.getAlarmFlag());
    }

    @Test
    public void AbsoluteAlarmTest()
    {
        trainController.setJoystickPosition(50);
        trainController.followSpeed();
        sensor.overrideSpeedLimit(0);
        Assert.assertTrue(user.getAlarmFlag());

        trainController.setJoystickPosition(490);
        trainController.followSpeed();
        sensor.overrideSpeedLimit(490);

        trainController.setJoystickPosition(490);
        trainController.followSpeed();
        sensor.overrideSpeedLimit(500);
        Assert.assertTrue(user.getAlarmFlag());
    }
    @Test
    public void NoRelativeAlarmTest()
    {
        trainController.setJoystickPosition(50);
        trainController.followSpeed();
        sensor.overrideSpeedLimit(100);
        Assert.assertFalse(user.getAlarmFlag());
    }
    @Test
    public void RelativeAlarmTest()
    {
        trainController.setJoystickPosition(50);
        trainController.followSpeed();
        sensor.overrideSpeedLimit(24);
        Assert.assertTrue(user.getAlarmFlag());
    }

}