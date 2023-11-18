package CompilerBase.Errors.ErrorAttachments;

final public class ExpectationAttachment implements ErrorAttachment {

    String expectation;

    public ExpectationAttachment(String expectation) {
        this.expectation = expectation;
    }

    @Override
    public String getPrefix() {
        return "Expectation: ";
    }

    @Override
    public String getContents() {
        return expectation;
    }
}
