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
        int size = 0;
        byte[] input = in.readAllBytes();
        for (int j = 0; j < input.length; j++) {
            size += (int)input[j];
        }
        byte[] res = new byte[size];

        for (int j = 0; j < input.length; j++) {
            for (int i = 0; i < input[j]; i++) {
                if (j % 2 == 0) {
                    res[resInd] = 0;
                }
                else{
                    res[resInd] = 1;
                }
                resInd++;
            }
        }
        b = res;
        return read(b, 0, b.length);
    }



}
