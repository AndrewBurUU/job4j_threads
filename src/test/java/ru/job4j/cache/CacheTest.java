package ru.job4j.cache;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CacheTest {

    @Test
    public void whenAdd() {
        Cache cache = new Cache();
        cache.add(new Base(1, 0));
        assertThat(cache.getCount()).isEqualTo(1);
    }

    @Test
    public void whenDelete() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        cache.add(base);
        cache.delete(base);
        assertThat(cache.getCount()).isEqualTo(0);
    }

    @Test
    public void whenUpdate() {
        Cache cache = new Cache();
        Base firstEdition = new Base(1, 1);
        firstEdition.setName("first");
        Base secondEdition = new Base(1, 2);
        cache.add(firstEdition);
        cache.update(firstEdition);
        assertThat(cache.getModelById(1).getVersion()).isEqualTo(2);
    }
}