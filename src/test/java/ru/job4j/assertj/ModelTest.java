package ru.job4j.assertj;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ModelTest {

    @Test
    void checkBoolean() {
        Model model = new Model(1, 5.255d, "name", true);
        boolean result = model.isCondition();
        assertThat(result).isTrue();
    }

    @Test
    void checkStringChain() {
        Model model = new Model(1, 5.255d, "I am learning Java", true);
        String result = model.getLine();
        assertThat(result).isNotNull()
                .isNotEmpty()
                .isNotBlank()
                .containsIgnoringCase("java")
                .contains("am", "Java")
                .doesNotContain("javascript")
                .startsWith("I am")
                .startsWithIgnoringCase("i")
                .endsWith("Java")
                .isEqualTo("I am learning Java");
    }

    @Test
    void checkInt() {
        Model model = new Model(2, 5.2d, "name", true);
        int result = model.getTop();
        assertThat(result).isNotZero()
                .isPositive()
                .isEven()
                .isGreaterThan(1)
                .isLessThan(3)
                .isEqualTo(2);
    }
}