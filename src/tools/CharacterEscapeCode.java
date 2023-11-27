package tools;

import java.util.List;

public class CharacterEscapeCode {
    static public class CharacterMapping {
        public char escapeCode;
        public char codeValue;

        public CharacterMapping(char escapeCode, char codeValue) {
            this.escapeCode = escapeCode;
            this.codeValue = codeValue;
        }
    }

    public static CharacterEscapeCode.CharacterMapping manageEscapeCodes(List<Character> input, int programPosition) throws RuntimeException {
        for(CharacterEscapeCode.CharacterMapping mapping: new CharacterEscapeCode.CharacterMapping[]{
                new CharacterMapping('t', '\t'),
                new CharacterMapping('b', '\b'),
                new CharacterMapping('n', '\n'),
                new CharacterMapping('r', '\r'),
                new CharacterMapping('\'', '\''),
                new CharacterMapping('\"', '\"'),
                new CharacterMapping('\\', '\\'),
        }) {
            if(input.get(programPosition) == mapping.escapeCode)
                return mapping;
        }
        //INVALID ESCAPE CODE
        throw new RuntimeException("Unrecognized escape character: " + input.get(programPosition));
    }


}
