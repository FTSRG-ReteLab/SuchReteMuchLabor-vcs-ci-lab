package hu.bme.mit.train.controller;

import org.junit.Assert;
import org.junit.Test;

public class TrainControllerImplTest {
    @Test
    public void WrongHotfix()
    {
        TrainControllerImpl tmp=new TrainControllerImpl();
        Assert.assertEquals(0,tmp.neededhotfix);
    }

}