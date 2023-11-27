package compiler_base.tokens.non_specific;

import compiler_base.tokens.ProgramToken;
import tools.ConvertResult;
import tools.Pattern;

import java.util.Arrays;
import java.util.Optional;

public final class WhitespaceToken implements ProgramToken {
    @Override
    public String toString() {
        return "WhitespaceToken";
    }

    public static Pattern<Character, ProgramToken> getPattern() {
        return (input, i) -> (Arrays.asList(' ', '\n', '\t', '\r', '\f').contains(input.get(i))) ?
                Optional.of(ConvertResult.of(new WhitespaceToken())) :
                Optional.empty();
    }
}
