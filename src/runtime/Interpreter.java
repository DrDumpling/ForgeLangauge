package runtime;

import compiler_base.statements_nodes.ProgramNodeStatement;
import compiler_base.statements_nodes.block_statements.FunctionStatement;

import java.util.List;
import java.util.Objects;

public class Interpreter {
    public static void run(List<ProgramNodeStatement> compiledProgram) {
        Environment environment = new Environment();

        ProgramNodeStatement mainStatement = compiledProgram.stream().filter(
                x -> x instanceof FunctionStatement functionStatement &&
                Objects.equals(functionStatement.functionName, "main")
        ).findFirst().get();

        mainStatement.runStatement(environment);
    }
}
