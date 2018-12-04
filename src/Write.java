import java.io.*;

class MyWrite extends BufferedWriter {
    static private long startTime = System.currentTimeMillis();

    public MyWrite() throws IOException {
        super(new FileWriter("test.txt"));
    }

    //@Override
    public void writer(String str) throws IOException {
        super.write((System.currentTimeMillis() - startTime) + " "+str);
        newLine();
    }
}
