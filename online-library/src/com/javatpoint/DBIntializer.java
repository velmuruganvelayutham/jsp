package com.javatpoint;

public interface DBIntializer {
  String DRIVER = "oracle.jdbc.driver.OracleDriver";
  String CON_STRING = "jdbc:oracle:thin:@localhost:1521:xe";
  String USERNAME = "system";
  String PASSWORD = "root";
}