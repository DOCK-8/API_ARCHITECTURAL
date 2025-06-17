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
  @WebMethod
  public String createDataTable(@WebParam(name="tabla")String tabla,@WebParam(name="data")String data) throws Exception{
    MysqlData dtb = new MysqlData("database","EmpresaDB","root","root");
    String[] dataGroup = data.split("-");
    return dtb.createDataTabla(tabla, dataGroup);
  }
  @WebMethod
  public void updateDataTable(){
    MysqlData dtb = new MysqlData("database","EmpresaDB","root","root");
    dtb.updateDataTabla();
  }
  @WebMethod
  public void deleteDataTable(){
    MysqlData dtb = new MysqlData("database","EmpresaDB","root","root");
    dtb.deleteDataTabla();
  }
}
