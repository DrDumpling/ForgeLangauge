package compiler_base.errors.character_errors;

import compiler_base.errors.CompilerErrorHelper;

public class UnclosedCharacterLiteral extends RuntimeException {
    public UnclosedCharacterLiteral(String program, int charLocation) {
        super(CompilerErrorHelper.getExceptionInformation("UnclosedCharacterLiteral",
                program,
                charLocation
        ));
    }
}
