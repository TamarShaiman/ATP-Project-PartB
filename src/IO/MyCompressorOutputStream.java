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
    public void write(byte[] b) throws IOException { //each 8 "bits" will perform as byte
        int count = 0;
        int resInd = 0;
        int newSize = (int) Math.ceil(b.length/8.0) ;
        byte[] res = new byte[newSize];
        for (int i = 0; i < b.length; i = i+8) {
            byte newNum = getBinToDec(b, i);
            res[resInd] = newNum;
            resInd++;
        }
        out.write(res);
    }

    private byte getBinToDec(byte[] b, int i) {  //algorithm to convert binary number to decimal
        int sum = 0;
        for (int j = 7; j >= 0; j--) {
            if (i+j < b.length) {
                if (b[i + j] == (byte) 1) {
                    sum += Math.pow(2, j);
                }
            }
        }
        return (byte)sum;
    }
}
