package lotto;

import java.util.List;

public class WinningNumber {

    private final Lotto lottoNumbers;
    private final int bonusNumber;

    public WinningNumber(Lotto lottoNumbers, int bonusNumber) {
        isValidBonusNumbers(lottoNumbers, bonusNumber);
        this.lottoNumbers = lottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void isValidBonusNumbers(Lotto lottoNumbers, int bonusNumber) {
        isDuplicateBonusNumber(lottoNumbers, bonusNumber);
        isValidBonusNumber(bonusNumber);
    }

    private static void isDuplicateBonusNumber(Lotto lottoNumbers, int bonusNumber) {
        if (lottoNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    private static void isValidBonusNumber(int bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("보너스 번호는 1부터 45사이여야 합니다.");
        }
    }

    public WinningNumber(List<Integer> lottoNumbers, int bonusNumber) {
        this(new Lotto(lottoNumbers), bonusNumber);
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber.getNumber());
    }
}
