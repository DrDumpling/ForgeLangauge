package CompilerBase.tokens;

public final class GenericToken implements ProgramToken {

    final Character heldValue;

    public GenericToken(Character input) {
        heldValue = input;
    }
}
