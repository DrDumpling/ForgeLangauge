package compiler_base.statements;

import compiler_base.tokens.ProgramToken;
import compiler_base.tokens.non_specific.KeywordToken;
import compiler_base.tokens.non_specific.StatementEndToken;
import runtime.Environment;
import tools.ConvertResult;
import tools.Pattern;

import java.util.Optional;

public class ReturnStatement implements ProgramStatement {
    ProgramToken returnedValue;

    ReturnStatement(ProgramToken returnedValue) {
        this.returnedValue = returnedValue;
    }

    public static Pattern<ProgramToken, ProgramStatement> getPattern() {
        return (input, i) -> {
            if(!(input.get(i) instanceof KeywordToken keywordToken && keywordToken.heldKeyword == KeywordToken.Keyword.RETURN))
                return Optional.empty();

            ProgramToken returnedValue = input.get(i + 1);
            if(!(input.get(i + 2) instanceof StatementEndToken)) return Optional.empty();

            return Optional.of(ConvertResult.of(new ReturnStatement(returnedValue), 3));
        };
    }

    @Override
    public void runStatement(Environment environment) {

    }
}
