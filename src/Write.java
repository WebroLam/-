import java.io.*;

class MyWrite {
    static public long startTime = System.currentTimeMillis();
    static FileWriter one;

    static {
        try {
            int i = 1;
            File file;
            do {
                file = new File("test_" + i + ".txt");
                i++;
            }
            while (file.exists());

            one = new FileWriter(file, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    MyWrite()   {
    }

    static public void write(char heroNum, String content_writen) throws IOException {
        one.write(heroNum + " " + (System.currentTimeMillis() - startTime )+ " " + content_writen + "\n");
        one.flush();
    }
}
