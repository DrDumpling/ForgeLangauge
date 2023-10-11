package tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// bare skeleton for how this should work, VERY much WIP
public class PatternConverter<I, O> {
    public List<O> convert(List<I> input, List<Pattern<I, O>> patterns) {
        ArrayList<O> output = new ArrayList<>();
        for(I inputElement: input) {
            Optional<BasicConvertResult<O>> possibleConvertResult = elementMatches(inputElement, patterns);
            if(possibleConvertResult.isPresent()) {
                BasicConvertResult<O> convertResult = possibleConvertResult.get();
                if(convertResult.isResult()) output.add(convertResult.get());
            } else {
                // no patterns match, still need to figure out how to handle this case
                throw new IllegalStateException("Unexpected State");
            }
        }
        return output;
    }

    private Optional<BasicConvertResult<O>> elementMatches(I inputElement, List<Pattern<I, O>> patterns) {
        for(Pattern<I, O> pattern: patterns) {
            Optional<BasicConvertResult<O>> possiblePattern = pattern.matches(inputElement);
            if (possiblePattern.isPresent()) {
                return possiblePattern;
            }
        }

        return Optional.empty();
    }
}
