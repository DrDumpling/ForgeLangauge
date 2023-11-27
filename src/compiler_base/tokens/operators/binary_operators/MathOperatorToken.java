package compiler_base.tokens.operators.binary_operators;

import compiler_base.tokens.ProgramToken;
import tools.ConvertResult;
import tools.Pattern;

import java.util.Optional;

public enum MathOperatorToken implements ProgramToken {
    PLUS,
    MINUS,
    MULTIPLY,
    DIVIDE,
    MODULUS;

    public static Pattern<Character, ProgramToken> getPattern() {
        return (input, i) -> {
            if (input.get(i) == '+')
                return Optional.of(ConvertResult.of(MathOperatorToken.PLUS));
            if (input.get(i) == '-')
                return Optional.of(ConvertResult.of(MathOperatorToken.MINUS));
            if (input.get(i) == '*')
                return Optional.of(ConvertResult.of(MathOperatorToken.MULTIPLY));
            if (input.get(i) == '/')
                return Optional.of(ConvertResult.of(MathOperatorToken.DIVIDE));
            if (input.get(i) == '%')
                return Optional.of(ConvertResult.of(MathOperatorToken.MODULUS));

            return Optional.empty();
        };
    }
}
