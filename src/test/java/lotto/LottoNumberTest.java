package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 45})
    @DisplayName("로또 번호가 1부터 45사이인 경우 예외를 발생하지 않는다.")
    void validRangeLottoNumber(int number) {
        LottoNumber lottoNumber = LottoNumber.of(number);
        assertThat(lottoNumber.getNumber())
                .isEqualTo(number);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    @DisplayName("로또 번호가 1부터 45사이가 아닌 경우 예외를 발생한다.")
    void invalidRangeLottoNumber(int number) {
        assertThatThrownBy(() -> LottoNumber.of(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1부터 45사이여야 합니다.");
    }
}
