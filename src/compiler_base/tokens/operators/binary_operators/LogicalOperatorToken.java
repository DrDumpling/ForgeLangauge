package compiler_base.tokens.operators.binary_operators;

import compiler_base.tokens.ProgramToken;
import tools.ConvertResult;
import tools.Pattern;

import java.util.Optional;

public enum LogicalOperatorToken implements ProgramToken {
    OR,
    AND;

    public static Pattern<Character, ProgramToken> getPattern() {
        return (input, i) -> {
            if (input.get(i) == '|' && input.get(i + 1) == '|')
                return Optional.of(ConvertResult.of(LogicalOperatorToken.OR));
            if (input.get(i) == '&' && input.get(i + 1) == '&')
                return Optional.of(ConvertResult.of(LogicalOperatorToken.AND));
            return Optional.empty();
        };
    }
}
