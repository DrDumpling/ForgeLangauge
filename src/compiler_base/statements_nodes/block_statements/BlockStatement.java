package compiler_base.statements_nodes.block_statements;

import compiler_base.statements_nodes.ProgramNodeStatement;
import runtime.Environment;

import java.util.List;

public abstract class BlockStatement<T> implements ProgramNodeStatement<T> {
    List<ProgramNodeStatement<Void>> heldStatements;

    T runBlock(Environment environment) {
        for(ProgramNodeStatement<Void> heldStatement: heldStatements) {
            heldStatement.runStatement(environment);
            if(environment.hasReturned()) return (T) environment.getReturnedValue();
        }

        return null;
    }
}
