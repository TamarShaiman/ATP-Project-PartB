package Server;

import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.*;

import java.io.*;
import java.util.ArrayList;

public class ServerStrategyGenerateMaze implements IServerStrategy {
    public ServerStrategyGenerateMaze() {
    }

    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            int[] mazeSizes = (int[]) fromClient.readObject();
            MyMazeGenerator MMG = new MyMazeGenerator();
            Maze maze = MMG.generate(mazeSizes[0], mazeSizes[1]);
            byte[] mazeToByteArray = maze.toByteArray();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            try {
                OutputStream out = new SimpleCompressorOutputStream(outputStream); //TODO: change to myCompressor
                out.write(maze.toByteArray());
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();                    //TODO: decide how to handle exceptions

            }
            toClient.writeObject(outputStream);
            toClient.flush();
            fromClient.close();
            toClient.close();
        } catch (Exception var6) {
            var6.printStackTrace();                    //TODO: decide how to handle exceptions

        }
    }
}