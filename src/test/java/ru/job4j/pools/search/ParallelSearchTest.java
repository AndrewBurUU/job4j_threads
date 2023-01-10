package ru.job4j.pools.search;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ParallelSearchTest {

    @Test
    void whenSearchLess11() {
        Object[] array = new Object[] {3, 4, 5, 2};
        assertThat(ParallelSearch.search(array, 5)).isEqualTo(2);
    }

    @Test
    void whenSearchMore10() {
        Object[] array = new Object[] {3, 4, 5, 2, 8, 7, 65, 43, 11, 12, 34, 56};
        assertThat(ParallelSearch.search(array, 5)).isEqualTo(2);
    }

    @Test
    void whenStringType() {
        Object[] array = new Object[] {"3", "4", "5", "2", "8",
                "7", "65", "43", "11", "12",
                "34", "56"};
        assertThat(ParallelSearch.search(array, "34")).isEqualTo(10);
    }

    @Test
    void whenNotFound() {
        Object[] array = new Object[] {"3", "4", "5", "2", "8",
                "7", "65", "43", "11", "12",
                "34", "56"};
        assertThat(ParallelSearch.search(array, "100")).isEqualTo(-1);
    }

    @Test
    void whenFindLastElement() {
        Object[] array = new Object[] {"3", "4", "5", "2", "8",
                "7", "65", "43", "11", "12",
                "34", "56"};
        assertThat(ParallelSearch.search(array, "56")).isEqualTo(11);
    }

}