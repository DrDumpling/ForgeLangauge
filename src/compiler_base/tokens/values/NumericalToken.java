package compiler_base.tokens.values;

import compiler_base.tokens.ProgramToken;
import tools.ConvertResult;
import tools.Pattern;

import java.util.Optional;

public final class NumericalToken implements ProgramToken {
    public final int heldValue;
    public NumericalToken(Integer input) {
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
                                new NumericalToken(Integer.parseInt(numericalString.toString())),
                                numericalString.length()
                        )
                );
            }
            return Optional.empty();
        };
    }
}
