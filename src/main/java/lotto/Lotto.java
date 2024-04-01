package lotto;

import java.util.List;

public class Lotto {

    private final List<Integer> lottoNums;
    public Lotto(List<Integer> lottoNums) {
        this.lottoNums = lottoNums;
    }

    public LottoResult matchNumber(LottoNumber lottoNumber) {
        int winCount = (int) lottoNumber.getWinningNumber().stream().filter(lottoNums::contains).count();
        boolean hasBonus = lottoNums.contains(lottoNumber.getBonusNumber());

        return LottoResult.getResult(winCount, hasBonus);
    }
}
