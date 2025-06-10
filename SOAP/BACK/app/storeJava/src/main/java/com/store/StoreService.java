package com.store;

import com.database.MysqlData;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;

import java.sql.ResultSet;

@WebService
public class StoreService{
  @WebMethod
  public String helloBro(@WebParam(name="name") String name){
    return "Hello " + name;
  }
  @WebMethod
  public String getDepartamentos() throws Exception{
    MysqlData dtb = new MysqlData("database","EmpresaDB","root","root");
    dtb.getDataColumn("Nombre");
    return "Reviza la consola";
  }
  @WebMethod
  public ResultSet getDataTable() throws Exception{
    MysqlData dtb = new MysqlData("database","EmpresaDB","root","root");
    return dtb.getData();
  }
}
