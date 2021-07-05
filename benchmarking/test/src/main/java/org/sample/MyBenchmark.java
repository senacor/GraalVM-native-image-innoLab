package org.sample;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.RandomStringUtils;

import org.openjdk.jmh.annotations.Benchmark;

public class MyBenchmark {

    @Benchmark
    public void testMethod() {
        List<String> randomStrings = Stream.generate(() -> RandomStringUtils
                .randomAlphabetic(25))
                .limit(100_000)
                .collect(Collectors.toList());
        randomStrings.sort(String::compareToIgnoreCase);
    }
}
