package lotto;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class LottoNumbers {
    private static final int LOTTO_NUMBER_SIZE = 6;
    private static final int LOTTO_NUMBER_MIN = 1;
    private static final int LOTTO_NUMBER_MAX = 45;


    private final List<Integer> lottoNumbers;

    public LottoNumbers(List<Integer> lottoNumbers) {
        isValidLottoNumbers(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private static void isValidLottoNumbers(List<Integer> lottoNumbers) {
        isDuplicatedLottoNumber(lottoNumbers);
        isValidSizeLottoNumber(lottoNumbers);
        isValidBoundLottoNumber(lottoNumbers);
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

    private static void isValidBoundLottoNumber(List<Integer> lottoNumbers) {
        if (lottoNumbers.stream().anyMatch(number -> number < LOTTO_NUMBER_MIN || number > LOTTO_NUMBER_MAX)) {
            throw new IllegalArgumentException("로또 번호는 1부터 45사이여야 합니다.");
        }
    }

    public boolean contains(int number) {
        return lottoNumbers.contains(number);
    }

    public List<Integer> getLottoNumbersReadOnly() {
        return Collections.unmodifiableList(lottoNumbers);
    }
}
