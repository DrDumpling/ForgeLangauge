package compiler_base.tokens;

import java.util.HashMap;

public final class KeywordToken implements ProgramToken {

    Keyword heldKeyword;

    enum Keyword {
        INT,
        CHAR,
    }

    public static HashMap<String, Keyword> keywordMappings = new HashMap<>(){{
        put("int", Keyword.INT);
        put("char", Keyword.CHAR);
    }};

    KeywordToken(Keyword input) {
        heldKeyword = input;
    }

    @Override
    public String toString() {
        return "KeywordToken[" + heldKeyword.toString() + "]";
    }
}
