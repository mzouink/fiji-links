package sc.fiji.links;

import org.scijava.console.AbstractConsoleArgument;
import org.scijava.console.ConsoleArgument;
import org.scijava.links.LinkService;
import org.scijava.plugin.AbstractHandlerService;
import org.scijava.plugin.Plugin;
import org.scijava.service.AbstractService;
import org.scijava.service.Service;
import org.scijava.startup.StartupService;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

@Plugin(type = ConsoleArgument.class)
public class WindowsSchemeRegister extends AbstractConsoleArgument {

    private final Deque<Runnable> operations = new ArrayDeque<>();
    private static final String URI_SCHEME = "fiji";
//    private static final String EXECUTABLE_PATH = "C:\\path\\to\\Fiji.app\\ImageJ-win64.exe";


    private static boolean isAdmin() {
        try {
            String key = "HKEY_LOCAL_MACHINE\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Policies\\System";
            Process process = Runtime.getRuntime().exec("reg query " + key);
            process.waitFor();
            return process.exitValue() == 0;
        } catch (IOException | InterruptedException e) {
            return false;
        }
    }

    private static String getFijiPath() {
        String currentPath = System.getProperty("user.dir");
        print("Current path: " + currentPath);
        return currentPath;
    }

    private static boolean isURISchemeRegistered(String registryRoot) throws IOException, InterruptedException {
        String checkCommand = "reg query " + registryRoot + "\\" + URI_SCHEME;
        Process process = Runtime.getRuntime().exec(checkCommand);
        int exitCode = process.waitFor();
        return exitCode == 0; // Exit code 0 indicates the command was successful (i.e., the URI scheme exists)
    }

    private static void registerURIScheme(String registryRoot) throws IOException, InterruptedException {
        final String EXECUTABLE_PATH = getFijiPath() + "\\ImageJ-win64.exe";
        print("Executable path: " + EXECUTABLE_PATH);
        String[] commands = {
                "reg add " + registryRoot + "\\" + URI_SCHEME + " /ve /t REG_SZ /d \"URL:" + URI_SCHEME + " Protocol\" /f",
                "reg add " + registryRoot + "\\" + URI_SCHEME + " /v \"URL Protocol\" /t REG_SZ /d \"\" /f",
                "reg add " + registryRoot + "\\" + URI_SCHEME + "\\DefaultIcon /ve /t REG_SZ /d \"" + EXECUTABLE_PATH + ",1\" /f",
                "reg add " + registryRoot + "\\" + URI_SCHEME + "\\shell\\open\\command /ve /t REG_SZ /d \"\\\"" + EXECUTABLE_PATH + "\\\" \\\"%1\\\"\" /f"
        };

        for (String command : commands) {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
        }
    }



    static void print(String text) {
        System.out.println(text);
        String path = System.getProperty("user.home") + "\\Desktop\\tmp.txt";
        try {
            FileWriter fw = new FileWriter(path, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(text);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(LinkedList<String> args) {
            try {
                String registryRoot = isAdmin() ? "HKEY_CLASSES_ROOT" : "HKEY_CURRENT_USER\\Software\\Classes";
                print("Registry root: " + registryRoot);
                if (isURISchemeRegistered(registryRoot)) {
                    print("URI scheme " + URI_SCHEME + " is already registered under " + registryRoot + ".");
                } else {
                    registerURIScheme(registryRoot);
                    print("URI scheme " + URI_SCHEME + " registered successfully under " + registryRoot + ".");
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

    }
}
