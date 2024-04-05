package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoGameTest {

    @Test
    @DisplayName("로또 금액의 1/1000만큼 로또를 구매한다.")
    void buyLottoTest() {
        int budget = 5_000;
        List<List<Integer>> manualLottos = List.of();
        LottoGame game = new LottoGame(budget, manualLottos, new NumberGenerator());
        assertThat(game.getLottos().size()).isEqualTo(5);
    }

    @Test
    @DisplayName("로또 당첨 번호와 비교하여 결과를 반환한다.")
    void calculateResult() {
        int budget = 2_000;
        List<List<Integer>> manualLottos = List.of();
        NumberGenerator fakeGenerator = new FakeNumberGenerator(List.of(
                List.of(10, 20, 30, 1, 2, 3),
                List.of(7, 8, 9, 10, 11, 12)
        ));
        LottoGame game = new LottoGame(budget, manualLottos, fakeGenerator);
        WinningLotto winningLotto = new WinningLotto(
                new Lotto(Stream.of(1, 2, 3, 4, 5, 6)
                        .map(LottoNumber::of)
                        .collect(Collectors.toList())), LottoNumber.of(7));

        GameResult results = game.getGameResult(winningLotto);
        assertThat(results.getResultCount(LottoResult.FIFTH_PRIZE)).isEqualTo(1);
        assertThat(results.getProfitRate()).isEqualTo(2.5);
    }

    @Test
    @DisplayName("수동 로또를 구매한다.")
    void buyManualLottoTest() {
        int budget = 5_000;
        List<List<Integer>> manualLottos = List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(7, 8, 9, 10, 11, 12)
        );
        LottoGame game = new LottoGame(budget, manualLottos, new NumberGenerator());
        assertThat(game.getManualLottoCount()).isEqualTo(2);
    }

    @Test
    @DisplayName("투입 금액보다 많은 수동 로또를 구매하면 예외가 발생한다.")
    void buyManualLottoOverBudgetTest() {
        int budget = 1_000;
        List<List<Integer>> manualLottos = List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(7, 8, 9, 10, 11, 12),
                List.of(13, 14, 15, 16, 17, 18)
        );
        assertThatThrownBy(() -> new LottoGame(budget, manualLottos, new NumberGenerator()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 금액이 부족합니다.");
    }
}
