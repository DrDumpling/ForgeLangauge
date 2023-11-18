package CompilerBase.Errors.ErrorAttachments;

final public class HelpAttachment implements ErrorAttachment {

    String suggestion;

    public HelpAttachment(String suggestion) {
        this.suggestion = suggestion;
    }

    @Override
    public String getPrefix() {
        return "Help: ";
    }

    @Override
    public String getContents() {
        return suggestion;
    }
}
