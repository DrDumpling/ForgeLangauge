package compiler_base.statements.block_statements;

import compiler_base.statements.ProgramStatement;

import java.util.List;

public abstract class BlockStatement implements ProgramStatement {
    List<ProgramStatement> heldStatements;
}
