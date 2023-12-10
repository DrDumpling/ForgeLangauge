package compiler_base.statements_nodes.block_statements;

import compiler_base.statements_nodes.ProgramNodeStatement;
import compiler_base.statements_nodes.StatementConverter;
import compiler_base.statements_nodes.evaluated.EvaluatedNode;
import compiler_base.tokens.ProgramToken;
import compiler_base.tokens.non_specific.KeywordToken;
import compiler_base.tokens.operators.UnfixedOperator;
import runtime.Environment;
import tools.ConvertResult;
import tools.Pattern;

import java.util.List;
import java.util.Optional;

public class IfStatement<T> extends BlockStatement<T> {
    EvaluatedNode<Boolean> evaluatedNode;

    IfStatement(List<ProgramNodeStatement<Void>> programNodeStatements, EvaluatedNode<Boolean> evaluatedNode) {
        this.heldStatements = programNodeStatements;
        this.evaluatedNode = evaluatedNode;
    }

    public static Pattern<ProgramToken, ProgramNodeStatement<Void>> getPattern() {
        return (input, i) -> {
            if(!(input.get(i) instanceof KeywordToken keywordToken &&
                    keywordToken.heldKeyword == KeywordToken.Keyword.IF))
                return Optional.empty();

            if(input.get(i + 1) != UnfixedOperator.OPENING_PARENTHESES)
                return Optional.empty();

            int matchingParenthesesPosition = ((UnfixedOperator) input.get(i + 1)).findMatchingToken(input, i + 1);
            EvaluatedNode<Boolean> evaluatedNode = StatementConverter.evaluateTokens(input.subList(i + 2, matchingParenthesesPosition));

            int startingBracePosition = matchingParenthesesPosition + 1;
            int matchingBracePosition;

            if(input.get(startingBracePosition) == UnfixedOperator.OPENING_BRACE) {
                matchingBracePosition = ((UnfixedOperator) input.get(startingBracePosition))
                        .findMatchingToken(input, startingBracePosition);
            } else {
                return Optional.empty();
            }

            List<ProgramToken> parsedStatements = input.subList(startingBracePosition + 1, matchingBracePosition);
            List<ProgramNodeStatement<Void>> heldStatements = StatementConverter.convert(parsedStatements);

            return Optional.of(ConvertResult.of(new IfStatement(heldStatements, evaluatedNode), matchingBracePosition - i + 1));
        };
    }

    @Override
    public T runStatement(Environment environment) {
        if(evaluatedNode.runStatement(environment)) {
            return this.runBlock(environment);
        }
        return null;
    }

    @Override
    public String toString() {
        return "if(" + this.evaluatedNode + ")" + heldStatements;
    }
}
