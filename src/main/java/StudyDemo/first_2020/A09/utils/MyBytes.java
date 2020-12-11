package StudyDemo.first_2020.A09.utils;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

/**
 * @author kikukk
 */
public class MyBytes {
    byte[] mybytes;
    int lenBytes;

    public MyBytes(byte[] bytes){
        mybytes = bytes;
        lenBytes = mybytes.length;
    }

    private byte popFirstByte(){
        byte tmpByte = mybytes[0];
        System.arraycopy(mybytes,1,mybytes,0,lenBytes-1);
        lenBytes--;
        return tmpByte;
    }

    public byte[] popFirstBytes(){
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte tmpByte;
        while((tmpByte = popFirstByte())!='\t'){
            os.write(tmpByte);
        }
        return os.toByteArray();
    }

    public String popFirstString(){
        StringBuilder stringBuilder = new StringBuilder();
        byte[] bytes = popFirstBytes();
        for(int i = 0;i < bytes.length;i++){
            stringBuilder.append((char)bytes[i]);
        }
        return new String(stringBuilder);
    }

    public byte[] getMybytes(){return mybytes;}

    public String getMyBytesToString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0;i < lenBytes;i++){
            stringBuilder.append((char)mybytes[i]);
        }
        return new String(stringBuilder);
    }

    public void addBytes(byte[] bytes){
        byte[] tmpBytes = new byte[lenBytes+bytes.length];
        System.arraycopy(mybytes,0,tmpBytes,0,lenBytes);
        System.arraycopy(bytes,0,tmpBytes,lenBytes,bytes.length);
        mybytes = tmpBytes;
    }

    public static void main(String[] args) {
        byte[] bytes = "hahah".getBytes();
        MyBytes myBytes = new MyBytes(bytes);
        System.out.println(myBytes.getMyBytesToString());
    }

}
