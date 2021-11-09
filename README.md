# JMC-JVM-LANG-Tutorial
The project contains a collection of JFR usage examples for considered languages. 
The goal is to present "comparable" use cases. The examples may slightly vary 
due to the language but the intent holds.

Note 1: project is inspired by the jmc-tutorial [1]
Note 2: slides from the JUG presentation [2]

1. Java
2. Kotlin

#### Examples
* Hot-Methods
  * Application that spends a lot of time at particular place 
    may not provide expected performance.
* Latency
  * Latency may limit the throughput of the application.
  * It may result in bigger problem.
* Garbage-Collection
  * GC pauses may involve the performance of the application.
  * It may result in application instability due to the GC behaviour.
#### Requirements
- Java: JDK 17+ or JDK 11 to use JMC Agent v1.0.0
- Kotlin: 1.5.31+
- Maven: 3.8+

#### Notes
1. Kotlin Latency examples contains a multiple implementation examples. 
They could be run with VM Options to 
create a continuous reading: ``-XX:+FlightRecorder -XX:StartFlightRecording=filename=jmc_jvm_tutorial.jfr
``

## Reference
1. JMC-Tutorial: https://github.com/thegreystone/jmc-tutorial
2. JUG-Ingolstadt: Kotlin spezial - Presentation slides 20211109_IngJUG_JFR_KotlinSpezial.pdf