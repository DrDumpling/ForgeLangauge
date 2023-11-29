package compiler_base.statements_nodes;

import runtime.Environment;

public interface ProgramNodeStatement {

    void runStatement(Environment environment);
}
