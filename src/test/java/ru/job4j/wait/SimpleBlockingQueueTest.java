package ru.job4j.wait;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.assertThat;

@Disabled
public class SimpleBlockingQueueTest {
    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        int count = 5;
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(count);
        Thread producer = new Thread(
                () -> {
                    IntStream.range(0, count).forEach(
                            queue::offer
                    );
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer).isEqualTo(Arrays.asList(0, 1, 2, 3, 4));
    }
}