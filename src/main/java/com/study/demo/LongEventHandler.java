package com.study.demo;


import com.lmax.disruptor.EventHandler;

public class LongEventHandler implements EventHandler<LongEvent> {

   static long count = 0;

    @Override
    public void onEvent(LongEvent event, long sequence, boolean b) throws Exception {
        count ++;
        System.out.println("["+Thread.currentThread().getName()+"]"+event+"序号: "+sequence);
    }
}
