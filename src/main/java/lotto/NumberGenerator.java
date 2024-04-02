package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumberGenerator {

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
