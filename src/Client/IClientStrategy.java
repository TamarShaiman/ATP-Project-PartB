package Client;

import java.io.InputStream;
import java.io.OutputStream;

public interface IClientStrategy {
    //void applyStrategy(InputStream var1, OutputStream var2);
    void clientStrategy(InputStream inFromServer, OutputStream outToServer);
}
