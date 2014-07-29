package tomcat;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelloWorldServlet extends HttpServlet
{
  final byte[] msg = "HelloWorld".getBytes();
 
  public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
  {
      response.setContentType("text/plain; charset=UTF-8");
      response.setContentLength(msg.length);

      response.setHeader("ETag", "\"t-53d5df40-18519600\"");
      response.setHeader("Last-Modified", "Mon, 28 Jul 2014 05:27:28 GMT");

      response.setHeader("Connection", "keep-alive");
      response.setHeader("Accept-Ranges", "bytes");
      response.setHeader("Cache-Control", "private, no-cache");

      //response.setHeader("Date", "Mon, 28 Jul 2014 05:27:28 GMT");
      //response.setHeader("Server", "servlet");

      response.getOutputStream().write(msg);
  }
}