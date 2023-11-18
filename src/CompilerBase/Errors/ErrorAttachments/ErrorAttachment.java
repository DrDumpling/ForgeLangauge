package CompilerBase.Errors.ErrorAttachments;

public sealed interface ErrorAttachment permits ExpectationAttachment, HelpAttachment {
    String getPrefix();
    String getContents();
}
