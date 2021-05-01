package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
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
        this.threadPool = Executors.newFixedThreadPool(10); //TODO: part C - configuration file integration
    }

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(this.port);
            serverSocket.setSoTimeout(this.listeningIntervalMS);

            while(!this.stop) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    this.threadPool.submit(() -> {
                        this.handleClient(clientSocket);
                    });
                } catch (SocketTimeoutException var3) {
                    //TODO: decide how to handle exceptions
                }
            }
            serverSocket.close();
            this.threadPool.shutdownNow();
        } catch (IOException var4) {
            //TODO: decide how to handle exceptions
        }

    }

    private void handleClient(Socket clientSocket) {
        try {
            this.strategy.applyStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
            this.stop();
        } catch (IOException var3) {
            //TODO: decide how to handle exceptions
        }

    }

    public void stop() {
        this.stop = true;
    }
}
