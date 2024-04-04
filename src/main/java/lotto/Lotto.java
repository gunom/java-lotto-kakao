package lotto;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {

    private final List<LottoNumber> lottoNumbers;
    private static final int LOTTO_NUMBER_SIZE = 6;

    public Lotto(List<Integer> lottoNumbers) {
        isValidLottoNumbers(lottoNumbers);
        this.lottoNumbers = lottoNumbers.stream().map(LottoNumber::new).collect(Collectors.toList());
    }

    private static void isValidLottoNumbers(List<Integer> lottoNumbers) {
        isDuplicatedLottoNumber(lottoNumbers);
        isValidSizeLottoNumber(lottoNumbers);
    }

    private static void isDuplicatedLottoNumber(List<Integer> lottoNumbers) {
        if(new HashSet<>(lottoNumbers).size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException("로또 번호는 중복되지 않아야 합니다.");
        }
    }

    private static void isValidSizeLottoNumber(List<Integer> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    public LottoResult matchNumber(WinningNumber winningNumber) {
        int winCount = (int) lottoNumbers.stream().filter(winningNumber::contains).count();
        boolean hasBonus = contains(winningNumber.getBonusNumber());

        return LottoResult.getResult(winCount, hasBonus);
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers.stream().map(LottoNumber::getNumber).collect(Collectors.toUnmodifiableList());
    }

    public boolean contains(int number) {
        return lottoNumbers.stream().anyMatch(lottoNumber -> lottoNumber.getNumber() == number);
    }
}
