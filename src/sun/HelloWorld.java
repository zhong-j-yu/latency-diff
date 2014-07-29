package sun;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.OutputStream;
import java.net.InetSocketAddress;

public class HelloWorld
{
    public static void main(String[] args) throws Exception
    {
        System.setProperty("sun.net.httpserver.nodelay", "true");

        HttpHandler handler = (HttpExchange ex) ->
        {
            Headers headers = ex.getResponseHeaders();
            headers.add("Accept-Ranges", "bytes");
            headers.add("Content-Type", "text/plain; charset=UTF-8");
            headers.add("ETag", "\"t-53d5df40-18519600\"");
            headers.add("Last-Modified", "Mon, 28 Jul 2014 05:27:28 GMT");
            headers.add("Cache-Control", "private, no-cache");
            headers.add("Content-Length", "10");
            headers.add("Connection", "keep-alive");
            //response.putHeader("Date", "Mon, 28 Jul 2014 05:27:28 GMT");
            headers.add("Server", "sun");

            ex.sendResponseHeaders(200, 10);
            OutputStream out = ex.getResponseBody();
            out.write("HelloWorld".getBytes());
            out.close();
        };

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 50);
        server.createContext("/", handler);
        server.setExecutor(null);
        server.start();
    }
}
