package ru.job4j.wait;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class SimpleBlockingQueueTest {

    @Test
    void whenProducerOffer() throws InterruptedException {
        int expected = 1;
        int res = 0;
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<Integer>();
        Thread producer = new Thread(
                () -> {
                    simpleBlockingQueue.offer(expected);
                },
                "Producer"
        );
        Thread customer = new Thread(
                () -> {
                    /**res = simpleBlockingQueue.poll();*/
                    System.out.println(res);
                },
                "Customer"
        );
        producer.start();
        customer.start();
        producer.join();
        customer.join();
        assertThat(res).isEqualTo(expected);
    }

}