package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTest {

    private static final List<Integer> winningNumber = List.of(1, 2, 3, 4, 5, 6);
    private static final int bonusNumber = 7;

    @Test
    @DisplayName("로또 번호가 1등과 일치하는지 확인한다.")
    void matchWithWinningNumberTest() {
        WinningNumber lottoNumber = new WinningNumber(
                winningNumber, bonusNumber
        );

        Lotto lotto = new Lotto(winningNumber);
        LottoResult lottoResult = lotto.matchNumber(lottoNumber);
        assertThat(lottoResult).isEqualTo(LottoResult.FIRST_PRIZE);
    }

    @Test
    @DisplayName("로또 번호가 2등과 일치하는지 확인한다.")
    void matchWithFiveMatch() {
        WinningNumber lottoNumber = new WinningNumber(
                winningNumber, bonusNumber
        );

        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        LottoResult lottoResult = lotto.matchNumber(lottoNumber);
        assertThat(lottoResult).isEqualTo(LottoResult.SECOND_PRIZE);
    }

    @Test
    @DisplayName("로또 번호가 3등과 일치하는지 확인한다.")
    void matchWithFourMatch() {
        WinningNumber lottoNumber = new WinningNumber(
                winningNumber, bonusNumber
        );

        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 8));
        LottoResult lottoResult = lotto.matchNumber(lottoNumber);
        assertThat(lottoResult).isEqualTo(LottoResult.THIRD_PRIZE);
    }

    @Test
    @DisplayName("로또 번호가 4등과 일치하는지 확인한다.")
    void matchWithThreeMatch() {
        WinningNumber lottoNumber = new WinningNumber(
                winningNumber, bonusNumber
        );

        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 7, 8));
        LottoResult lottoResult = lotto.matchNumber(lottoNumber);
        assertThat(lottoResult).isEqualTo(LottoResult.FORTH_PRIZE);
    }

    @Test
    @DisplayName("로또 번호가 5등과 일치하는지 확인한다.")
    void matchWithTwoMatch() {
        WinningNumber lottoNumber = new WinningNumber(
                winningNumber, bonusNumber
        );

        Lotto lotto = new Lotto(List.of(1, 2, 3, 8, 9, 10));
        LottoResult lottoResult = lotto.matchNumber(lottoNumber);
        assertThat(lottoResult).isEqualTo(LottoResult.FIFTH_PRIZE);
    }

    @Test
    @DisplayName("로또 번호가 꽝인지 확인한다.")
    void matchLooser() {
        WinningNumber lottoNumber = new WinningNumber(
                winningNumber, bonusNumber
        );

        Lotto lotto = new Lotto(List.of(45, 44, 43, 42, 41, 40));
        LottoResult lottoResult = lotto.matchNumber(lottoNumber);
        assertThat(lottoResult).isEqualTo(LottoResult.LOOSE);

    }
}
