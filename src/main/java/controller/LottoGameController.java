package controller;

import lotto.*;
import view.LottoGameInputView;
import view.LottoGameOutputView;

import java.util.List;

public class LottoGameController {

    public  void startGame() {
        LottoGame lottoGame = setupLottoGame();
        GameResult gameResult = matchWinningNumber(lottoGame);
        displayResult(gameResult);
    }

    private static LottoGame setupLottoGame() {
        int budget = LottoGameInputView.getBudget();
        LottoGame lottoGame = new LottoGame(budget, new NumberGenerator());
        displayPurchasedLottos(lottoGame);
        return lottoGame;
    }

    private static void displayPurchasedLottos(LottoGame lottoGame) {
        List<Lotto> lottos = lottoGame.getLottos();
        LottoGameOutputView.displayNumberOfLottos(lottos.size());
        lottos.forEach(LottoGameOutputView::displayLotto);
    }

    private static GameResult matchWinningNumber(LottoGame lottoGame) {
        WinningLotto winningLotto = getWinningNumber(lottoGame);
        return lottoGame.getGameResult(winningLotto);
    }

    private static WinningLotto getWinningNumber(LottoGame lottoGame) {
        List<Integer> winningLottoNumber = LottoGameInputView.getWinningNumber();
        int bonusNumber = LottoGameInputView.getBonusNumber(winningLottoNumber);
        return lottoGame.generateWinningLotto(winningLottoNumber, bonusNumber);
    }

    private static void displayResult(GameResult gameResult) {
        LottoGameOutputView.displayResultHeader();
        LottoGameOutputView.displayResultLotto(gameResult);
        LottoGameOutputView.displayResultProfitRate(gameResult);
    }
}
