package lotto;

import java.util.List;

public class Lotto {

    private final LottoNumbers lottoNumbers;

    public Lotto(LottoNumbers lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public Lotto(List<Integer> lottoNumbers) {
        this(new LottoNumbers(lottoNumbers));
    }

    public LottoResult matchNumber(WinningNumber winningNumber) {
        int winCount = (int) winningNumber.getLottoNumbers().stream().filter(lottoNumbers::contains).count();
        boolean hasBonus = lottoNumbers.contains(winningNumber.getBonusNumber());

        return LottoResult.getResult(winCount, hasBonus);
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers.getLottoNumbersReadOnly();
    }
}
