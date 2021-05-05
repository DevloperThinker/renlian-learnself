package com.study.demo;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.sql.SQLOutput;

public class T03_DisruptorDemo {
    public static void main(String[] args) {
        int bufferSize = 1024;

        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(
                LongEvent::new,bufferSize, DaemonThreadFactory.INSTANCE
        );

      disruptor.handleEventsWith(((event, sequence, endOfBatch) -> System.out.println("Event:"+event+" 序号："+sequence)));

      disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        ringBuffer.publishEvent(((event, sequence) -> event.set(10000L)));


    }
}
