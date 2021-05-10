package Server;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;
    private volatile boolean stop;
    private ExecutorService threadPool;

    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        try (InputStream input = new FileInputStream("resources/config.properties")) {
            Properties prop = new Properties();
            // load a properties file
            prop.load(input);
            // get the property value and print it out
            int threadPoolSize = Integer.parseInt(prop.getProperty("threadPoolSize"));
            this.threadPool = Executors.newFixedThreadPool(threadPoolSize);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void start() {
        new Thread(() -> {
            this.startInner();
        }).start();
    }

    private void startInner(){
        try {
            ServerSocket serverSocket = new ServerSocket(this.port);
            serverSocket.setSoTimeout(this.listeningIntervalMS);

            while(!this.stop) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    this.threadPool.execute(() -> {
                        this.handleClient(clientSocket);
                    });
                } catch (SocketTimeoutException var3) {
                    //System.out.println("no client connected");
                }
            }
            serverSocket.close();
            this.threadPool.shutdownNow();
        } catch (IOException var4) {

        }
    }

    private void handleClient(Socket clientSocket) {
        try {
            this.strategy.ServerStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
            this.stop();
        } catch (IOException var3) {

        }

    }

    public void stop() {
        this.stop = true;
    }
}
