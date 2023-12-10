package compiler_base.statements_nodes;

import compiler_base.statements_nodes.block_statements.FunctionStatement;
import compiler_base.statements_nodes.block_statements.IfStatement;
import compiler_base.statements_nodes.evaluated.BasicValueNode;
import compiler_base.statements_nodes.evaluated.EvaluatedNode;
import compiler_base.statements_nodes.evaluated.FunctionCallNode;
import compiler_base.statements_nodes.evaluated.RelationalNode;
import compiler_base.statements_nodes.evaluated.math.AdditiveNode;
import compiler_base.statements_nodes.evaluated.math.MultiplicativeNode;
import compiler_base.tokens.ProgramToken;
import tools.Pattern;
import tools.PatternConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class StatementConverter {
    static List<Pattern<ProgramToken, ProgramNodeStatement>> patterns =  new ArrayList<>() {{
        add(FunctionStatement.getPattern());
        add(IfStatement.getPattern());
        add(ReturnStatement.getPattern());
        add(FunctionCallStatement.getPattern());
    }};

    static List<Function<List<ProgramToken>, Optional<EvaluatedNode>>> nodeMatchers = Arrays.asList(
            MultiplicativeNode::matches,
            AdditiveNode::matches,
            RelationalNode::matches,
            FunctionCallNode::matches,
            BasicValueNode::matches
    );

    public static List<ProgramNodeStatement> convert(List<ProgramToken> tokens) {
        return new PatternConverter<ProgramToken, ProgramNodeStatement>().convert(
                tokens,
                patterns
        );
    }

    public static EvaluatedNode evaluateTokens(List<ProgramToken> inputTokens) {
        for (Function<List<ProgramToken>, Optional<EvaluatedNode>> matcher : nodeMatchers) {
            Optional<EvaluatedNode> node = matcher.apply(inputTokens);
            if (node.isPresent()) {
                return node.get();
            }
        }
        throw new RuntimeException("illegal state!!: " + inputTokens);
    }
}