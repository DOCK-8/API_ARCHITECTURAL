package com.SOAPWrapperREST;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Api extends HttpServlet {

    private String message;

    @Override
    public void init() throws ServletException {
        this.message = "Hello World";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String url = "http://localhost:8081/soap";

        String soapRequest = """
            <?xml version="1.0" encoding="UTF-8"?>
            <soapenv:Envelope 
              xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
              xmlns:lab="http://SOAPService.com/">
              <soapenv:Header/>
              <soapenv:Body>
                <lab:getDataTable>
                    <tabla>Departamentos</tabla>
                </lab:getDataTable>
              </soapenv:Body>
            </soapenv:Envelope>
            """;
        try{
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest requestSoap = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Content-Type", "text/xml;charset=UTF-8")
            .header("SOAPAction", "") // SOAPAction vacío
            .POST(HttpRequest.BodyPublishers.ofString(soapRequest))
            .build();

        HttpResponse<String> responseSoap = client.send(requestSoap, HttpResponse.BodyHandlers.ofString());

        // Indicamos que la respuesta será HTML
        response.setContentType("text/html");

        // Obtenemos el canal de salida para enviar HTML al navegador
        PrintWriter out = response.getWriter();
        out.println("<h1> RESPUESTA </h1>");
        out.println(responseSoap.body());
        out.println("<h1> STATUS </h1>");
        out.println("<p>"+responseSoap.statusCode()+"</p>");
        }catch(InterruptedException e){
            System.out.println("Error de peticion");
        }
    }

    @Override
    public void destroy() {
        // Aquí podrías cerrar conexiones o liberar recursos si fuera necesario
    }
}
