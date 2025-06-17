package com.SOAPWrapperREST;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProyectosApi extends HttpServlet{
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    String data = """
      <lab:getDataTable>
        <tabla>Proyectos</tabla>
      </lab:getDataTable>
    """;
    response.setContentType("text/xml;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.write(SoapFetchStruct.fetchSOAP(data).body());
  }
}
