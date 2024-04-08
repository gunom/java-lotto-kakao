package lotto;

public class WinningLotto {

    private final Lotto winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningNumbers, LottoNumber bonusNumber) {
        isValidBonusNumbers(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public LottoResult matchNumber(Lotto lottoNumbers) {
        int matchCount = winningNumbers.matchNumber(lottoNumbers);
        boolean hasBonus = lottoNumbers.hasBonusNumber(this.bonusNumber);
        return LottoResult.getResult(matchCount, hasBonus);
    }

    private void isValidBonusNumbers(Lotto lottoNumbers, LottoNumber bonusNumber) {
        isDuplicateBonusNumber(lottoNumbers, bonusNumber);
    }

    private static void isDuplicateBonusNumber(Lotto winningNumbers, LottoNumber bonusNumber) {
        if (winningNumbers.hasBonusNumber(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }
}
