package lotto;

import java.util.List;
import java.util.stream.Collectors;

public class LottoNumbers {

    private static final List<LottoNumber> LOTTO_NUMBER_POOL = LottoNumber.createLottoPool();

    public List<LottoNumber> getLottoNumbers(List<Integer> lottoNumbers) {
        return lottoNumbers.stream()
                .map(number -> LOTTO_NUMBER_POOL.get(number - 1))
                .collect(Collectors.toList());
    }

    public LottoNumber getLottoNumber(int number) {
        return LOTTO_NUMBER_POOL.get(number - 1);
    }
}
