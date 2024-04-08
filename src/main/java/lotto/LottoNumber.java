package lotto;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

public class LottoNumber {
    private final int number;
    private static final int LOTTO_NUMBER_MIN = 1;
    private static final int LOTTO_NUMBER_MAX = 45;
    private static final Map<Integer, LottoNumber> LOTTO_NUMBER_POOL;

    static {
        Map<Integer, LottoNumber> tempMap = new HashMap<>();
        IntStream.rangeClosed(LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX)
                .forEach(i -> tempMap.put(i, new LottoNumber(i)));
        LOTTO_NUMBER_POOL = Collections.unmodifiableMap(tempMap);
    }

    private LottoNumber(int number) {
        isValidLottoNumber(number);
        this.number = number;
    }

    public static LottoNumber of(int number) {
        isValidLottoNumber(number);
        return LOTTO_NUMBER_POOL.get(number);
    }

    private static void isValidLottoNumber(int number) {
        if (number < LOTTO_NUMBER_MIN || number > LOTTO_NUMBER_MAX) {
            throw new IllegalArgumentException("로또 번호는 1부터 45사이여야 합니다.");
        }
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LottoNumber)) return false;
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
