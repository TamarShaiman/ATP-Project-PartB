package IO;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {
    private OutputStream out;

    public MyCompressorOutputStream(OutputStream outputStream) {
        this.out = outputStream;
    }

    @Override
    public void write(int b) throws IOException {

    }

    @Override
    public void write(byte[] b) throws IOException {
        int count = 0;
        int resInd = 0;
        int newSize = Math.round(b.length/8);
        byte[] res = new byte[newSize];
        for (int i = 0; i < b.length; i = i+8) {
            byte newNum = getBinToDec(b, i);
            res[resInd] = newNum;
            resInd++;
        }
        out.write(res);
    }

    private byte getBinToDec(byte[] b, int i) {
        int sum = 0;
        for (int j = 7; j > 0; j--) {
            if (b[i+j] == (byte)1){
                sum += Math.pow(2, j);
            }
        }
        return (byte)sum;
    }
}
