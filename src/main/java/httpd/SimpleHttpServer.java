package httpd;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

class SimpleHttpServer {

    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress("0.0.0.0", 8090), 0);
            server.createContext("/", new MyHandler());
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class MyHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            System.out.printf("%s: %s%n", httpExchange.getRemoteAddress(), httpExchange.getRequestURI());
            String response = "Nothing to see here";
            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}

