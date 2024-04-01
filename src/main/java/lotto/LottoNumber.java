package lotto;

import java.util.List;

public class LottoNumber {

    private final List<Integer> winningNumber;
    private final int bonusNumber;

    public LottoNumber(List<Integer> winningNumber, int bonusNumber) {
        this.winningNumber = winningNumber;
        this.bonusNumber = bonusNumber;
    }

    public List<Integer> getWinningNumber() {
        return winningNumber;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
