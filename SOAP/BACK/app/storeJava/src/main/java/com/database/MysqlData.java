package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlData{
  private String host;
  private String db;
  private String user;
  private String password;
  
  public MysqlData(String host, String database, String nameUser, String passwordUser){
    this.host = host;
    this.db = database;
    this.user = nameUser;
    this.password = passwordUser;
  }
  private Connection connectionDB() throws Exception{
    System.out.println("Conexion exitosa con la base de datos "+this.db);
    try{
      Connection cn = DriverManager.getConnection("jdbc:mysql://"+this.host+":3306/"+this.db, this.user, this.password);
      return cn;
    }catch(SQLException e){
      System.out.println(e);
      return null;
    }
  }

  public void getDataColumn (String column) throws Exception{
    String query = "SELECT * FROM Departamentos";
    try{
      Connection cn = this.connectionDB();
      Statement st = cn.createStatement();
      ResultSet rs = st.executeQuery(query);
      System.out.println("TABLA Departamentos");
      while (rs.next()) {
        String name = rs.getString(column);
        System.out.println(name);
      }
      st.close();
      cn.close();
    }catch(SQLException e){
      System.out.println(e);
    }
    System.out.println("Conexion cerrada con la base de datos "+this.db);
  }

  public ResultSet getData() throws Exception{
    String query = "SELECT * FROM Departamentos";
    try{
      Connection cn = this.connectionDB();
      Statement st = cn.createStatement();
      ResultSet rs = st.executeQuery(query);
      System.out.println("TABLA Departamentos");
      st.close();
      cn.close();
      return rs;
    }catch(SQLException e){
      System.out.println(e);
    }
    System.out.println("Conexion cerrada con la base de datos "+this.db);
    return null;
  }
}
