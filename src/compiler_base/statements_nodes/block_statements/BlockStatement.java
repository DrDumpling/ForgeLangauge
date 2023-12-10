package compiler_base.statements_nodes.block_statements;

import compiler_base.statements_nodes.ProgramNodeStatement;
import compiler_base.statements_nodes.ReturnStatement;
import runtime.Environment;

import java.util.List;

public abstract class BlockStatement<T> implements ProgramNodeStatement<T> {
    List<ProgramNodeStatement<Void>> heldStatements;

    // TODO: Create a result type, and return that instead
    T runBlock(Environment environment) {
        for(ProgramNodeStatement<Void> heldStatement: heldStatements) {
            if(heldStatement instanceof ReturnStatement)
                return (T) heldStatement.runStatement(environment);
            heldStatement.runStatement(environment);
        }
        return null;
    }
}
