package compiler_base.statements_nodes.evaluated.math;

import compiler_base.statements_nodes.StatementConverter;
import compiler_base.statements_nodes.evaluated.EvaluatedNode;
import compiler_base.tokens.ProgramToken;
import compiler_base.tokens.operators.UnfixedOperator;
import compiler_base.tokens.operators.binary_operators.MathOperatorToken;
import runtime.Environment;

import java.util.List;
import java.util.Optional;

public abstract sealed class AdditiveNode implements EvaluatedNode<Integer> {
    static final class AdditionNode extends AdditiveNode {
        AdditionNode(EvaluatedNode<Integer> left, EvaluatedNode<Integer> right) {
            super(left, right);
        }

        @Override
        public Integer runStatement(Environment environment) {
            return left.runStatement(environment) + right.runStatement(environment);
        }
    }
    static final class MinusNode extends AdditiveNode {
        MinusNode(EvaluatedNode<Integer> left, EvaluatedNode<Integer> right) {
            super(left, right);
        }

        @Override
        public Integer runStatement(Environment environment) {
            return left.runStatement(environment) - right.runStatement(environment);
        }
    }

    final EvaluatedNode<Integer> left;
    final EvaluatedNode<Integer> right;

    private AdditiveNode(EvaluatedNode<Integer> left, EvaluatedNode<Integer> right) {
        this.left = left;
        this.right = right;
    }

    public static Optional<EvaluatedNode> matches(List<ProgramToken> input) {
        for(int i = 0; i < input.size(); i++) {
            ProgramToken comparedToken = input.get(i);
            if(comparedToken == UnfixedOperator.OPENING_PARENTHESES) {
                i = ((UnfixedOperator)input.get(i)).findMatchingToken(input,i);
                continue;
            }

            //may be faster if you don't initialize left and right until needed
            List<ProgramToken> left = input.subList(0, i);
            List<ProgramToken> right = input.subList(i+1, input.size());

            if(comparedToken == MathOperatorToken.PLUS) return Optional.of(
                    new AdditionNode(StatementConverter.evaluateTokens(left),
                            StatementConverter.evaluateTokens(right))
            );
            if(comparedToken == MathOperatorToken.MINUS) return Optional.of(
                    new MinusNode(StatementConverter.evaluateTokens(left),
                            StatementConverter.evaluateTokens(right))
            );
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + left + ", " + right;
    }
}
