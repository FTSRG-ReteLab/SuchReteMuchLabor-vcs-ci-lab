package hu.bme.mit.train.controller;

import org.junit.Test;

public class TrainControllerImplTest {
    @Test
    public void WrongHotfix()
    {
        TrainControllerImpl tmp=new TrainControllerImpl();
        if(tmp.neededhotfix!=1) System.out.println("bad hotfix");
        else System.out.println("good hotfix");
    }

}