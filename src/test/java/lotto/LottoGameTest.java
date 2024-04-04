package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGameTest {

    @Test
    @DisplayName("로또 금액의 1/1000만큼 로또를 구매한다.")
    void buyLottoTest() {
        int budget = 5_000;
        LottoGame game = new LottoGame(budget, new NumberGenerator());
        assertThat(game.getLottos().size()).isEqualTo(5);
    }

    @Test
    @DisplayName("로또 당첨 번호와 비교하여 결과를 반환한다.")
    void calculateResult() {
        int budget = 2_000;
        NumberGenerator fakeGenerator = new FakeNumberGenerator(List.of(
                List.of(10, 20, 30, 1, 2, 3),
                List.of(7, 8, 9, 10, 11, 12)
        ));
        LottoGame game = new LottoGame(budget, fakeGenerator);
        WinningNumber winningNumber = new WinningNumber(List.of(1, 2, 3, 4, 5, 6), 7);
        GameResult results = game.getGameResult(winningNumber);

        assertThat(results.getResultCount(LottoResult.FIFTH_PRIZE)).isEqualTo(1);
        assertThat(results.getProfitRate()).isEqualTo(2.5);
    }
}
