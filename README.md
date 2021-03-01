Micro Benchmark of AspectJ 

To Build: mvn clean install

### aspect-spring-proxy
Aspect implemented as Spring Proxy Bean

java -jar aspect-spring-proxy/target/aspect-spring-proxy-1.0-SNAPSHOT.jar

Throughput: 453564.482 ± 55248.293  ops/s

### aspect-load-time-weaving
Aspect implemented as Load-Time-Weaving subclass

java -jar aspect-load-time-weaving/target/aspect-load-time-weaving-1.0-SNAPSHOT.jar

Throughput: 977620.713 ± 45814.977  ops/s

### aspect-not-used
Equivalent Functionalities of the Aspect are implemented as function calls before and after the actual computation.

java -cp aspect-not-used/target/benchmark.jar com.priceline.jmh.withoutaspect.Computation

Throughput: 970102.407 ± 50133.311  ops/s