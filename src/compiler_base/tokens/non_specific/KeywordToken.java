package compiler_base.tokens.non_specific;

import compiler_base.tokens.ProgramToken;

import java.util.HashMap;

public final class KeywordToken implements ProgramToken {

    public final Keyword heldKeyword;

    public enum Keyword {
        INT,
        CHAR,
        STRING,

        FUN,
        RETURN,

        IF,
        ELSE,
        FOR,
        BREAK,

        VAR,
        VAL,

        ;

        public boolean isType(Keyword testedKeyword) {
            return testedKeyword == INT ||
                    testedKeyword == CHAR ||
                    testedKeyword == STRING;
        }
    }

    public static HashMap<String, Keyword> keywordMappings = new HashMap<>(){{
        put("int", Keyword.INT);
        put("char", Keyword.CHAR);
        put("string", Keyword.STRING);

        put("fun", Keyword.FUN);
        put("return", Keyword.RETURN);

        put("if", Keyword.IF);
        put("else", Keyword.ELSE);
        put("for", Keyword.FOR);
        put("break", Keyword.BREAK);

        put("var", Keyword.VAR);
        put("val", Keyword.VAL);
    }};

    KeywordToken(Keyword input) {
        heldKeyword = input;
    }

    @Override
    public String toString() {
        return "KeywordToken[" + heldKeyword.toString() + "]";
    }
}
