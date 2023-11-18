package tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatternConverter<I, O> {

    // need to reduce simplify method eventually
    public List<O> convert(List<I> input, List<Pattern<I, O>> patterns) {
        ArrayList<O> output = new ArrayList<>();

        for(int inputIndex = 0; inputIndex < input.size(); ) {
            Optional<ConvertResult<O>> possibleConvertResult = elementMatches(input, inputIndex, patterns);
            if(possibleConvertResult.isPresent()) {
                ConvertResult<O> convertResult = possibleConvertResult.get();
                output.add(convertResult.get());
                inputIndex += convertResult.getSize();
            } else {
                // no patterns match
                throw new IllegalStateException("Unexpected State");
            }
        }

        return output;
    }

    // probably a more rigid set of parameters could be defined here
    // too lazy to figure what those should be right now
    private Optional<ConvertResult<O>> elementMatches(List<I> input, int inputIndex, List<Pattern<I, O>> patterns) {
        for(Pattern<I, O> pattern: patterns) {
            Optional<ConvertResult<O>> possiblePattern = pattern.matches(input, inputIndex);
            if (possiblePattern.isPresent()) return possiblePattern;
        }

        return Optional.empty();
    }
}
