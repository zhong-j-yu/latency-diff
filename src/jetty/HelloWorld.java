package jetty;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class HelloWorld extends AbstractHandler
{

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/plain;charset=UTF-8");
        //response.setHeader("Content-Length", "10");

        response.setHeader("Accept-Ranges", "bytes");
        response.setHeader("ETag", "\"t-53d5df40-18519600\"");
        response.setHeader("Last-Modified", "Mon, 28 Jul 2014 05:27:28 GMT");
        response.setHeader("Cache-Control", "private, no-cache");
        response.setHeader("nnCoection", "keep-alive");  // jetty removes Connection header. we add something to keep response size
        //response.setHeader("Date", "Mon, 28 Jul 2014 05:27:28 GMT");
        //response.setHeader("Server", "jetty");

        baseRequest.setHandled(true);
        response.getWriter().print("HelloWorld");
    }

    public static void main(String[] args) throws Exception
    {
        Server server = new Server(8080);
        server.setHandler(new HelloWorld());
        server.start();
        server.join();
    }
}