package compiler_base.statements;

import runtime.Environment;

public interface ProgramStatement {

    void runStatement(Environment environment);
}
