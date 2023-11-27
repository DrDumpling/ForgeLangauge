package compiler_base.tokens.non_specific;

import compiler_base.tokens.ProgramToken;
import tools.ConvertResult;
import tools.Pattern;

import java.util.Optional;

public class StatementEndToken implements ProgramToken {

    @Override
    public String toString() {
        return "StatementEndToken";
    }
    public static Pattern<Character, ProgramToken> getPattern() {
        return (input, i) -> {
            if(input.get(i) == ';')
                return Optional.of(ConvertResult.of(new StatementEndToken()));
            return Optional.empty();
        };
    }
}
