package Server;

import java.io.InputStream;
import java.io.OutputStream;

public interface IServerStrategy {
    void applyStrategy(InputStream var1, OutputStream var2);
}