package com.store;
/**
 * Hello world!
 *
 */
import jakarta.xml.ws.Endpoint;

public class App 
{
    public static void main( String[] args )
    {
        Endpoint.publish("http://0.0.0.0:8080/hello", new StoreService());
        System.out.println("Listen in 80 port");
    }
}
