package com.test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Test01 {
    public static void main(String[] args) {
        createTest();
        streamActionTest();
        streamPipelineTest();
    }
    public static void createTest(){
        Stream<String> streamEmpty = Stream.empty();
        Stream<Integer> intStream = Arrays.asList(1,2,3).stream();
        Stream<String> strStream = Stream.of("a","b");
        Stream<Integer> intBuildStream = Stream.<Integer>builder().add(1).add(2).build();
        Stream<String> str2Stream = Stream.generate(() -> "xxx ").limit(10);
        Stream<Integer> intStream2 = Stream.iterate(40, n -> n + 10).limit(10);

        System.out.println(streamEmpty);
    }
    public static void streamActionTest() {
        Stream<String> strStream = Stream.of("a", "b", "c");
        Stream<String> filterdStream = strStream.filter(element -> element.contains("b"));
        Optional<String> anyElement = filterdStream.findAny();
    }
    public static void streamPipelineTest() {
        Stream<String> onceModifiedStream = Stream.of("abcd", "bbcd", "cbcd").skip(1);
        Stream<String> twiceModifiedStream = Stream.of("abcd", "bbcd", "cbcd").skip(1).map(element -> element.substring(0, 3));
        List<String> list = Arrays.asList("abc1", "abc2", "abc3");
        long size = list.stream().skip(1).map(element -> element.substring(0, 3)).sorted().count();
        System.out.println(size);
    }
}
