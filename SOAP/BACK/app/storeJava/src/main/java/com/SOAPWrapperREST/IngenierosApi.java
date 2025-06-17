package com.SOAPWrapperREST;

import java.io.PrintWriter;
import java.io.IOException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;

public class IngenierosApi extends HttpServlet{
  @Override
  public void doGet(HttpServletRequest requestSoap, HttpServletResponse responseSoap) throws ServletException, IOException{
    String data = """
      <lab:getDataTable>
        <tabla>Ingenieros</tabla>
      </lab:getDataTable>
    """;
    responseSoap.setContentType("text/xml;charset=UTF-8");
    PrintWriter pw = responseSoap.getWriter();
    pw.write(SoapFetchStruct.fetchSOAP(data).body());
  }
}
