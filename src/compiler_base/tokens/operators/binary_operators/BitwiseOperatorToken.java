package compiler_base.tokens.operators.binary_operators;

import compiler_base.tokens.ProgramToken;
import tools.ConvertResult;
import tools.Pattern;

import java.util.Optional;

public enum BitwiseOperatorToken implements ProgramToken {
    SHIFT_LEFT,
    SHIFT_RIGHT,
    AND,
    OR,
    XOR;

    public static Pattern<Character, ProgramToken> getPattern() {
        return (input, i) -> {
            if (input.get(i) == '<' && input.get(i + 1) == '<')
                return Optional.of(ConvertResult.of(BitwiseOperatorToken.SHIFT_LEFT, 2));
            if (input.get(i) == '>' && input.get(i + 1) == '>')
                return Optional.of(ConvertResult.of(BitwiseOperatorToken.SHIFT_RIGHT, 2));
            if (input.get(i) == '&')
                return Optional.of(ConvertResult.of(BitwiseOperatorToken.AND));
            if (input.get(i) == '|')
                return Optional.of(ConvertResult.of(BitwiseOperatorToken.OR));
            if (input.get(i) == '^')
                return Optional.of(ConvertResult.of(BitwiseOperatorToken.XOR));
            return Optional.empty();
        };
    }
}
