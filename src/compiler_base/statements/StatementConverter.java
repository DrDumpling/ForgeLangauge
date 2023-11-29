package compiler_base.statements;

import compiler_base.statements.block_statements.FunctionStatement;
import compiler_base.statements.block_statements.IfStatement;
import compiler_base.tokens.ProgramToken;
import tools.Pattern;
import tools.PatternConverter;

import java.util.ArrayList;
import java.util.List;

public class StatementConverter {
    static List<Pattern<ProgramToken, ProgramStatement>> patterns =  new ArrayList<>() {{
        add(FunctionStatement.getPattern());
        add(IfStatement.getPattern());
        add(ReturnStatement.getPattern());
    }};

    public static List<ProgramStatement> convert(List<ProgramToken> tokens) {
        return new PatternConverter<ProgramToken, ProgramStatement>().convert(
                tokens,
                patterns
        );
    }
}
