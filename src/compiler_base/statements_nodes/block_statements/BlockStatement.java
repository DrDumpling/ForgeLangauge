package compiler_base.statements_nodes.block_statements;

import compiler_base.statements_nodes.ProgramNodeStatement;

import java.util.List;

public abstract class BlockStatement implements ProgramNodeStatement<Void> {
    List<ProgramNodeStatement<Void>> heldStatements;
}
