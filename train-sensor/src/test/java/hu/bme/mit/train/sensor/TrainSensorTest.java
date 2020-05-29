package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TrainSensorTest {

    TrainController trainController;
    TrainUser trainUser;
    TrainSensorImpl trainSensorImpl;

    @Before
    public void before() {
        trainController = mock(TrainController.class);
        trainUser = mock(TrainUser.class);
        trainSensorImpl = new TrainSensorImpl(trainController, trainUser);
    }

    @Test
    public void CheckSpeedlimit_Success() {
        Assert.assertEquals(5,5);
    }

    @Test
    public void CheckAlarmStateAbsMarginNormal() {
        trainSensorImpl.overrideSpeedLimit(5);
        verify(trainUser, times(1)).setAlarmState(false);
    }

    @Test
    public void CheckAlarmStateAbsMarginMax() {
        trainSensorImpl.overrideSpeedLimit(5000);
        verify(trainUser, times(1)).setAlarmState(true);
    }

    @Test
    public void CheckRelativeAlarmTest()
    {
        when(trainController.getReferenceSpeed()).thenReturn(50);
        trainSensorImpl.overrideSpeedLimit(24);
        verify(trainUser,times(1)).setAlarmState(true);
    }


}
