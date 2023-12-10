package compiler_base.statements_nodes.evaluated;

import compiler_base.tokens.ProgramToken;
import compiler_base.tokens.non_specific.NameToken;
import compiler_base.tokens.values.CharacterToken;
import compiler_base.tokens.values.NumericalToken;
import compiler_base.tokens.values.StringToken;
import runtime.Environment;

import java.util.List;
import java.util.Optional;

sealed public abstract class BasicValueNode<T> implements EvaluatedNode<T> {
    static final class VariableValue<T> extends BasicValueNode<T> {
        final String variableName;
        VariableValue(String variableName) {
            this.variableName = variableName;
        }

        @Override
        public String toString() {
            return "VariableValue: " + variableName;
        }

        @Override
        public T runStatement(Environment environment) {
            return environment.getVariable(variableName);
        }
    }
    static final class IntValue extends BasicValueNode<Integer> {
        final int heldValue;
        IntValue(int heldValue) {
            this.heldValue = heldValue;
        }

        @Override
        public String toString() {
            return heldValue + "";
        }

        @Override
        public Integer runStatement(Environment environment) {
            return heldValue;
        }
    }
    static final class BoolValue extends BasicValueNode<Boolean> {
        final boolean heldValue;
        BoolValue(boolean heldValue) {
            this.heldValue = heldValue;
        }

        @Override
        public String toString() {
            return heldValue + "";
        }

        @Override
        public Boolean runStatement(Environment environment) {
            return heldValue;
        }
    }
    static final class CharValue extends BasicValueNode<Character> {
        final char heldValue;
        CharValue(char heldValue) {
            this.heldValue = heldValue;
        }

        @Override
        public String toString() {
            return heldValue + "";
        }

        @Override
        public Character runStatement(Environment environment) {
            return heldValue;
        }
    }
    static final class StringValue extends BasicValueNode<String> {
        final String heldValue;
        StringValue(String heldValue) {
            this.heldValue = heldValue;
        }

        @Override
        public String toString() {
            return heldValue;
        }

        @Override
        public String runStatement(Environment environment) {
            return heldValue;
        }
    }
    static final class DoubleValue extends BasicValueNode<Double> {
        final double heldValue;
        DoubleValue(double heldValue) {
            this.heldValue = heldValue;
        }

        @Override
        public String toString() {
            return heldValue + "";
        }

        @Override
        public Double runStatement(Environment environment) {
            return heldValue;
        }
    }

    public static Optional<EvaluatedNode> matches(List<ProgramToken> input) {
        if(input.size() != 1) return Optional.empty();

        if(input.get(0) instanceof NameToken nameToken) return Optional.of(new VariableValue(nameToken.heldName));
        if(input.get(0) instanceof NumericalToken numericalToken) return Optional.of(new IntValue(numericalToken.heldValue));
//        if(input.get(0) instanceof BoolValue boolValue) return Optional.of(new BoolValue());
        if(input.get(0) instanceof CharacterToken charToken) return Optional.of(new CharValue(charToken.heldChar));
        if(input.get(0) instanceof StringToken stringToken) return Optional.of(new StringValue(stringToken.heldString));
//        if(input.get(0) instanceof DoubleToken doubleToken) return Optional.of(new DoubleValue(doubleToken.heldDouble));

        return Optional.empty();
    }
}
