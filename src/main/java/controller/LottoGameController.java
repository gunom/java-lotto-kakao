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
        int manualLottoCount = LottoGameInputView.getManualLottoCount(budget);
        List<List<Integer>> manualLottoNumbers = LottoGameInputView.getManualLottoNumbers(manualLottoCount);
        LottoGame lottoGame = new LottoGame(budget, manualLottoNumbers, new NumberGenerator());
        displayPurchasedLottos(lottoGame);
        return lottoGame;
    }

    private static void displayPurchasedLottos(LottoGame lottoGame) {
        List<Lotto> lottos = lottoGame.getLottos();
        LottoGameOutputView.displayNumberOfLottos(lottoGame.getManualLottoCount(), lottoGame.getAutoLottoCount());
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
