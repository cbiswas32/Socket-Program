import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Clients {

    public static void main(String[] args) {

        /*
         * To connect to another machine we need
         * a socket connection.
         * A socket connection means the two machines
         * have information about each other’s
         * network location (IP Address) and TCP port.
         * The java.net.Socket class represents a Socket.
         * To open a socket:
         */
        Socket socket = null;
        /*
         * The first argument –
         * IP address of Server. ( 127.0.0.1 is the IP address of localhost,
         * where code will run on the single stand-alone machine).
         * The second argument – TCP Port.
         * (Just a number representing which application to
         * run on a server. For example, HTTP runs on port 80. Port number can be from 0
         * to 65535)
         */

        /*
         * To communicate over a socket connection,
         * streams are used to both input and output the data.
         */
        InputStreamReader inputstreamReader = null;
        OutputStreamWriter outputstreamWriter = null;

        /*
         * To increase the efficiency we will use buffer reader
         * To Read more charecter once.
         */

        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {
            socket = new Socket("localhost", 1234);
            inputstreamReader = new InputStreamReader(socket.getInputStream());
            outputstreamWriter = new OutputStreamWriter(socket.getOutputStream());

            bufferedReader = new BufferedReader(inputstreamReader);
            bufferedWriter = new BufferedWriter(outputstreamWriter);

            Scanner scanner = new Scanner(System.in);

            while (true) {

                /* Write Msg to Server */
                String msgToSend = scanner.nextLine();

                bufferedWriter.write(msgToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();

                /* Read What Server Response */
                System.out.println("Server: " + bufferedReader.readLine());

                if (msgToSend.equalsIgnoreCase("close")) {
                    break;
                }

            }

        }

        catch (IOException e) {
            System.out.println(e);
        }

        finally {
            try {
                if (socket != null) {
                    socket.close();
                }

                if (inputstreamReader != null) {
                    inputstreamReader.close();
                }

                if (outputstreamWriter != null) {
                    outputstreamWriter.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }

            }

            catch (IOException e) {
                System.out.println(e);

            }
        }

    }

}
