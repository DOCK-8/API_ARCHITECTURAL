package com.SOAPWrapperREST;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
public class DepartamentosApi extends HttpServlet {
    private String message;
    @Override
    public void init() throws ServletException {
        this.message = "Hello World";
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String data = """
        <lab:getDataTable>
            <tabla>Departamentos</tabla>
        </lab:getDataTable>
        """;
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:8081");
        response.addHeader("Access-Control-Allow-Methods", "GET");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(SoapFetchStruct.fetchSOAP(data).body());
    }
    @Override
    public void doPost(HttpServletRequest requestSoap, HttpServletResponse responseSoap) throws ServletException, IOException{
        responseSoap.setContentType("text/html; charset=UTF-8");
        String contentText;
        StringBuilder body = new StringBuilder();
        BufferedReader br = requestSoap.getReader();
        while((contentText = br.readLine()) != null){
            body.append(contentText);
        }
        PrintWriter out = responseSoap.getWriter();
        out.println("<p>"+body.toString()+"</p>");
    }
    @Override
    public void destroy() {
        // Aquí podrías cerrar conexiones o liberar recursos si fuera necesario
    }
}
