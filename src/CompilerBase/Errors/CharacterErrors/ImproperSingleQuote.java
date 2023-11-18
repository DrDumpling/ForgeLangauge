package CompilerBase.Errors.CharacterErrors;

import CompilerBase.Errors.CompilerErrorHelper;
import CompilerBase.Errors.ErrorAttachments.HelpAttachment;

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
