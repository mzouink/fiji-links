package sc.fiji.links.linux;

import org.scijava.console.AbstractConsoleArgument;
import org.scijava.console.ConsoleArgument;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;
import org.scijava.ui.UIService;

import java.util.LinkedList;

@Plugin(type = ConsoleArgument.class)
public class LinuxPlugin extends AbstractConsoleArgument {
    public static final String PLUGIN_NAME = "test";

    @Parameter
    private UIService uiService;

    @Override
    public void handle(LinkedList<String> linkedList) {
        System.out.println("LinuxPlugin : got handle URI " );
      for(String s: linkedList)
          System.out.println(s);
    }

    @Override
    public Class<LinkedList<String>> getType() {
        return super.getType();
    }
}
