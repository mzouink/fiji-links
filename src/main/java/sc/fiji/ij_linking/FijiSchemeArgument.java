package sc.fiji.ij_linking;

import org.scijava.console.AbstractConsoleArgument;
import org.scijava.console.ConsoleArgument;
import org.scijava.plugin.Plugin;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;

@Plugin(type = ConsoleArgument.class)
public class FijiSchemeArgument extends AbstractConsoleArgument {

    public FijiSchemeArgument() {
        super(1, "--uri");
        Desktop.getDesktop().setOpenURIHandler((event) -> {
            System.out.println("Open URI: " + event.getURI());
            this.handle(new LinkedList<>(Arrays.asList(event.getURI().toString())));
        });
    }

    @Override
    public void handle(final LinkedList<String> args) {
        System.out.println("Got handel request");
        System.out.println(String.join(" ", args));

        // Do something with URL...
//        if (!supports(args)) return;
//        String url = args.removeFirst(); // --run
    }

    @Override
    public boolean supports(final LinkedList<String> args) {
        System.out.println("check support");
        return args.size() > 0 && args.get(0).startsWith("fiji:");
    }
}

