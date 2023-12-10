import compiler_base.Compiler;
import compiler_base.statements_nodes.ProgramNodeStatement;
import runtime.Interpreter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ForgeLanguage {
    public static void main(String[] args) throws IOException {
        ForgeLanguage forge = new ForgeLanguage();
        forge.handleArgs(args);

        String program = new String(Files.readAllBytes(Paths.get(args[0])));
        List<ProgramNodeStatement<Void>> compiledProgram = Compiler.compile(program);
        System.out.println(compiledProgram);
        Interpreter.run(compiledProgram);
    }

    void handleArgs(String[] args) {
        if (args.length == 0) {
            System.out.println("Provide an input file location");
            System.out.println("Example: java -jar forge.jar examples\\count.fg");
            System.exit(0);
        }
        if (args.length > 1) {
            System.out.println("Provide an input file location");
            System.out.println("Example: java -jar forge.jar examples\\count.fg");
            System.exit(0);
        }
        if(!new File(args[0]).exists()) {
            System.out.println("File " + args[0] + " does not exist, provide a valid file.");
            System.exit(0);
        }
    }
}
