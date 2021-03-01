package com.priceline.jmh;

import java.util.Random;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class JMHApplication {

    private final static Integer MEASUREMENT_ITERATIONS = 10;
    private final static Integer WARMUP_ITERATIONS = 5;

    static Computation computation;

    public static void main(String[] args) throws RunnerException {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder().sources(JMHApplication.class).build().run();

        computation = (Computation) applicationContext.getBean("computation");

        Options opt = new OptionsBuilder()
            // set the class name regex for benchmarks to search for to the current class
            .include("\\.JMHApplication\\.")
            .warmupIterations(WARMUP_ITERATIONS)
            .measurementIterations(MEASUREMENT_ITERATIONS)
            // do not use forking or the benchmark methods will not see references stored within its class
            .forks(0)
            // do not use multiple threads
            .threads(1)
            .mode(Mode.Throughput)
            .shouldDoGC(true)
            .shouldFailOnError(true)
            .resultFormat(ResultFormatType.JSON)
            .shouldFailOnError(true)
            .jvmArgs("-server")
            .build();

        new Runner(opt).run();
    }

    @State(Scope.Benchmark)
    public static class BenchmarkState {

        Random random;

        @Setup(Level.Invocation)
        public void setUp() {
            random = new Random(Long.MAX_VALUE);
        }
    }

    @Benchmark
    public void microBenchmark(Blackhole blackhole, BenchmarkState benchmarkState) {
        computation.doWork(blackhole, benchmarkState);
    }

}
