import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ForgeLanguage {
    public static void main(String[] args) throws IOException {
        ForgeLanguage forge = new ForgeLanguage();
        forge.handleArgs(args);

        String program = new String(Files.readAllBytes(Paths.get(args[0])));
        System.out.println(program);
    }

    void handleArgs(String[] args) {
        if (args.length == 0) {
            System.out.println("Provide an input file location");
            System.out.println("Example: java -jar forge.jar examples\\count.txt");
            System.exit(0);
        }
        if (args.length > 1) {
            System.out.println("Provide an input file location");
            System.out.println("Example: java -jar forge.jar examples\\count.txt");
            System.exit(0);
        }
        if(!new File(args[0]).exists()) {
            System.out.println("File " + args[0] + " does not exist, provide a valid file.");
            System.exit(0);
        }
    }
}
