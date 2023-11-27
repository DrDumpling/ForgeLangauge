package compiler_base.tokens.non_specific;

import compiler_base.tokens.ProgramToken;
import tools.ConvertResult;
import tools.Pattern;

import java.util.Optional;

public class ColonToken implements ProgramToken {
    public static Pattern<Character, ProgramToken> getPattern() {
        return (input, i) -> (input.get(i) == ':') ?
                Optional.of(ConvertResult.of(new ColonToken())):
                Optional.empty();
    }

    @Override
    public String toString() {
        return "ColonToken";
    }
}
