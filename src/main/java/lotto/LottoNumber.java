package lotto;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class LottoNumber {
    private final int number;
    private static final int LOTTO_NUMBER_MIN = 1;
    private static final int LOTTO_NUMBER_MAX = 45;
    private static final Map<Integer, LottoNumber> LOTTO_NUMBER_POOL = new HashMap<>();

    static {
         IntStream.range(LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX + 1)
                 .forEach(i -> LOTTO_NUMBER_POOL.put(i, new LottoNumber(i)));
    }

    private LottoNumber(int number) {
        isValidLottoNumber(number);
        this.number = number;
    }

    public static LottoNumber of(int number) {
        return LOTTO_NUMBER_POOL.get(number);
    }

    private void isValidLottoNumber(int number) {
        if (number < LOTTO_NUMBER_MIN || number > LOTTO_NUMBER_MAX) {
            throw new IllegalArgumentException("로또 번호는 1부터 45사이여야 합니다.");
        }
    }

    public int getNumber() {
        return number;
    }
}
