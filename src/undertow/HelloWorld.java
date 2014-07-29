package undertow;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HeaderMap;
import io.undertow.util.Headers;

public class HelloWorld
{
    public static void main(final String[] args) {
        Undertow server = Undertow.builder()
            .addHttpListener(8080, "localhost")
            .setHandler(exchange ->
            {
                HeaderMap headers = exchange.getResponseHeaders();
                headers.add(Headers.ACCEPT_RANGES, "bytes");
                headers.put(Headers.CONTENT_TYPE, "text/plain; charset=UTF-8");
                headers.add(Headers.ETAG, "\"t-53d5df40-18519600\"");
                headers.add(Headers.LAST_MODIFIED, "Mon, 28 Jul 2014 05:27:28 GMT");
                headers.add(Headers.CACHE_CONTROL, "private, no-cache");
                headers.add(Headers.CONTENT_LENGTH, 10);
                //headers.add(Headers.CONNECTION, "keep-alive");
                //headers.add(Headers.DATE, "Mon, 28 Jul 2014 05:27:28 GMT");
                headers.add(Headers.SERVER, "undertow");

                exchange.getResponseSender().send("HelloWorld");
            }).build();
        server.start();
    }
}
