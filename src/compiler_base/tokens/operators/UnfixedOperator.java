package compiler_base.tokens.operators;

import compiler_base.tokens.ProgramToken;
import tools.ConvertResult;
import tools.Pattern;

import java.util.List;
import java.util.Optional;

public enum UnfixedOperator implements ProgramToken {
    ASSIGNMENT_OPERATOR,
    OPENING_BRACE,
    CLOSING_BRACE,
    OPENING_BRACKET,
    CLOSING_BRACKET,
    OPENING_PARENTHESES,
    CLOSING_PARENTHESES;

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
            if (input.get(i) == '(')
                return Optional.of(ConvertResult.of(OPENING_PARENTHESES));
            if (input.get(i) == ')')
                return Optional.of(ConvertResult.of(CLOSING_PARENTHESES));
            return Optional.empty();
        };
    }

    boolean isMatch(UnfixedOperator comparedOperator) {
        return this == OPENING_BRACE && comparedOperator == CLOSING_BRACE ||
                this == CLOSING_BRACE && comparedOperator == OPENING_BRACE ||

                this == OPENING_BRACKET && comparedOperator == CLOSING_BRACKET ||
                this == CLOSING_BRACKET && comparedOperator == OPENING_BRACKET ||

                this == OPENING_PARENTHESES && comparedOperator == CLOSING_PARENTHESES ||
                this == CLOSING_PARENTHESES && comparedOperator == OPENING_PARENTHESES;
    }

    public int findMatchingToken(List<ProgramToken> input, int i) {
        if(this == ASSIGNMENT_OPERATOR) throw new RuntimeException("Unable to match to " + ASSIGNMENT_OPERATOR);

        int nestDepth = 0;
        int pointerDirection = -1;

        if(this == OPENING_BRACE || this == OPENING_BRACKET || this == OPENING_PARENTHESES)
            pointerDirection = 1;

        i += pointerDirection;

        while (!((input.get(i) instanceof UnfixedOperator comparedOperator) &&
                this.isMatch(comparedOperator) &&
                nestDepth == 0))
        {
            if(input.get(i) == this) {
                nestDepth++;
            } else if(input.get(i) instanceof UnfixedOperator comparedOperator) {
                if(this.isMatch(comparedOperator)) nestDepth--;
            }

            i += pointerDirection;
        }

        return i;
    }
}
