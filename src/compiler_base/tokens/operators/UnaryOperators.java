package compiler_base.tokens.operators;

import compiler_base.tokens.ProgramToken;
import tools.ConvertResult;
import tools.Pattern;

import java.util.Optional;

public enum UnaryOperators implements ProgramToken {
    UNARY_NOT,
    UNARY_BITWISE_NOT;

    public static Pattern<Character, ProgramToken> getPattern() {
        return (input, i) -> {
            if (input.get(i) == '!')
                return Optional.of(ConvertResult.of(UnaryOperators.UNARY_NOT));
            if (input.get(i) == '~')
                return Optional.of(ConvertResult.of(UnaryOperators.UNARY_BITWISE_NOT));
            return Optional.empty();
        };
    }
}
