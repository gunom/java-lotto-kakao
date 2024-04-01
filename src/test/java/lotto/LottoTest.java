package lotto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTest {

    @Test
    void matchWithWinningNumberTest() {
        List<Integer> winningNumber = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        WinningNumber lottoNumber = new WinningNumber(
                winningNumber, bonusNumber
        );

        Lotto lotto = new Lotto(winningNumber);
        LottoResult lottoResult = lotto.matchNumber(lottoNumber);
        assertThat(lottoResult).isEqualTo(LottoResult.SIX_MATCH);
    }

    @Test
    void matchWithFiveMatch() {
        List<Integer> winningNumber = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        WinningNumber lottoNumber = new WinningNumber(
                winningNumber, bonusNumber
        );

        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        LottoResult lottoResult = lotto.matchNumber(lottoNumber);
        assertThat(lottoResult).isEqualTo(LottoResult.FIVE_AND_BONUS_MATCH);
    }

    @Test
    void matchLooser() {
        List<Integer> winningNumber = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        WinningNumber lottoNumber = new WinningNumber(
                winningNumber, bonusNumber
        );

        Lotto lotto = new Lotto(List.of(45, 44, 43, 42, 41, 40));
        LottoResult lottoResult = lotto.matchNumber(lottoNumber);
        assertThat(lottoResult).isEqualTo(LottoResult.BLANK_MATCH);

    }
}
