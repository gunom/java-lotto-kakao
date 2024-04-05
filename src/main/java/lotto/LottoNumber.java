package lotto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber {
    private final int number;
    private static final int LOTTO_NUMBER_MIN = 1;
    private static final int LOTTO_NUMBER_MAX = 45;

    public LottoNumber(int number) {
        isValidLottoNumber(number);
        this.number = number;
    }

    private void isValidLottoNumber(int number) {
        if (number < LOTTO_NUMBER_MIN || number > LOTTO_NUMBER_MAX) {
            throw new IllegalArgumentException("로또 번호는 1부터 45사이여야 합니다.");
        }
    }

    public static List<LottoNumber> createLottoPool() {
        return IntStream.range(LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX + 1)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
    }

    public int getNumber() {
        return number;
    }
}
