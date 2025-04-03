package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void whenParseEmptyNamesThenThrownException() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("is empty");
    }

    @Test
    void whenParseNameWithoutKeyThenThrownException() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"key=value", "=value"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("=value")
                .hasMessageContaining("a key");
    }

    @Test
    void whenParseNameWithoutValueThenThrownException() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"key=", "key=value"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("key=")
                .hasMessageContaining("a value");
    }

    @Test
    void whenParseNameWithoutEqualSignThenThrowException() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"key=value", "keyvalue"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("keyvalue")
                .hasMessageContaining("the symbol '='");
    }
}