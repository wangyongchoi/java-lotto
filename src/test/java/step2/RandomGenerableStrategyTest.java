package step2;

import static org.assertj.core.api.Assertions.assertThat;
import static step2.LottoGenerator.LOTTO_FIRST_NUMBER;
import static step2.LottoGenerator.LOTTO_LAST_NUMBER;
import static step2.LottoGenerator.LOTTO_SELECTION_COUNT;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class RandomGenerableStrategyTest {

    RandomGenerableStrategy randomGenerableStrategy;

    @BeforeEach
    void before() {
        this.randomGenerableStrategy = new RandomGenerableStrategy();
    }

    @Test
    public void makeSequentialNumbersTest() {
        //given&when
        List<Integer> sequentialNumbers = ReflectionTestUtils
            .invokeMethod(randomGenerableStrategy,
                "makeSequentialNumbers",
                LOTTO_FIRST_NUMBER,
                LOTTO_LAST_NUMBER);

        //then
        assertThat(sequentialNumbers).startsWith(LOTTO_FIRST_NUMBER);
        assertThat(sequentialNumbers).endsWith(LOTTO_LAST_NUMBER);
    }

    @Test
    void generate() {
        //given&when
        List<Integer> generatedNumbers = randomGenerableStrategy.generate(LOTTO_SELECTION_COUNT);

        //then
        assertThat(generatedNumbers).hasSize(6);
        assertThat(generatedNumbers)
            .allMatch(number -> number >= LOTTO_FIRST_NUMBER && number <= LOTTO_LAST_NUMBER);
        assertThat(generatedNumbers).isSorted();
    }
}