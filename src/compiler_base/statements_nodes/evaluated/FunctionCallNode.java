package compiler_base.statements_nodes.evaluated;

import compiler_base.statements_nodes.StatementConverter;
import compiler_base.statements_nodes.block_statements.FunctionStatement;
import compiler_base.tokens.ProgramToken;
import compiler_base.tokens.non_specific.CommaToken;
import compiler_base.tokens.non_specific.NameToken;
import compiler_base.tokens.operators.UnfixedOperator;
import runtime.Environment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static compiler_base.Compiler.functionMap;

public class FunctionCallNode<T> implements EvaluatedNode<T> {
    FunctionStatement<T> functionStatement;
    String functionName;
    List<EvaluatedNode> params;

    FunctionCallNode(String functionName, List<EvaluatedNode> params) {
        this.functionName = functionName;
        this.params = params;
    }

    public static Optional<EvaluatedNode> matches(List<ProgramToken> input) {
        if(input.size() < 3) return Optional.empty();

        String name;
        List<EvaluatedNode> params = new ArrayList<>();

        for(int i = 0; i < input.size(); i++) {
            if(!(input.get(i) instanceof NameToken nameToken)) continue;
            if(input.get(i + 1) != UnfixedOperator.OPENING_PARENTHESES) continue;

            name = nameToken.heldName;

            int paramStart = i + 2;

            int pairingClosing = ((UnfixedOperator)input.get(i + 1)).findMatchingToken(input,i+1);
            List<ProgramToken> currentTokens = new ArrayList<>();

            // TODO: ADD NESTING SUPPORT
            for(int paramIndex = paramStart; paramIndex < pairingClosing; paramIndex++) {
                if(input.get(paramIndex) instanceof CommaToken) {
                    params.add(StatementConverter.evaluateTokens(currentTokens));
                    currentTokens = new ArrayList<>();
                } else {
                    currentTokens.add(input.get(paramIndex));
                }
            }

            params.add(StatementConverter.evaluateTokens(currentTokens));

            return Optional.of(new FunctionCallNode(name, params));
        }
        return Optional.empty();
    }

    @Override
    public T runStatement(Environment environment) {
        // TODO: NEED TO ADD A MORE PERMANENT HOME FOR LIBRARY STATEMENTS
        if(functionName.equals("print")) {
            System.out.println(params.get(0).runStatement(environment));
            return null;
        }
        if(Objects.isNull(functionStatement)) {
            functionStatement = functionMap.get(this.functionName);
        }

        Environment newEnvironment = new Environment();

        for(int i = 0; i < params.size(); i++) {
            EvaluatedNode addedParam = params.get(i);
            String paramName = functionStatement.takenVariables.get(i);
            newEnvironment.addVariable(paramName, addedParam.runStatement(environment));
        }

        return functionStatement.runStatement(newEnvironment);
    }

    @Override
    public String toString() {
        return functionName + params;
    }
}
