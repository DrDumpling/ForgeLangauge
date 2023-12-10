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
            if(!(input.get(input.size() - 1) instanceof StatementEndToken)) {
                return Optional.empty();
            }

            List<ProgramToken> evaluatedTokens = input.subList(0, input.size());
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
}
