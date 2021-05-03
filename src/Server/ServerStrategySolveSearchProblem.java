package Server;

import IO.SimpleDecompressorInputStream;
import algorithms.mazeGenerators.*;
import algorithms.search.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class ServerStrategySolveSearchProblem implements IServerStrategy {
    public ServerStrategySolveSearchProblem() {
    }

    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            Maze maze = (Maze) fromClient.readObject();
            SearchableMaze searchableMaze = new SearchableMaze((Maze)maze);
            ASearchingAlgorithm searchingAlgorithm = findSearchAlgorith();
            //BestFirstSearch best = new BestFirstSearch(); // TODO: delete
            Solution solution = searchingAlgorithm.solve(searchableMaze);
            toClient.writeObject(solution);
            toClient.flush();
            fromClient.close();
            toClient.close();
        } catch (Exception var6) {
            var6.printStackTrace();   //TODO: decide how to handle exceptions
        }
    }

    private ASearchingAlgorithm findSearchAlgorith() {
        ASearchingAlgorithm searchingAlgorithm =  null;
        try (InputStream input = new FileInputStream("resources/config.properties")) {
            Properties prop = new Properties();
            prop.load(input); // load a properties file
            String mazeSearchingAlgorithm = prop.getProperty("mazeSearchingAlgorithm"); // get the property value and print it out
            if(mazeSearchingAlgorithm.compareTo("BestFirstSearch") == 0){
                searchingAlgorithm = new BestFirstSearch();
            }
            else if (mazeSearchingAlgorithm.compareTo("BreadthFirstSearch")== 0){
                searchingAlgorithm = new BreadthFirstSearch();
            }
            else if (mazeSearchingAlgorithm.compareTo("DepthFirstSearch") == 0) {
                searchingAlgorithm = new DepthFirstSearch();
            }
            else { return null;}

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return searchingAlgorithm;
    }

}
