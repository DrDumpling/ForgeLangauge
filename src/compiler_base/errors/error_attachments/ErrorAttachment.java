package compiler_base.errors.error_attachments;

public sealed interface ErrorAttachment permits ExpectationAttachment, HelpAttachment {
    String getPrefix();
    String getContents();
}
