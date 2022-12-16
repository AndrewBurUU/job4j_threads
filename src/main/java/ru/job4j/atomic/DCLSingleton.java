package ru.job4j.atomic;

public final class DCLSingleton {

    private static volatile DCLSingleton inst;

    public static DCLSingleton instOf() {
        DCLSingleton local = inst;
        if (local == null) {
            synchronized (DCLSingleton.class) {
                local = inst;
                if (local == null) {
                    local = new DCLSingleton();
                    inst = local;
                }
            }
        }
        return local;
    }

    private DCLSingleton() {
    }
}
