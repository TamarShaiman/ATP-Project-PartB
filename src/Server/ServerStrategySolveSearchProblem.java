package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.*;
import algorithms.search.*;

import java.io.*;
import java.util.Hashtable;
import java.util.Properties;



public class ServerStrategySolveSearchProblem implements IServerStrategy {


    static int solutionNumber = 1;
    String tempDirectoryPath;
    Hashtable<byte[],String> hashtableSolutions;      // Hashtable in order to store solutions of mazes

    public ServerStrategySolveSearchProblem() {
        this.tempDirectoryPath = System.getProperty("java.io.tmpdir");
        this.hashtableSolutions = new Hashtable<>();
    }



    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            Solution solution;
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            Maze maze = (Maze) fromClient.readObject();
            byte[] compressedMaze = mazeToCompress(maze);
            String solutionPath = getSolutionFromHash(compressedMaze);
            if(solutionPath == null) {
                SearchableMaze searchableMaze = new SearchableMaze((Maze) maze);
                ASearchingAlgorithm searchingAlgorithm = findSearchAlgorith();
                //BestFirstSearch best = new BestFirstSearch(); // TODO: delete
                 solution = searchingAlgorithm.solve(searchableMaze);
                 saveSolution(compressedMaze,solution);
            }
            else{
                solution = getSolutionByPath(solutionPath);
            }
            toClient.writeObject(solution);
            toClient.flush();
            fromClient.close();
            toClient.close();
        } catch (Exception var6) {
            var6.printStackTrace();   //TODO: decide how to handle exceptions
        }
    }

    private Solution getSolutionByPath(String solutionPath) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(solutionPath));
        Solution solution =(Solution) objectInputStream.readObject();
         return  solution;
    }
    private void saveSolution(byte[] compressedMaze, Solution solution) throws IOException {
        String fileName = String.valueOf(getSolutionNumber());
        String finalPath = getTempDirectoryPath()+fileName+"_"+String.valueOf(System.currentTimeMillis())+".Solution";
        FileOutputStream fileOut = new FileOutputStream(finalPath);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(solution); //TODO: get solution to string and add the whole solution;
        out.close();
        fileOut.close();
        this.hashtableSolutions.put(compressedMaze,finalPath);
        solutionNumber++;

    }
    public static int getSolutionNumber() {
        return solutionNumber;
    }

    public String getTempDirectoryPath() {
        return tempDirectoryPath;
    }

    private String getSolutionFromHash(byte[] compressedMaze) {
        if (this.hashtableSolutions.containsKey(compressedMaze)) {
            return this.hashtableSolutions.get(compressedMaze);
        }
        return null;
    }

    private byte[] mazeToCompress(Maze maze) {
        ByteArrayOutputStream b = new ByteArrayOutputStream(Math.round((maze.toByteArray().length)/8));
        try {
            OutputStream out = new MyCompressorOutputStream(b);
            out.write(maze.toByteArray());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] compressedMaze = b.toByteArray();
        return compressedMaze;
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
