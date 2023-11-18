package CompilerBase.tokens;

import tools.ConvertResult;
import tools.Pattern;

import java.util.Optional;

public final class KeywordToken implements ProgramToken {
    enum Keyword {
        VAR
    }

    Keyword heldKeyword;

    public KeywordToken(Keyword input) {
        heldKeyword = input;
    }

    @Override
    public String toString() {
        return "KeywordToken[" + heldKeyword.name() + "]";
    }

    static class KeywordMapping {
        String keywordString;
        Keyword keyword;

        KeywordMapping(String keywordString, Keyword keyword) {
            this.keywordString = keywordString;
            this.keyword = keyword;
        }
    }

    // IMPORTANT: ORDER BY LENGTH (SHOULD IMPLEMENT UNIT TESTS LATER TO ENSURE THIS IS FOLLOWED)
    public static KeywordMapping[] keywordMappings = new KeywordMapping[]{new KeywordToken.KeywordMapping("var", Keyword.VAR)};

    public static Pattern<Character, ProgramToken> getPattern() {
        return (input, i) -> {
            String testedWord = "";

            for(KeywordMapping keywordMapping: keywordMappings) {
                while(testedWord.length() < keywordMapping.keywordString.length()) {
                    char addedChar =  input.get(i + testedWord.length());
                    if(!Character.isAlphabetic(addedChar)) return Optional.empty();
                    testedWord += addedChar;
                }

                if(testedWord.equals(keywordMapping.keywordString)) {
                    return Optional.of(
                            ConvertResult.of(new KeywordToken(keywordMapping.keyword),
                            keywordMapping.keywordString.length())
                    );
                }
            }

            return Optional.empty();
        };
    }
}
