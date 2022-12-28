package ru.job4j.pool.emailnotification;

import java.util.concurrent.*;

public class EmailNotification {

    private static final String SUBJECT_TEMPLATE = "Notification %s to email %s";
    private static final String BODY_TEMPLATE = "Add a new event to %s";

    private ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    private void emailTo(User user) {
        pool.submit(
                () -> {
                    send(String.format(SUBJECT_TEMPLATE, user.name(), user.email()),
                            String.format(BODY_TEMPLATE, user.name()),
                            user.email()
                            );
                }
        );
    }

    private void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(String subject, String body, String email) {

    }
}
