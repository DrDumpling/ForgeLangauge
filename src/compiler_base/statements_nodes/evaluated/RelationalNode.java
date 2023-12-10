package compiler_base.statements_nodes.evaluated;

import compiler_base.statements_nodes.StatementConverter;
import compiler_base.tokens.ProgramToken;
import compiler_base.tokens.operators.binary_operators.ComparisonOperatorToken;
import runtime.Environment;

import java.util.List;
import java.util.Optional;

public sealed class RelationalNode implements EvaluatedNode<Boolean> {
    static final class EqualsNode extends RelationalNode {
        EqualsNode(EvaluatedNode left, EvaluatedNode right) {
            super(left, right);
        }
    }
    static final class LessThanNode extends RelationalNode {
        LessThanNode(EvaluatedNode left, EvaluatedNode right) {
            super(left, right);
        }
    }
    static final class GreaterThanNode extends RelationalNode {
        GreaterThanNode(EvaluatedNode left, EvaluatedNode right) {
            super(left, right);
        }
    }
    static final class LessThanOrEqualNode extends RelationalNode {
        LessThanOrEqualNode(EvaluatedNode left, EvaluatedNode right) {
            super(left, right);
        }
    }
    static final class GreaterThanOrEqualNode extends RelationalNode {
        GreaterThanOrEqualNode(EvaluatedNode left, EvaluatedNode right) {
            super(left, right);
        }
    }
    static final class NotEqualNode extends RelationalNode {
        NotEqualNode(EvaluatedNode left, EvaluatedNode right) {
            super(left, right);
        }
    }

    final EvaluatedNode left;
    final EvaluatedNode right;

    private RelationalNode(EvaluatedNode left, EvaluatedNode right) {
        this.left = left;
        this.right = right;
    }

    public static Optional<EvaluatedNode> matches(List<ProgramToken> input) {
        for(int i = 0; i < input.size(); i++) {
            ProgramToken comparedToken = input.get(i);

            if(!(comparedToken instanceof ComparisonOperatorToken comparisonOperatorToken)) continue;

            EvaluatedNode left = StatementConverter.evaluateTokens(input.subList(0, i));
            EvaluatedNode right = StatementConverter.evaluateTokens(input.subList(i+1, input.size()));

            return Optional.of(switch (comparisonOperatorToken) {
                case EQUALS -> new EqualsNode(left, right);
                case LESS_THAN -> new LessThanNode(left, right);
                case GREATER_THAN -> new GreaterThanNode(left, right);
                case LESS_THAN_OR_EQUAL -> new LessThanOrEqualNode(left, right);
                case GREATER_THAN_OR_EQUAL -> new GreaterThanOrEqualNode(left, right);
                case NOT_EQUAL -> new NotEqualNode(left, right);
            });
        }

        return Optional.empty();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + left + ", " + right;
    }

    @Override
    public Boolean runStatement(Environment environment) {
        if(this instanceof RelationalNode.EqualsNode) {
            return left.runStatement(environment).equals(right.runStatement(environment));
        }
        if(this instanceof RelationalNode.NotEqualNode) {
            return !left.runStatement(environment).equals(right.runStatement(environment));
        }
        if(this instanceof RelationalNode.LessThanNode) {
            return (Integer)left.runStatement(environment) < (Integer)right.runStatement(environment);
        }
        if(this instanceof RelationalNode.GreaterThanNode) {
            return (Integer)left.runStatement(environment) > (Integer)right.runStatement(environment);
        }
        throw new RuntimeException("TODO!!");
    }
}
