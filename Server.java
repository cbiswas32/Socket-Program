import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        Socket s = null;
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        BufferedReader br = null;
        BufferedWriter bw = null;

        ServerSocket ss = null;

        ss = new ServerSocket(1234);

        while (true) {
            try {
                s = ss.accept();
                isr = new InputStreamReader(s.getInputStream());
                osw = new OutputStreamWriter(s.getOutputStream());
                br = new BufferedReader(isr);
                bw = new BufferedWriter(osw);

                while (true) {
                    String msgFromClient = br.readLine();
                    System.out.println("Client: " + msgFromClient);

                    bw.write("MSG Received");
                    bw.newLine();
                    bw.flush();

                    if (msgFromClient.equalsIgnoreCase("close")) {
                        break;
                    }

                }

                s.close();
                isr.close();
                osw.close();
                br.close();
                bw.close();

            }

            catch (IOException e) {
                System.out.println(e);
            }
        }

    }

}