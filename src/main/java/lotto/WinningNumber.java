package lotto;

import java.util.List;

public class WinningNumber {

    private final Lotto lottoNumbers;
    private final int bonusNumber;

    public WinningNumber(Lotto lottoNumbers, int bonusNumber) {
        this.lottoNumbers = lottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    public WinningNumber(List<Integer> lottoNumbers, int bonusNumber) {
        this(new Lotto(lottoNumbers), bonusNumber);
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers.getLottoNumbers();
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
