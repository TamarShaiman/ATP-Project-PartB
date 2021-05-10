package Server;

import IO.MyCompressorOutputStream;
import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.Executors;

public class ServerStrategyGenerateMaze implements IServerStrategy {
    public ServerStrategyGenerateMaze() {
    }

    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            int[] mazeSizes = (int[]) fromClient.readObject();
            AMazeGenerator mazeGenerator = findMazeGenerator();
            Maze maze = mazeGenerator.generate(mazeSizes[0], mazeSizes[1]);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            try {
                OutputStream out = new MyCompressorOutputStream(outputStream);
                out.write(maze.toByteArray());
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();                    //TODO: decide how to handle exceptions
            }
            toClient.writeObject(outputStream.toByteArray());
            toClient.flush();
            fromClient.close();
            toClient.close();
        } catch (Exception var6) {
            var6.printStackTrace();                    //TODO: decide how to handle exceptions

        }
    }

    private AMazeGenerator findMazeGenerator() {
        AMazeGenerator mazeGenerator =  null;
        try (InputStream input = new FileInputStream("resources/config.properties")) {
            Properties prop = new Properties();
            prop.load(input); // load a properties file
            String mazeGeneratingAlgorithm = prop.getProperty("mazeGeneratingAlgorithm"); // get the property value and print it out
            if(mazeGeneratingAlgorithm.compareTo("EmptyMazeGenerator") == 0){
                 mazeGenerator = new EmptyMazeGenerator();
            }
            else if (mazeGeneratingAlgorithm.compareTo("SimpleMazeGenerator")== 0){
                 mazeGenerator = new SimpleMazeGenerator();
            }
            else if (mazeGeneratingAlgorithm.compareTo("MyMazeGenerator") == 0) {
                 mazeGenerator = new MyMazeGenerator();
            }
            else { return null;}

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return mazeGenerator;
    }
}