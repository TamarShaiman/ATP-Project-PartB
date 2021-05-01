package Server;

import IO.SimpleDecompressorInputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.BestFirstSearch;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.*;
import java.util.ArrayList;

public class ServerStrategySolveSearchProblem implements IServerStrategy {
    public ServerStrategySolveSearchProblem() {
    }

    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            byte[] compressedMaze = (byte[]) fromClient.readObject();
            int size = compressedMazeSize(compressedMaze);
            byte[] savedMazeBytes = new byte[0];
            ByteArrayInputStream inputStream = new ByteArrayInputStream(compressedMaze);

            try {
                InputStream in = new SimpleDecompressorInputStream(inputStream);  // TODO: Change to MyDecompressorInputStream
                savedMazeBytes = new byte[size];
                in.read(savedMazeBytes);
                in.close();
            } catch (IOException e) {
                e.printStackTrace(); //TODO: decide how to handle exceptions
            }
            Maze maze =  new Maze(savedMazeBytes);
            SearchableMaze searchableMaze = new SearchableMaze(maze);

            BestFirstSearch best = new BestFirstSearch();
            Solution solution = best.solve(searchableMaze);
            toClient.writeObject(solution);
            toClient.flush();
            fromClient.close();
            toClient.close();
        } catch (Exception var6) {
            var6.printStackTrace();   //TODO: decide how to handle exceptions
        }
    }

    private int compressedMazeSize(byte[] compressedMaze) {
        int sum = 0;
        for (int i = 0; i < compressedMaze.length; i++) {
            int val;
            if (compressedMaze[i] > 0) {
                val = compressedMaze[i];
            } else {
                val = 256 + compressedMaze[i];
            }
            sum += val;
        }
        return sum;
    }
}
