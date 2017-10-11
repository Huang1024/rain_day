package com.hht.practice.jdk8.streams;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by hht on 2017/10/11.
 */
public class Streams {

    private enum Status {
        OPEN, CLOSED
    }

    private static final class Task {
        private final Status status;
        private final Integer points;

        Task(final Status status, final Integer points) {
            this.status = status;
            this.points = points;
        }

        public Integer getPoints() {
            return points;
        }

        public Status getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return String.format("[%s, %d]", status, points);
        }
    }


    public static void main(String[] args) {
        final Collection<Task> tasks = Arrays.asList(
            new Task(Status.OPEN, 5),
            new Task(Status.OPEN, 13),
            new Task(Status.CLOSED, 8)
        );

        long timeStamp = System.currentTimeMillis();
        // Calculate total points of all active tasks using sum()
        final long totalPointsOfOpenTasks = tasks
                .stream()
                .filter( task -> task.getStatus() == Status.OPEN )
                .mapToInt( Task::getPoints )
                .sum();

        System.out.println( "Total points: " + totalPointsOfOpenTasks );
        System.out.println( "time: " + (System.currentTimeMillis() - timeStamp) );

        timeStamp = System.currentTimeMillis();
        final double totalPoints = tasks
                .stream()
                .filter( task -> task.getStatus() == Status.OPEN )
                .parallel()
                .map( task -> task.getPoints() ) // or map( Task::getPoints )
                .reduce( 0, Integer::sum );

        System.out.println( "Total points (all tasks): " + totalPoints );
        System.out.println( "time: " + (System.currentTimeMillis() - timeStamp) );

        timeStamp = System.currentTimeMillis();
        // Group tasks by their status
        final Map< Status, List< Task >> map = tasks
                .stream()
                .collect( Collectors.groupingBy( Task::getStatus ) );
        System.out.println( "group by " + map );
        System.out.println( "time: " + (System.currentTimeMillis() - timeStamp) );

        timeStamp = System.currentTimeMillis();
        // Calculate the weight of each tasks (as percent of total points)
        // 如何计算集合中每个任务的点数在集合中所占的比重，具体处理的代码如下：
        final Collection< String > result = tasks
                .stream()                                        // Stream< String >
                .mapToInt( Task::getPoints )                     // IntStream
                .asLongStream()                                  // LongStream
                .mapToDouble( points -> points / totalPoints )   // DoubleStream
                .boxed()                                         // Stream< Double >
                .mapToLong( weigth -> ( long )( weigth * 100 ) ) // LongStream
                .mapToObj( percentage -> percentage + "%" )      // Stream< String>
                .collect( Collectors.toList() );                 // List< String >

        System.out.println( result );
        System.out.println( "time: " + (System.currentTimeMillis() - timeStamp) );


        //stream可以操作文件的读取
        final Path path = new File( "/Users/hht/Desktop/untitled.html" ).toPath();
        try {
            try( Stream< String > lines = Files.lines( path, StandardCharsets.UTF_8 ) ) {
                lines.onClose( () -> System.out.println("Done!") ).forEach( System.out::println );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
