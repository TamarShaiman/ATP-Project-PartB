package IO;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {
    private InputStream in;

    public MyDecompressorInputStream(InputStream inputStream) {
        this.in = inputStream;
    }

    @Override
    public int read() throws IOException {
        return 0;
    }

    @Override
    public int read(byte b[]) throws IOException {
        //System.out.println("hello from read");
        int index = 0;
        int len = b.length;
        byte[] compressedData = in.readAllBytes();

        for (int j = 0; j < compressedData.length; j++) {
            int num = compressedData[j];
            if (num < 0){
                num = 256 + num ;
            }
            int currInd = 0;
            while((num > 0 || currInd < 8) &&(index< len)){

                    byte val = (byte) (num % 2);
                    b[index++] = val;
                    num = num / 2;
                    currInd++;

            }
        }
        return len;
    }
}
