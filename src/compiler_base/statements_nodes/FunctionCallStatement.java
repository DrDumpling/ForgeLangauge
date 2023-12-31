package compiler_base.statements_nodes;

import compiler_base.statements_nodes.evaluated.EvaluatedNode;
import compiler_base.statements_nodes.evaluated.FunctionCallNode;
import compiler_base.tokens.ProgramToken;
import compiler_base.tokens.non_specific.StatementEndToken;
import runtime.Environment;
import tools.ConvertResult;
import tools.Pattern;

import java.util.List;
import java.util.Optional;

public class FunctionCallStatement implements ProgramNodeStatement {
    FunctionCallNode calledFunction;

    FunctionCallStatement(FunctionCallNode calledFunction) {
        this.calledFunction = calledFunction;
    }

    public static Pattern<ProgramToken, ProgramNodeStatement<Void>> getPattern() {
        return (input, i) -> {
            System.out.println(input);
            int statementEndIndex = 0;
            for(; statementEndIndex < input.size(); statementEndIndex++) {
                if(input.get(statementEndIndex) instanceof StatementEndToken) break;
            }

            if(!(input.get(statementEndIndex) instanceof StatementEndToken)) return Optional.empty();


            List<ProgramToken> evaluatedTokens = input.subList(0, statementEndIndex);
            System.out.println(evaluatedTokens);

            EvaluatedNode evaluatedNode = StatementConverter.evaluateTokens(evaluatedTokens);

            if (evaluatedNode instanceof FunctionCallNode functionCallNode) {
                return Optional.of(ConvertResult.of(new FunctionCallStatement(functionCallNode), input.size()));
            }

            return Optional.empty();
        };
    }

    @Override
    public Void runStatement(Environment environment) {
        calledFunction.runStatement(environment);

        return null;
    }

    @Override
    public String toString() {
        return calledFunction.toString();
    }
}
