package compiler_base.tokens;

import compiler_base.tokens.non_specific.*;
import compiler_base.tokens.operators.UnaryOperators;
import compiler_base.tokens.operators.UnfixedOperator;
import compiler_base.tokens.operators.binary_operators.BitwiseOperatorToken;
import compiler_base.tokens.operators.binary_operators.ComparisonOperatorToken;
import compiler_base.tokens.operators.binary_operators.LogicalOperatorToken;
import compiler_base.tokens.operators.binary_operators.MathOperatorToken;
import compiler_base.tokens.values.CharacterToken;
import compiler_base.tokens.values.NumericalToken;
import compiler_base.tokens.values.StringToken;
import tools.Pattern;
import tools.PatternConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Tokenizer {
    public List<ProgramToken> tokenize(String program) {
        List<Character> chars = program
                .chars()
                .mapToObj(e -> (char) e)
                .collect(Collectors.toList());

        List<Pattern<Character, ProgramToken>> patterns = new ArrayList<>() {{
            add(StringToken.getPattern(program));
            add(NumericalToken.getPattern());
            add(CharacterToken.getPattern(program));
            add(NameToken.getPattern());
            add(WhitespaceToken.getPattern());
            add(LogicalOperatorToken.getPattern());
            add(MathOperatorToken.getPattern());
            add(BitwiseOperatorToken.getPattern());
            add(StatementEndToken.getPattern());
            add(ComparisonOperatorToken.getPattern());
            add(UnfixedOperator.getPattern());
            add(UnaryOperators.getPattern());
            add(ColonToken.getPattern());
            add(CommaToken.getPattern());
        }};

        return new PatternConverter<Character, ProgramToken>().convert(
                chars,
                patterns
        );
    }

    public List<ProgramToken> cleanTokens(List<ProgramToken> tokens) {
        return tokens.stream()
                .filter(it -> !(it instanceof WhitespaceToken))
                .toList();
    }
}
