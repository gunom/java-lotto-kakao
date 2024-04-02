package lotto;

import java.util.List;

public class WinningNumber {

    private final List<Integer> lottoNumbers;
    private final int bonusNumber;

    public WinningNumber(List<Integer> lottoNumbers, int bonusNumber) {
        this.lottoNumbers = lottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
