package compiler_base.errors.string_errors;

import compiler_base.errors.CompilerErrorHelper;
import compiler_base.errors.error_attachments.HelpAttachment;

import java.util.List;

public class NewlineStringError extends RuntimeException {
    public NewlineStringError(String program, int charLocation) {
        super(CompilerErrorHelper.getExceptionInformation("Illegal line end in string literal",
                program,
                charLocation,
                List.of(
                        new HelpAttachment("use \\n to represent newlines"))
        ));
    }
}
