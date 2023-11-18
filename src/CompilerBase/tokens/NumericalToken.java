package CompilerBase.tokens;

import tools.ConvertResult;
import tools.Pattern;

import java.util.Optional;

public final class NumericalToken implements ProgramToken {
    final Long heldValue;
    public NumericalToken(Long input) {
        heldValue = input;
    }

    @Override
    public String toString() {
        return "NumericalToken[" + heldValue + "]";
    }

    public static Pattern<Character, ProgramToken> getPattern() {
        return (input, i) -> {
            if (Character.isDigit(input.get(i))) {
                StringBuilder numericalString = new StringBuilder();
                numericalString.append(input.get(i));

                while(Character.isDigit(input.get(i + 1))) {
                    numericalString.append(input.get(i + 1));
                    i += 1;
                }
                return Optional.of(
                        ConvertResult.of(
                                new NumericalToken(Long.parseLong(numericalString.toString())),
                                numericalString.length()
                        )
                );
            }
            return Optional.empty();
        };
    }
}
