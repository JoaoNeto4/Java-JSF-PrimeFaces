
package br.com.carros.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FabricaConexao {
 
    private static Connection conexao;//com.mysql.cj.jdbc
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_carros";
    private static final String USER = "root";
    private static final String PASSWD = "root";
    
    public static Connection getConexao(){
        
            try {
                Class.forName("com.mysql.jdbc.Driver");//com.mysql.cj.jdbc com.mysql.jdbc.Driver
                conexao = DriverManager.getConnection(URL, USER, PASSWD);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger("eitaaa: "+FabricaConexao.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return conexao;
    }
    
    public static  void fecharConexao(){
        if(conexao != null){
            try {
                conexao.close();
                conexao = null;
            } catch (SQLException ex) {
                Logger.getLogger(FabricaConexao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
