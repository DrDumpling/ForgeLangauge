package compiler_base.errors.character_errors;

import compiler_base.errors.CompilerErrorHelper;
import compiler_base.errors.error_attachments.HelpAttachment;

import java.util.List;

public class ImproperSingleQuote extends RuntimeException {
    public ImproperSingleQuote(String program, int charLocation) {
        super(CompilerErrorHelper.getExceptionInformation("ImproperSingleQuote",
                program,
                charLocation,
                List.of(new HelpAttachment("to hold a single quote value, use '\\'' instead"))
        ));
    }
}
