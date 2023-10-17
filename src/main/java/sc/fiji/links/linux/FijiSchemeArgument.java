package sc.fiji.links.linux;

import org.scijava.InstantiableException;
import org.scijava.console.AbstractConsoleArgument;
import org.scijava.console.ConsoleArgument;
import org.scijava.plugin.Plugin;

import java.awt.*;
import java.util.LinkedList;

@Plugin(type = ConsoleArgument.class)
public class FijiSchemeArgument extends AbstractConsoleArgument {

    public FijiSchemeArgument() {
        System.out.println("init FijiSchemeArgument");
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
            Desktop.getDesktop().setOpenURIHandler((event) -> {
                System.out.println("Open URI: " + event.getURI());
                try {
                    FijiSchemeService.process(event.getURI());
                } catch (InstantiableException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    @Override
    public void handle(LinkedList<String> linkedList) {
        System.out.println("FijiSchemeArgument : got URI " + linkedList);
//        throw new UnsupportedOperationException("Not supported method.");
    }
}