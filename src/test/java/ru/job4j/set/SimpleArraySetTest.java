package ru.job4j.set;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class SimpleArraySetTest {

    @Test
    void whenAddNonNull() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
    }

    @Test
    void whenAddSomeElementsNonNull() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        assertThat(set.contains(1)).isFalse();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
        assertThat(set.contains(2)).isFalse();
        assertThat(set.add(2)).isTrue();
        assertThat(set.contains(2)).isTrue();
        assertThat(set.add(2)).isFalse();
        assertThat(set.contains(3)).isFalse();
        assertThat(set.add(3)).isTrue();
        assertThat(set.contains(3)).isTrue();
        assertThat(set.add(3)).isFalse();
    }

    @Test
    void whenAddNull() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        assertThat(set.add(null)).isTrue();
        assertThat(set.contains(null)).isTrue();
        assertThat(set.add(null)).isFalse();
    }

    @Test
    void whenAddManyElementsThenNoDuplicates() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        for (int i = 0; i < 100; i++) {
            assertThat(set.add(i)).isTrue();
            assertThat(set.contains(i)).isTrue();
        }
        for (int i = 0; i < 100; i++) {
            assertThat(set.add(i)).isFalse();
        }
    }

    @Test
    void whenContainsAllAddedElements() {
        SimpleSet<String> set = new SimpleArraySet<>();
        set.add("java");
        set.add("kotlin");
        set.add("groovy");
        assertThat(set.contains("java")).isTrue();
        assertThat(set.contains("kotlin")).isTrue();
        assertThat(set.contains("groovy")).isTrue();
        assertThat(set.contains("scala")).isFalse();
    }
}