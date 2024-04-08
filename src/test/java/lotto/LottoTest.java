package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {
    @Test
    @DisplayName("로또 번호가 6개가 아닌 경우 예외를 발생한다.")
    void invalidSizeLottoNumber() {
        assertThatThrownBy(() -> new Lotto(Stream.of(1, 2, 3, 4, 5)
                .map(LottoNumber::of)
                .collect(Collectors.toList())))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 6개여야 합니다.");
    }

    @Test
    @DisplayName("로또 번호가 중복된 경우 예외를 발생한다.")
    void duplicateLottoNumber() {
        assertThatThrownBy(() -> new Lotto(Stream.of(1, 2, 3, 4, 5, 5)
                .map(LottoNumber::of)
                .collect(Collectors.toList())))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 중복되지 않아야 합니다.");
    }

    @Test
    @DisplayName("로또 번호가 1부터 45사이가 아닌 경우 예외를 발생한다.")
    void invalidRangeLottoNumber() {
        assertThatThrownBy(() -> new Lotto(Stream.of(1, 2, 3, 4, 5, 46)
                .map(LottoNumber::of)
                .collect(Collectors.toList())))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1부터 45사이여야 합니다.");
    }

    @Test
    @DisplayName("로또 보너스 번호가 1부터 45사이가 아닌 경우 예외를 발생한다.")
    void invalidRangeBonusNumber() {
        Lotto lotto = new Lotto(Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::of)
                .collect(Collectors.toList()));
        assertThatThrownBy(() -> new WinningLotto(lotto, LottoNumber.of(46)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1부터 45사이여야 합니다.");
    }

    @Test
    @DisplayName("로또 보너스 번호가 로또 번호와 중복된 경우 예외를 발생한다.")
    void duplicateBonusNumber() {
        Lotto lotto = new Lotto(Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::of)
                .collect(Collectors.toList()));
        assertThatThrownBy(() -> new WinningLotto(lotto, LottoNumber.of(6)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }
}
