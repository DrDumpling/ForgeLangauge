package compiler_base.statements_nodes;

import compiler_base.statements_nodes.evaluated.EvaluatedNode;
import compiler_base.tokens.ProgramToken;
import compiler_base.tokens.non_specific.KeywordToken;
import compiler_base.tokens.non_specific.StatementEndToken;
import runtime.Environment;
import tools.ConvertResult;
import tools.Pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

public class ReturnStatement<T> implements ProgramNodeStatement<T> {
    EvaluatedNode<T> returnedValue;

    ReturnStatement(EvaluatedNode<T> returnedValue) {
        this.returnedValue = returnedValue;
    }

    public static Pattern<ProgramToken, ProgramNodeStatement<Void>> getPattern() {
        return (input, i) -> {
            if(!(input.get(i) instanceof KeywordToken keywordToken && keywordToken.heldKeyword == KeywordToken.Keyword.RETURN))
                return Optional.empty();

            List<ProgramToken> evaluatedTokens = new ArrayList<>();
            ListIterator<ProgramToken> tokenIter = input.listIterator(i + 1);

            while(true) {
                ProgramToken addedValue = tokenIter.next();
                if(addedValue instanceof StatementEndToken) {
                    break;
                }
                evaluatedTokens.add(addedValue);
            }

            EvaluatedNode returnedValue = StatementConverter.evaluateTokens(evaluatedTokens);

            return Optional.of(ConvertResult.of(new ReturnStatement(returnedValue), evaluatedTokens.size() + 2));
        };
    }

    @Override
    public T runStatement(Environment environment) {
        return returnedValue.runStatement(environment);
    }

    @Override
    public String toString() {
        return "return: " + this.returnedValue;
    }
}
