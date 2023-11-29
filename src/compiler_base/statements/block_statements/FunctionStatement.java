package compiler_base.statements.block_statements;

import compiler_base.statements.ProgramStatement;
import compiler_base.statements.StatementConverter;
import compiler_base.tokens.ProgramToken;
import compiler_base.tokens.non_specific.ColonToken;
import compiler_base.tokens.non_specific.CommaToken;
import compiler_base.tokens.non_specific.KeywordToken;
import compiler_base.tokens.non_specific.NameToken;
import compiler_base.tokens.operators.UnfixedOperator;
import runtime.Environment;
import tools.ConvertResult;
import tools.Pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

public class FunctionStatement extends BlockStatement {
    String functionName;
    List<NameToken> takenVariables;

    FunctionStatement(String functionName, List<NameToken> takenVariables, List<ProgramStatement> programStatements) {
        this.functionName = functionName;
        this.takenVariables = takenVariables;
        this.heldStatements = programStatements;
    }

    //starts with fun, valid name, (
    private static boolean verifyStart(List<ProgramToken> input, int i) {
        //first token is fun keyword
        if(!(input.get(i) instanceof KeywordToken keyword &&
                keyword.heldKeyword == KeywordToken.Keyword.FUN))
            return false;

        //second token is valid name
        if (!(input.get(i + 1) instanceof NameToken)) return false;

        //third token is (
        return input.get(i + 2) == UnfixedOperator.OPENING_PARENTHESES;
    }

    public static Pattern<ProgramToken, ProgramStatement> getPattern() {
        return (input, i) -> {
            System.out.println(input);
            List<NameToken> takenVariables = new ArrayList<>();
            String functionName;

            if(verifyStart(input, i)) {
                functionName = ((NameToken)input.get(i + 1)).heldName;
            } else {
                return Optional.empty();
            }

            // repeating valid type, valid name, comma
            int paramCount = 0;
            int paramStart = i + 3;
            ListIterator<ProgramToken> paramTokenIter = input.listIterator(paramStart);

            if(input.get(paramStart) != UnfixedOperator.CLOSING_PARENTHESES) {
                while (true) {
                    if (paramTokenIter.next() instanceof NameToken nameToken) {
                        takenVariables.add(nameToken);
                    } else {
                        return Optional.empty();
                    }

                    if (!(paramTokenIter.next() instanceof ColonToken))
                        return Optional.empty();

                    if (!(paramTokenIter.next() instanceof KeywordToken keywordToken &&
                            keywordToken.heldKeyword.isType(keywordToken.heldKeyword)))
                        return Optional.empty();

                    ProgramToken possibleEnd = paramTokenIter.next();
                    if (possibleEnd == UnfixedOperator.CLOSING_PARENTHESES) {
                        paramCount += 1;
                        break;
                    }
                    if (!(possibleEnd instanceof CommaToken)) return Optional.empty();

                    paramCount += 1;
                }
            }

            int paramTokenCount = (paramCount == 0) ? 0 : paramCount * 4 - 1;
            int afterParamsIndex = paramStart + paramTokenCount + 1;

            int matchingBracePosition;

            if(input.get(afterParamsIndex) == UnfixedOperator.OPENING_BRACE) {
                matchingBracePosition = ((UnfixedOperator) input.get(afterParamsIndex))
                        .findMatchingToken(input, afterParamsIndex);
            } else return Optional.empty();

            List<ProgramToken> parsedStatements = input.subList(afterParamsIndex + 1, matchingBracePosition);
            List<ProgramStatement> heldStatements = StatementConverter.convert(parsedStatements);

            return Optional.of(ConvertResult.of(new FunctionStatement(functionName, takenVariables, heldStatements),
                    matchingBracePosition - i + 1));
        };
    }

    @Override
    public void runStatement(Environment environment) {
        Environment heldEnvironment = new Environment();
        for(ProgramStatement currentStatement: heldStatements) {
            currentStatement.runStatement(heldEnvironment);
        }
    }
}
