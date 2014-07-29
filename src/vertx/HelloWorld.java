package vertx;

import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerResponse;
import org.vertx.java.platform.Verticle;

// command line:
// set JAVA_HOME
// vert.x-2.1.2/bin/vertx run src/vertx/HelloWorld.java

public class HelloWorld extends Verticle
{
    @Override
    public void start()
    {
        HttpServer server = vertx.createHttpServer();

        server.requestHandler( request->
        {
            HttpServerResponse response = request.response();

            response.putHeader("Accept-Ranges", "bytes");
            response.putHeader("Content-Type", "text/plain; charset=UTF-8");
            response.putHeader("ETag", "\"t-53d5df40-18519600\"");
            response.putHeader("Last-Modified", "Mon, 28 Jul 2014 05:27:28 GMT");
            response.putHeader("Cache-Control", "private, no-cache");
            response.putHeader("Content-Length", "10");
            response.putHeader("Connection", "keep-alive");
            response.putHeader("Date", "Mon, 28 Jul 2014 05:27:28 GMT");
            response.putHeader("Server", "vertx");

            response.end("HelloWorld");
        });

        server.listen(8080, "localhost");
    }
}
