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
            int num = compressedData[j];
            if (num < 0){
                num = 256 + num ;
            }
            if (j == 0 && compressedData[0] == 0){
                continue;
            }
            for (int i = 0; i < num; i++) {
                    if (j % 2 == 0) {
                        b[resInd] = 0;
                    } else {
                        b[resInd] = 1;
                    }
                    resInd++;
            }
        }

        return len;
    }



}
