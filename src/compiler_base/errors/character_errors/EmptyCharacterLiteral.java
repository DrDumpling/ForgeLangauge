package compiler_base.errors.character_errors;

import compiler_base.errors.CompilerErrorHelper;
import compiler_base.errors.error_attachments.ExpectationAttachment;

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
