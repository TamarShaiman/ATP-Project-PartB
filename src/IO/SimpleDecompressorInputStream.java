package IO;

import java.io.IOException;
import java.io.InputStream;

public class SimpleDecompressorInputStream extends InputStream {
    private InputStream in;

    public SimpleDecompressorInputStream(InputStream inputStream) {
        this.in = inputStream;
    }

    @Override
    public int read() throws IOException {
        return 0;
    }

    @Override
    public int read(byte b[]) throws IOException {
        int resInd = 0;
        int inInd = 0;
        int len = b.length;
        //b = new byte[len];

        byte[] compressedData = in.readAllBytes();

        for (int j = 0; j < compressedData.length; j++) {
            for (int i = 0; i < compressedData[j]; i++) {
                if (j % 2 == 0) {
                    b[resInd] = 0;
                }
                else{
                    b[resInd] = 1;
                }
                resInd++;
            }
        }
        return len;
    }



}
