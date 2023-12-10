package compiler_base.statements_nodes.evaluated.math;

import compiler_base.statements_nodes.StatementConverter;
import compiler_base.statements_nodes.evaluated.EvaluatedNode;
import compiler_base.tokens.ProgramToken;
import compiler_base.tokens.operators.UnfixedOperator;
import compiler_base.tokens.operators.binary_operators.MathOperatorToken;
import runtime.Environment;

import java.util.List;
import java.util.Optional;

public sealed class MultiplicativeNode implements EvaluatedNode {
    static final class MultiplyNode extends MultiplicativeNode {
        MultiplyNode(EvaluatedNode left, EvaluatedNode right) {
            super(left, right);
        }
    }
    static final class DivideNode extends MultiplicativeNode {
        DivideNode(EvaluatedNode left, EvaluatedNode right) {
            super(left, right);
        }
    }
    static final class ModulusNode extends MultiplicativeNode {
        ModulusNode(EvaluatedNode left, EvaluatedNode right) {
            super(left, right);
        }
    }

    final EvaluatedNode left;
    final EvaluatedNode right;

    private MultiplicativeNode(EvaluatedNode left, EvaluatedNode right) {
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

            if(comparedToken == MathOperatorToken.MULTIPLY) return Optional.of(
                    new MultiplyNode(StatementConverter.evaluateTokens(left),
                                    StatementConverter.evaluateTokens(right))
            );
            if(comparedToken == MathOperatorToken.DIVIDE) return Optional.of(
                    new DivideNode(StatementConverter.evaluateTokens(left),
                            StatementConverter.evaluateTokens(right))
            );
            if(comparedToken == MathOperatorToken.MODULUS) return Optional.of(
                    new ModulusNode(StatementConverter.evaluateTokens(left),
                            StatementConverter.evaluateTokens(right))
            );
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + left + ", " + right;
    }

    @Override
    public void runStatement(Environment environment) {

    }
}
