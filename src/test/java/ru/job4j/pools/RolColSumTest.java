package ru.job4j.pools;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

import static org.assertj.core.api.Assertions.*;

class RolColSumTest {

    @Test
    void whenSequence() {
        int[][] array = {{5, 1, 6}, {8, 2, 3}, {4, 1, 0}};
        RolColSum.Sums[] res = RolColSum.sum(array);
        assertThat(res[0].getRowSum()).isEqualTo(12);
        assertThat(res[1].getRowSum()).isEqualTo(13);
        assertThat(res[2].getRowSum()).isEqualTo(5);
        assertThat(res[0].getColSum()).isEqualTo(17);
        assertThat(res[1].getColSum()).isEqualTo(4);
        assertThat(res[2].getColSum()).isEqualTo(9);
    }

    @Test
    void whenAsync() throws ExecutionException, InterruptedException {
        int[][] array = {{5, 1, 6}, {8, 2, 3}, {4, 1, 0}};
        RolColSum.Sums[] res = RolColSum.asyncSum(array);
        assertThat(res[0].getRowSum()).isEqualTo(12);
        assertThat(res[1].getRowSum()).isEqualTo(13);
        assertThat(res[2].getRowSum()).isEqualTo(5);
        assertThat(res[0].getColSum()).isEqualTo(17);
        assertThat(res[1].getColSum()).isEqualTo(4);
        assertThat(res[2].getColSum()).isEqualTo(9);
    }

}