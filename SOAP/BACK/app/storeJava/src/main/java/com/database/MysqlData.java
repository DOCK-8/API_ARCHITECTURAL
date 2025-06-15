package com.database;

import com.struct.Campo;
import com.struct.Registro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

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

  public List<Registro> getDataTabla(String tabla) throws Exception{
    String query = "SELECT * FROM " + tabla;
    List<Registro> registros = new ArrayList<>();
    try{
      Connection cn = this.connectionDB();
      Statement st = cn.createStatement();
      ResultSet rs = st.executeQuery(query);
      ResultSetMetaData metaData = rs.getMetaData();
      int columnas = metaData.getColumnCount();
      while (rs.next()) {
        Registro registro = new Registro();
        for (int i = 1; i <= columnas; i++) {
          String nombreColumna = metaData.getColumnName(i);
          Object valor = rs.getObject(i);
          Campo campo = new Campo(nombreColumna, valor);
          registro.addCampo(campo);
        }
        registros.add(registro);
      }
    }catch (SQLException e) {
      System.out.println("Error al obtener datos: " + e.getMessage());
    }
    System.out.println("Conexión cerrada con la base de datos " + this.db);
    return registros;
  }
}
