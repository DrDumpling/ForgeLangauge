package compiler_base.statements.block_statements;

import compiler_base.statements.ProgramStatement;
import compiler_base.statements.StatementConverter;
import compiler_base.tokens.ProgramToken;
import compiler_base.tokens.non_specific.KeywordToken;
import compiler_base.tokens.operators.UnfixedOperator;
import runtime.Environment;
import tools.ConvertResult;
import tools.Pattern;

import java.util.List;
import java.util.Optional;

public class IfStatement extends BlockStatement {
    IfStatement(List<ProgramStatement> programStatements) {
        this.heldStatements = programStatements;
    }

    public static Pattern<ProgramToken, ProgramStatement> getPattern() {
        return (input, i) -> {
            if(!(input.get(i) instanceof KeywordToken keywordToken &&
                    keywordToken.heldKeyword == KeywordToken.Keyword.IF))
                return Optional.empty();

            if(input.get(i + 1) != UnfixedOperator.OPENING_PARENTHESES)
                return Optional.empty();

            int matchingParenthesesPosition = ((UnfixedOperator) input.get(i + 1)).findMatchingToken(input, i + 1);
            int startingBracePosition = matchingParenthesesPosition + 1;
            int matchingBracePosition;
            if(input.get(startingBracePosition) == UnfixedOperator.OPENING_BRACE) {
                matchingBracePosition = ((UnfixedOperator) input.get(startingBracePosition))
                        .findMatchingToken(input, startingBracePosition);
            } else {
                return Optional.empty();
            }

            List<ProgramToken> parsedStatements = input.subList(startingBracePosition + 1, matchingBracePosition);
            List<ProgramStatement> heldStatements = StatementConverter.convert(parsedStatements);

            // TODO: implement evaluated node for matching parentheses
            return Optional.of(ConvertResult.of(new IfStatement(heldStatements), matchingBracePosition - i + 1));
        };
    }

    @Override
    public void runStatement(Environment environment) {

    }
}
