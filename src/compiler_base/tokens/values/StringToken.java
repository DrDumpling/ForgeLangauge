package compiler_base.tokens.values;

import compiler_base.errors.string_errors.NewlineStringError;
import compiler_base.tokens.ProgramToken;
import tools.CharacterEscapeCode;
import tools.ConvertResult;
import tools.Pattern;

import java.util.Optional;

import static tools.CharacterEscapeCode.manageEscapeCodes;

public class StringToken implements ProgramToken {
    String heldString;

    public StringToken(String heldString) {
        this.heldString = heldString;
    }

    @Override
    public String toString() {
        return "StringToken[" + heldString + "]";
    }

    public static Pattern<Character, ProgramToken> getPattern(String program) {
        return (input, i) -> {
            if (input.get(i) != '"') return Optional.empty();

            int startingOffset = i;
            i++;

            StringBuilder builtString = new StringBuilder();

            while(input.get(i) != '"') {
                if (input.get(i) == '\n' || input.get(i) == '\r') throw new NewlineStringError(program, i);
                if (input.get(i) == '\\') {
                    CharacterEscapeCode.CharacterMapping mapping = manageEscapeCodes(input, i + 1);
                    builtString.append(mapping.codeValue);
                    i += 2;
                } else {
                    builtString.append(input.get(i));
                    i += 1;
                }
            }
            //not entirely sure why +3 is required instead of +2, should look into it at a later date
            return Optional.of(ConvertResult.of(
                    new StringToken(builtString.toString()),
                    i - startingOffset + 1)
            );
        };
    }
}
