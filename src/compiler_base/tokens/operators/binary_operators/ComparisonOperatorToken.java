package compiler_base.tokens.operators.binary_operators;

import compiler_base.tokens.ProgramToken;
import tools.ConvertResult;
import tools.Pattern;

import java.util.Optional;

public enum ComparisonOperatorToken implements ProgramToken {
    EQUALS,
    LESS_THAN,
    GREATER_THAN,
    LESS_THAN_OR_EQUAL,
    GREATER_THAN_OR_EQUAL,
    NOT_EQUAL;

    public static Pattern<Character, ProgramToken> getPattern() {
        return (input, i) -> {
            if (input.get(i) == '!' && input.get(i + 1) == '=')
                return Optional.of(ConvertResult.of(ComparisonOperatorToken.NOT_EQUAL));
            if (input.get(i) == '<' && input.get(i + 1) == '=')
                return Optional.of(ConvertResult.of(ComparisonOperatorToken.LESS_THAN_OR_EQUAL));
            if (input.get(i) == '>' && input.get(i + 1) == '=')
                return Optional.of(ConvertResult.of(ComparisonOperatorToken.GREATER_THAN_OR_EQUAL));
            if (input.get(i) == '=' && input.get(i + 1) == '=')
                return Optional.of(ConvertResult.of(ComparisonOperatorToken.EQUALS));
            if (input.get(i) == '<')
                return Optional.of(ConvertResult.of(ComparisonOperatorToken.LESS_THAN));
            if (input.get(i) == '>')
                return Optional.of(ConvertResult.of(ComparisonOperatorToken.GREATER_THAN));
            return Optional.empty();
        };
    }
}
