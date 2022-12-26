package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    private int count;

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        int id = model.getId();
        Base stored = memory.get(id);
        if (stored.getVersion() != model.getVersion()) {
            throw new OptimisticException("Versions are not equal!");
        }
        return memory.computeIfPresent(id, (key, value) -> model) == null;
    }

    public void delete(Base model) {
        memory.remove(model.getId());
    }

    public int getCount() {
        return memory.size();
    }

    public Base getModelById(int id) {
        return memory.get(id);
    }
}