package dummy;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class DummyServer
{
    public static void main(String[] args) throws Exception
    {
        int port = 8080;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Dummy Server started on port "+port);
        while(true)
        {
            Socket socket = serverSocket.accept();
            new Thread( ()->serve(socket) ).start();
        }
    }

    static final String responseStr = "HTTP/1.1 200 OK\r\n" +
        "Accept-Ranges: bytes\r\n" +
        "Content-Type: text/plain;charset=UTF-8\r\n" +
        "ETag: \"t-53d5df40-18519600\"\r\n" +
        "Last-Modified: Mon, 28 Jul 2014 05:27:28 GMT\r\n" +
        "Cache-Control: private, no-cache\r\n" +
        "Content-Length: 10\r\n" +
        "Connection: keep-alive\r\n" +
        "Date: Mon, 28 Jul 2014 05:27:28 GMT\r\n" +
        "Server: Dummy\r\n" +
        "\r\n" +
        "HelloWorld";

    static final byte[] responseBytes = responseStr.getBytes(StandardCharsets.ISO_8859_1);

    static void serve(Socket socket)
    {
        try
        {
            socket.setTcpNoDelay(true);
            int x = 0;
            byte[] buffer = new byte[2048];
            while(true)
            {
                int r = socket.getInputStream().read(buffer);
                if(r==-1)
                    break;

                // read the bytes at least once, even though we are not parsing them.
                for(int i=0; i<r; i++)
                    x += buffer[i];

                // a very crude verification that read() got one GET request
                if(r<4 || buffer[r-4]!='\r' || buffer[r-3]!='\n' || buffer[r-2]!='\r' || buffer[r-1]!='\n' )
                    throw new Exception("not ended in CR LF CR LF as expected");

                OutputStream out = socket.getOutputStream();
                out.write(responseBytes);
                out.flush();
            }
            System.out.println(x);
            socket.close(); // should be in finally{}
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }

}
