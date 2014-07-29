package bayou;

import bayou.http.HttpResponse;
import bayou.http.HttpServer;

public class HelloWorld
{
    public static void main(String[] args) throws Exception
    {
        HttpServer server = new HttpServer( request-> HttpResponse.text(200, "HelloWorld") );
        //server.conf().trafficDump(System.out::print);
        server.start(); // localhost:8080
    }

}
