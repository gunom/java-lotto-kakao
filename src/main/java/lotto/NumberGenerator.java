package lotto;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NumberGenerator {

//    private static final int MIN_LOTTO_NUMBER = 1;
//    private static final int MAX_LOTTO_NUMBER = 45;
//    private static final List<Integer> CANDIDATE_NUMBERS = IntStream.range(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER + 1).boxed().collect(Collectors.toList());

    public List<Integer> generateNumbers(List<Integer> candidateNumbers, int size) {
        if (size < 0) {
            throw new IllegalArgumentException("unreachable");
        }
        return shuffleNumbers(candidateNumbers).subList(0, size);
    }

    protected List<Integer> shuffleNumbers(List<Integer> candidateNumbers) {
        List<Integer> shuffledNumbers = new ArrayList<>(candidateNumbers);
        Collections.shuffle(shuffledNumbers);
        return shuffledNumbers;
    }

}
