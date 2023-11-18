package CompilerBase.Errors.CharacterErrors;

import CompilerBase.Errors.CompilerErrorHelper;
import CompilerBase.Errors.ErrorAttachments.ExpectationAttachment;

import java.util.List;

public class EmptyCharacterLiteral extends RuntimeException {
    public EmptyCharacterLiteral(String program, int charLocation) {
        super(CompilerErrorHelper.getExceptionInformation("EmptyCharacterLiteral",
                program,
                charLocation,
                List.of(new ExpectationAttachment("expected character to be present, instead found character end"))
            ));
    }
}
