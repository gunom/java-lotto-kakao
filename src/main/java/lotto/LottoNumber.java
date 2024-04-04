package lotto;

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

    public int getNumber() {
        return number;
    }
}
