package hu.bme.mit.train.interfaces;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.time.LocalDateTime;

public interface TrainTachograph {
    
    public void Tachographadd(LocalDateTime time, Integer refspeed, Integer joystick);

    public Table<LocalDateTime,Integer,Integer> getTable();
}
