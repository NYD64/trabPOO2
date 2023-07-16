package br.ufsm.csi.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConectaDB {

    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/clube";
    private static final String USER = "postgres";
    private static final String SENHA = "1234";

    public Connection getConexao(){
        Connection conn = null;
        try{
            Class.forName(this.DRIVER);
            conn = DriverManager.getConnection(this.URL, this.USER, this.SENHA);
        }catch(Exception e){
            e.printStackTrace();
        }

        return conn;
    }
}
