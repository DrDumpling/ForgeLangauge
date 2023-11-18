package CompilerBase.Errors;

import CompilerBase.Errors.ErrorAttachments.ErrorAttachment;

import java.util.List;

public class CompilerErrorHelper {

    private static class ErrorLocation {
        final int lineNumber;
        final int horizontalOffset;

        ErrorLocation(int lineNumber, int horizontalOffset) {
            this.lineNumber = lineNumber;
            this.horizontalOffset = horizontalOffset;
        }
    }

    public static String getExceptionInformation(String errorName, String program, int charLocation) {
        ErrorLocation errorLocation = getErrorLocation(program, charLocation);
        int lineNumber = errorLocation.lineNumber;
        int horizontalOffset = errorLocation.horizontalOffset;

        String line = program.split("\r\n|\n")[lineNumber - 1];

        return '\n' + errorName +
                " on line: " + lineNumber + '\n' +
                line + '\n' +
                " ".repeat(horizontalOffset) + "^";
    }

    public static String getExceptionInformation(String errorName, String program, int charLocation, List<ErrorAttachment> attachments) {
        StringBuilder result = new StringBuilder(getExceptionInformation(errorName, program, charLocation));

        for (ErrorAttachment attachment: attachments) {
            result.append('\n').append(attachment.getPrefix()).append(attachment.getContents());
        }

        return result.toString();
    }

    private static ErrorLocation getErrorLocation(String program, int charLocation) {
        int lineNumber = 1;
        int horizontalOffset = 0;

        char[] charArray = program.toCharArray();
        for(int i = 0; i < charArray.length && i < charLocation; i++) {
            if(charArray[i] == '\n') {
                lineNumber++;
                horizontalOffset = 0;
            } else {
                horizontalOffset += 1;
            }
        }

        return new ErrorLocation(lineNumber, horizontalOffset);
    }
}
