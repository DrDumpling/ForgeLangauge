package compiler_base.tokens;

import tools.ConvertResult;
import tools.Pattern;

import java.util.Optional;

import static compiler_base.tokens.KeywordToken.keywordMappings;

public final class NameToken implements ProgramToken {

    String heldName;

    public NameToken(String input) {
        heldName = input;
    }

    @Override
    public String toString() {
        return "KeywordToken[" + heldName + "]";
    }

    private static boolean isValidNameStart(char c) {
        return Character.isAlphabetic(c) || c == '_' || c == '$';
    }

    private static boolean isValidNameCharacter(char c) {
        return isValidNameStart(c) || Character.isDigit(c);
    }

    public static Pattern<Character, ProgramToken> getPattern() {
        return (input, i) -> {
            if(!isValidNameStart(input.get(i))) {
                return Optional.empty();
            }

            StringBuilder builtWord = new StringBuilder();

            for(int charOffset = i; isValidNameCharacter(input.get(charOffset)); charOffset++) {
                builtWord.append(input.get(charOffset));
            }

            String testedWord = builtWord.toString();

            if (keywordMappings.containsKey(testedWord)) {
                return Optional.of(
                    ConvertResult.of(
                        new KeywordToken(keywordMappings.get(testedWord)),
                        testedWord.length()
                    ));
            }

            return Optional.of(
                        ConvertResult.of(new NameToken(testedWord),
                        testedWord.length()
                    ));
        };
    }
}
