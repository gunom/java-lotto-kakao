package lotto;

import java.util.List;

public class Lotto {

    private final List<Integer> lottoNums;
    public Lotto(List<Integer> lottoNums) {
        this.lottoNums = lottoNums;
    }

    public LottoResult matchNumber(WinningNumber winningNumber) {
        int winCount = (int) winningNumber.getLottoNumbers().stream().filter(lottoNums::contains).count();
        boolean hasBonus = lottoNums.contains(winningNumber.getBonusNumber());

        return LottoResult.getResult(winCount, hasBonus);
    }
}
