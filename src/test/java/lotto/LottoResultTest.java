package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {

    @Test
    @DisplayName("로또 결과가 1등인지 확인한다.")
    void firstPrize() {
        LottoResult lottoResult = LottoResult.getResult(6, false);
        assertThat(lottoResult).isEqualTo(LottoResult.FIRST_PRIZE);
    }

    @Test
    @DisplayName("로또 결과가 2등인지 확인한다.")
    void secondPrize() {
        LottoResult lottoResult = LottoResult.getResult(5, true);
        assertThat(lottoResult).isEqualTo(LottoResult.SECOND_PRIZE);
    }

    @Test
    @DisplayName("로또 결과가 3등인지 확인한다.")
    void thirdPrize() {
        LottoResult lottoResult = LottoResult.getResult(5, false);
        assertThat(lottoResult).isEqualTo(LottoResult.THIRD_PRIZE);
    }

    @Test
    @DisplayName("로또 결과가 4등인지 확인한다.")
    void forthPrize() {
        LottoResult lottoResult = LottoResult.getResult(4, false);
        assertThat(lottoResult).isEqualTo(LottoResult.FORTH_PRIZE);
    }

    @Test
    @DisplayName("로또 결과가 5등인지 확인한다.")
    void fifthPrize() {
        LottoResult lottoResult = LottoResult.getResult(3, false);
        assertThat(lottoResult).isEqualTo(LottoResult.FIFTH_PRIZE);
    }

    @Test
    @DisplayName("로또 결과가 꽝인지 확인한다.")
    void loose() {
        LottoResult lottoResult = LottoResult.getResult(2, false);
        assertThat(lottoResult).isEqualTo(LottoResult.LOOSE);
    }
}
