package hu.bme.mit.tachograph;

import hu.bme.mit.train.interfaces.TrainSensor;
import com.google.common.collect.Table;
import com.google.common.collect.HashBasedTable;
import hu.bme.mit.train.interfaces.TrainTachograph;

import java.time.LocalDateTime;


public class TachographImpl implements TrainTachograph {
    private Table<LocalDateTime,Integer,Integer> table=HashBasedTable.create();

    public void Tachographadd(LocalDateTime time, Integer refspeed, Integer joystick)
    {
        table.put(time,refspeed,joystick);
    }

}
