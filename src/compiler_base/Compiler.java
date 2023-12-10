package compiler_base;

import compiler_base.statements_nodes.ProgramNodeStatement;
import compiler_base.statements_nodes.StatementConverter;
import compiler_base.statements_nodes.block_statements.FunctionStatement;
import compiler_base.tokens.ProgramToken;
import compiler_base.tokens.Tokenizer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Compiler {
    public static final Map<String, FunctionStatement> functionMap = new HashMap<>();

    public static List<ProgramNodeStatement<Void>> compile(String program) {
        Tokenizer tokenizer = new Tokenizer();
        List<ProgramToken> tokens = tokenizer.tokenize(program);
        tokens = tokenizer.cleanTokens(tokens);

        return StatementConverter.convert(tokens);
    }
}
