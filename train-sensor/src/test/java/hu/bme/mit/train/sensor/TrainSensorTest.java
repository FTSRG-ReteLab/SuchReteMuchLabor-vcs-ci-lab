package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import static org.mockito.Mockito.*;

public class TrainSensorTest {



    private TrainSensor sensor;

    private TrainUser user;
    private TrainController trainController;

    @Before
    public void before()
    {
        trainController = mock(TrainController.class);
        user = mock(TrainUser.class);
        sensor = new TrainSensorImpl(trainController,user);
    }
    @Test
    public void NoAbsoluteAlarmTest()
    {
        sensor.overrideSpeedLimit(1);
        verify(user,times(0)).setAlarmState(false);

        sensor.overrideSpeedLimit(499);
        verify(user,times(0)).setAlarmState(false);
    }

    @Test
    public void AbsoluteAlarmTest()
    {

        sensor.overrideSpeedLimit(0);
        verify(user,times(1)).setAlarmState(true);

        sensor.overrideSpeedLimit(500);
        verify(user,times(2)).setAlarmState(true);
    }
    @Test
    public void NoRelativeAlarmTest()
    {
        when(trainController.getReferenceSpeed()).thenReturn(50);
        sensor.overrideSpeedLimit(100);
        verify(user,times(0)).setAlarmState(false);
    }
    @Test
    public void RelativeAlarmTest()
    {
        when(trainController.getReferenceSpeed()).thenReturn(50);
        sensor.overrideSpeedLimit(24);
        verify(user,times(1)).setAlarmState(true);
    }

}
