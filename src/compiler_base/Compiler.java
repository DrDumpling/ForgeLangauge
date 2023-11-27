package compiler_base;

import compiler_base.tokens.*;
import compiler_base.tokens.operators.UnaryOperators;
import compiler_base.tokens.operators.UnfixedOperator;
import compiler_base.tokens.operators.binary_operators.BitwiseOperatorToken;
import compiler_base.tokens.operators.binary_operators.ComparisonOperatorToken;
import compiler_base.tokens.operators.binary_operators.LogicalOperatorToken;
import compiler_base.tokens.operators.binary_operators.MathOperatorToken;
import tools.ConvertResult;
import tools.Pattern;
import tools.PatternConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Compiler {
    public static List<ProgramToken> compile(String program) {
        List<Character> chars = program
                .chars()
                .mapToObj(e -> (char) e)
                .collect(Collectors.toList());

        List<Pattern<Character, ProgramToken>> patterns = new ArrayList<>() {{
            add(NumericalToken.getPattern());
            add(CharacterToken.getPattern(program));
            add(NameToken.getPattern());
            add((input, i) -> (Arrays.asList(' ', '\n', '\t', '\r', '\f').contains(input.get(i))) ?
                    Optional.of(ConvertResult.of(new WhitespaceToken())) :
                    Optional.empty());
            add(UnfixedOperator.getPattern());
            add(LogicalOperatorToken.getPattern());
            add(MathOperatorToken.getPattern());
            add(BitwiseOperatorToken.getPattern());
            add(StatementEndToken.getPattern());
            add(ComparisonOperatorToken.getPattern());
            add(UnaryOperators.getPattern());
        }};

        return new PatternConverter<Character, ProgramToken>().convert(
                chars,
                patterns
        );
    }
}
