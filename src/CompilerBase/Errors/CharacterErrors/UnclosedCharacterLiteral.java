package CompilerBase.Errors.CharacterErrors;

import CompilerBase.Errors.CompilerErrorHelper;

public class UnclosedCharacterLiteral extends RuntimeException {
    public UnclosedCharacterLiteral(String program, int charLocation) {
        super(CompilerErrorHelper.getExceptionInformation("UnclosedCharacterLiteral",
                program,
                charLocation
        ));
    }
}
