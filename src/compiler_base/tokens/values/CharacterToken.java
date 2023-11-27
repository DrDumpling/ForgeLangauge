package compiler_base.tokens.values;

import compiler_base.errors.character_errors.EmptyCharacterLiteral;
import compiler_base.errors.character_errors.ImproperSingleQuote;
import compiler_base.errors.character_errors.UnclosedCharacterLiteral;
import compiler_base.tokens.ProgramToken;
import tools.CharacterEscapeCode;
import tools.ConvertResult;
import tools.Pattern;

import java.util.List;
import java.util.Optional;

import static tools.CharacterEscapeCode.manageEscapeCodes;

public final class CharacterToken implements ProgramToken {
    final char heldChar;

    public CharacterToken(char input) {
        heldChar = input;
    }

    @Override
    public String toString() {
        return "CharacterToken[" + heldChar + "]";
    }

    public static Pattern<Character, ProgramToken> getPattern(String program) {
        return (input, i) -> {
            if (input.get(i) != '\'') return Optional.empty();

            if(input.get(i + 1) == '\\') {
                if(input.get(i + 3) != '\'') throw new UnclosedCharacterLiteral(program, i + 3);
                CharacterEscapeCode.CharacterMapping mapping = manageEscapeCodes(input, i + 2);
                return Optional.of(ConvertResult.of(new CharacterToken(mapping.codeValue), 4));
            }

            if(input.get(i + 1) == '\'') closedQuoteError(program, input, i);

            if(input.get(i + 2) != '\'') throw new UnclosedCharacterLiteral(program, i + 2);
            return Optional.of(
                    ConvertResult.of(new CharacterToken(input.get(i + 1)), 4)
            );
        };
    }

    private static void closedQuoteError(String program, List<Character> input, int i) throws RuntimeException {
        if(input.get(i + 2) == '\'') {
            throw new ImproperSingleQuote(program, i);
        } else {
            throw new EmptyCharacterLiteral(program, i);
        }
    }
}