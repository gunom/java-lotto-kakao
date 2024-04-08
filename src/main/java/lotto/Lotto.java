package lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {

    private final Set<LottoNumber> lottoNumbers;
    private static final int LOTTO_NUMBER_SIZE = 6;

    public Lotto(List<LottoNumber> lottoNumbers) {
        isValidLottoNumbers(lottoNumbers);
        this.lottoNumbers = new HashSet<>(lottoNumbers);
    }

    private static void isValidLottoNumbers(List<LottoNumber> lottoNumbers) {
        isDuplicatedLottoNumber(lottoNumbers);
        isValidSizeLottoNumber(lottoNumbers);
    }

    private static void isDuplicatedLottoNumber(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != new HashSet<>(lottoNumbers).size()) {
            throw new IllegalArgumentException("로또 번호는 중복되지 않아야 합니다.");
        }
    }

    private static void isValidSizeLottoNumber(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    public int matchNumber(Lotto winningLotto) {
        return (int) lottoNumbers.stream().filter(winningLotto::contains).count();
    }

    public boolean hasBonusNumber(LottoNumber bonusNumber) {
        return contains(bonusNumber);
    }

    private boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers.stream().map(LottoNumber::getNumber).collect(Collectors.toList());
    }
}
