package test;

import IO.SimpleCompressorOutputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class testForUs {
        public static void main(String[] args) {
            String mazeFileName = "savedMaze.txt";
             //Generate new maze
            try {
// save maze to a file
                FileOutputStream file = new FileOutputStream(mazeFileName);
                OutputStream out = new SimpleCompressorOutputStream(file);
                out.write("".getBytes(StandardCharsets.UTF_8));
                //out.write("100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011101110".getBytes(StandardCharsets.UTF_8));
                //System.out.println(out.toString());
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            byte savedMazeBytes[] = new byte[0];
            /*try {
//read maze from file
                InputStream in = new MyDecompressorInputStream(new
                        FileInputStream(mazeFileName));
                savedMazeBytes = new byte[maze.toByteArray().length];
                in.read(savedMazeBytes);
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }*/

//maze should be equal to loadedMaze
        }
    }
