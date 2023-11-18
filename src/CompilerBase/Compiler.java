package CompilerBase;

import CompilerBase.tokens.*;
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
                .mapToObj(e -> (char)e)
                .collect(Collectors.toList());

        List<Pattern<Character, ProgramToken>> patterns = new ArrayList<>(){{
            add(NumericalToken.getPattern());
            add(CharacterToken.getPattern(program));
            add(KeywordToken.getPattern());
            add((input, i) -> (Arrays.asList(' ', '\n', '\t', '\r', '\f').contains(input.get(i))) ?
                    Optional.of(ConvertResult.of(new WhitespaceToken())) :
                    Optional.empty());
            add((input, i) -> Optional.of(ConvertResult.of(new GenericToken(input.get(i)))));
        }};

        return new PatternConverter<Character, ProgramToken>().convert(
                chars,
                patterns
        );
    }
}
