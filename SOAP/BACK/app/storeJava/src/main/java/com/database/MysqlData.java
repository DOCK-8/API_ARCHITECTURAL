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
import java.util.StringJoiner;
import java.util.ArrayList;
import java.lang.StringBuilder;

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
    System.out.println(this.getNameColumn(tabla));
    System.out.println("Conexión cerrada con la base de datos " + this.db);
    return registros;
  }
  public ArrayList<String> getNameColumn(String table) throws Exception{
    String query = "SELECT COLUMN_NAME FROM "+table;
    ArrayList<String> column = new ArrayList<String>();
    try{
      Connection cn = this.connectionDB();
      Statement st = cn.createStatement();
      ResultSet rs = st.executeQuery(query);
      while(rs.next()){
        column.add(rs.getString(1));
      }
    }catch(SQLException e){
      System.out.println("Error de la conexion con la base de datos");
    }
    return column;
  }
  public String createDataTabla(String tabla, String[] data) throws Exception{
    StringBuilder query = new StringBuilder("INSERT INTO ");
    query.append(tabla);
    query.append(" (Nombre, Telefono, Fax) VALUES ");
    StringJoiner dataRegistro = new StringJoiner(",", "(", ")");
    for(String registro : data)
      dataRegistro.add(registro);
    query.append(dataRegistro.toString());
    try{
      Connection cn = this.connectionDB();
      Statement st = cn.createStatement();
      ResultSet rs = st.executeQuery(query.toString());
      return "Registro Insertado Correctament en "+tabla;
    }catch(SQLException e){
      System.out.println("Error en la BD");
      return "No se pudo insertar el Registro en la base de datos\n"+e;
    }
  }
  public void updateDataTabla(){}
  public void deleteDataTabla(){}
}
