package Client;

import java.io.*;

public class clientStrategyTemp implements IClientStrategy{
    public clientStrategyTemp() {
    }

    public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
        try {
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(inFromServer));
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter toServer = new BufferedWriter(new PrintWriter(outToServer));

            String userCommand;
            do {
                System.out.print(">>");
                userCommand = userInput.readLine();
                toServer.write(userCommand + "\n");
                toServer.flush();
                String serverResponse = fromServer.readLine();
                System.out.println("Server response = " + serverResponse);
            } while(!userCommand.toLowerCase().equals("exit"));

            fromServer.close();
            userInput.close();
            toServer.close();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

    }
}
