package Application.ShareScreen.App;

import Application.ShareScreen.System.LocalComputer;

/**
 * @author kikukk
 */
public class ServerApp
{
    public static void main(String[] args) {
        LocalComputer.init();
        Cli.init();
    }
}
