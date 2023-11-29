package compiler_base;

import compiler_base.statements.ProgramStatement;
import compiler_base.statements.StatementConverter;
import compiler_base.tokens.ProgramToken;
import compiler_base.tokens.Tokenizer;

import java.util.List;

public class Compiler {
    public static List<ProgramStatement> compile(String program) {
        Tokenizer tokenizer = new Tokenizer();
        List<ProgramToken> tokens = tokenizer.tokenize(program);
        tokens = tokenizer.cleanTokens(tokens);

        return StatementConverter.convert(tokens);
    }
}
