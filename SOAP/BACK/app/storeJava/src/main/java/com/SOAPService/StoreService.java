package com.SOAPService;

import com.database.MysqlData;
import com.struct.Registro;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;

import java.util.List;

@WebService
public class StoreService{
  @WebMethod
  public List<Registro> getDataTable(@WebParam(name="tabla") String tabla) throws Exception{
    MysqlData dtb = new MysqlData("database","EmpresaDB","root","root");
    System.out.println("Reviza la consola :)");
    return dtb.getDataTabla(tabla);
  }
}
