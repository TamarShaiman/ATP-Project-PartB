package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

public class SimpleCompressorOutputStream extends OutputStream {
    private OutputStream out;

    public SimpleCompressorOutputStream(OutputStream outputStream) {
        this.out = outputStream;
    }

    @Override
    public void write(int b) throws IOException {
        //TODO: we are not using this method because we implement write(byte[]) verify what should we do with this method
        //write(int) needs to be overridden in order to extend OutputStream
    }

    @Override
    public void write(byte[] b) throws IOException {
        int counter = 1;
        byte[] res = new byte[b.length]; //TODO: delete length
        int resInd = 0;
        if(b[0] == 49){
            res[0] = (byte)0;
            resInd ++;
        }
        for (int i = 1; i < b.length; i++) {
            if (b[i-1] == b[i]){
                counter ++;
            }
            else{
                if (counter <=255){
                    res[resInd] = (byte)counter;
                    resInd++;
                }
                else {
                    while (counter > 255) {
                        res[resInd] = (byte) 255;
                        res[resInd + 1] = (byte) 0;
                        resInd += 2;
                        counter -= 255;
                    }
                    res[resInd] = (byte)(counter);
                    resInd ++;
                }
                counter = 1;
            }

        }
        //last iteration
        if (b[b.length-2] == b[b.length-1]){
            res[resInd - 1] ++ ;
        }
        else{
            res[resInd] = 1;
        }
        res = removeZeroes(res);
        System.out.println("Compressor len arr:"+ res.length);
        System.out.println(Arrays.toString(res));
        out.write(res);
    }

    private byte[] removeZeroes(byte[] res) {
        int i = res.length - 1;
        int counterZeroes = 0;
        while (res[i] == 0){
            counterZeroes ++;
            i--;
        }

        int newSize = res.length - counterZeroes;
        byte[] newRes = new byte[newSize];
        for (int j = 0; j < newSize; j++) {
            newRes[j] = res[j];
        }
        return newRes;
    }

}
