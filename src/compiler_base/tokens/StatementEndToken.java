package compiler_base.tokens;

import tools.ConvertResult;
import tools.Pattern;

import java.util.Optional;

public class StatementEndToken implements ProgramToken {
    public static Pattern<Character, ProgramToken> getPattern() {
        return (input, i) -> {
            if(input.get(i) == ';')
                return Optional.of(ConvertResult.of(new StatementEndToken()));
            return Optional.empty();
        };
    }
}
