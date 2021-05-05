package com.study.demo;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.EventTranslatorTwoArg;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.Executors;

public class T02_DisruptorDemo {

    public static void main(String[] args) {
        LongEventFactory factory = new LongEventFactory();

        //定义RingBuffer，必须是2的N次方
        int bufferSize = 1024;

        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(
                factory, bufferSize, Executors.defaultThreadFactory()
        );

        disruptor.handleEventsWith(new LongEventHandler());

        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        ///============================================//
        EventTranslator<LongEvent> translator = new EventTranslator<LongEvent>() {
            @Override
            public void translateTo(LongEvent event, long sequence) {
                event.set(8888L);
            }
        };

        ringBuffer.publishEvent(translator);


//        //=============================================//
//        EventTranslatorOneArg<LongEvent,Long> translator02 = new EventTranslatorOneArg<LongEvent,Long>() {
//            @Override
//            public void translateTo(LongEvent event, long sequence,Long l) {
//                event.set(l);
//            }
//        };
//
//        ringBuffer.publishEvent(translator02,6666L);
//
//        //=================================================//
//        EventTranslatorTwoArg<LongEvent,Long,Long> translator03 = new EventTranslatorTwoArg<LongEvent, Long, Long>() {
//            @Override
//            public void translateTo(LongEvent event, long sequence, Long arg0, Long arg1) {
//                event.set(arg0+arg1);
//            }
//        };
//
//        ringBuffer.publishEvent(translator03,2222L,4444L);

    }
}
