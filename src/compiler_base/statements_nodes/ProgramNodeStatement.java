package compiler_base.statements_nodes;

import runtime.Environment;

public interface ProgramNodeStatement<T> {

    T runStatement(Environment environment);
}
