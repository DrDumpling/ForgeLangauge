package compiler_base.tokens.operators;

import compiler_base.tokens.ProgramToken;
import tools.ConvertResult;
import tools.Pattern;

import java.util.Optional;

public enum UnfixedOperator implements ProgramToken {
    ASSIGNMENT_OPERATOR,
    OPENING_BRACE,
    CLOSING_BRACE,
    OPENING_BRACKET,
    CLOSING_BRACKET;

    public static Pattern<Character, ProgramToken> getPattern() {
        return (input, i) -> {
            if (input.get(i) == '=')
                return Optional.of(ConvertResult.of(ASSIGNMENT_OPERATOR));
            if (input.get(i) == '{')
                return Optional.of(ConvertResult.of(OPENING_BRACE));
            if (input.get(i) == '}')
                return Optional.of(ConvertResult.of(CLOSING_BRACE));
            if (input.get(i) == '[')
                return Optional.of(ConvertResult.of(OPENING_BRACKET));
            if (input.get(i) == ']')
                return Optional.of(ConvertResult.of(CLOSING_BRACKET));
            return Optional.empty();
        };
    }
}
