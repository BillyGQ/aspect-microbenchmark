package com.priceline.jmh;

import java.util.Random;

import org.openjdk.jmh.infra.Blackhole;

import com.priceline.jmh.JMHApplication.BenchmarkState;

public class Computation {

     @SomeAnnotation
    public void doWork(Blackhole blackhole, BenchmarkState benchmarkState) {
        Random random = benchmarkState.random;

        blackhole.consume(random.nextInt());
    }
}
