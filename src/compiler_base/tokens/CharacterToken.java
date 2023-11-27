package compiler_base.tokens;

import compiler_base.errors.character_errors.EmptyCharacterLiteral;
import compiler_base.errors.character_errors.ImproperSingleQuote;
import compiler_base.errors.character_errors.UnclosedCharacterLiteral;
import tools.ConvertResult;
import tools.Pattern;

import java.util.List;
import java.util.Optional;

public final class CharacterToken implements ProgramToken {
    final char heldChar;

    public CharacterToken(char input) {
        heldChar = input;
    }

    @Override
    public String toString() {
        return "CharacterToken[" + heldChar + "]";
    }

    static private class CharacterMapping {
        char escapeCode;
        char codeValue;

        CharacterMapping(char escapeCode, char codeValue) {
            this.escapeCode = escapeCode;
            this.codeValue = codeValue;
        }
    }

    public static Pattern<Character, ProgramToken> getPattern(String program) {
        return (input, i) -> {
            if (input.get(i) != '\'') return Optional.empty();

            if(input.get(i + 1) == '\\') {
                CharacterMapping mapping = manageEscapeCodes(program, input, i);
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

    private static CharacterMapping manageEscapeCodes(String program, List<Character> input, int i) {
        if(input.get(i + 3) != '\'') throw new UnclosedCharacterLiteral(program, i + 3);
        for(CharacterMapping mapping: new CharacterMapping[]{
                new CharacterMapping('t', '\t'),
                new CharacterMapping('b', '\b'),
                new CharacterMapping('n', '\n'),
                new CharacterMapping('r', '\r'),
                new CharacterMapping('\'', '\''),
                new CharacterMapping('\"', '\"'),
                new CharacterMapping('\\', '\\'),
        }) {
            if(input.get(i + 2) == mapping.escapeCode)
                return mapping;
        }
        //INVALID ESCAPE CODE
        throw new RuntimeException("TODO");
    }
}