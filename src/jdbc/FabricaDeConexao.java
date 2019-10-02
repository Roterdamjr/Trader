package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class FabricaDeConexao {
	private static FabricaDeConexao instancia = new FabricaDeConexao();
	private static String usuario;
	private static String senha;
	
	public static FabricaDeConexao getInstancia() {  
		return instancia; // ... e retorno aqui--->>>>>>>.
	}  
	
	public Connection getConnection(){

		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e){
			throw new RuntimeException(e);
		} 
		
		try{
			/*@TESTE CASA*/
			return DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe",	"trader","trader");
			//return DriverManager.getConnection("jdbc:oracle:thin:@//10.1.38.145:1521/xe",					"sae_dsv","itauna");
			/*@TESTE PRODUCAO*/
			//
					

		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public static String getUsuario() {
		return usuario;
	}

	public static void setUsuario(String usuario) {
		FabricaDeConexao.usuario = usuario;
	}

	public static String getSenha() {
		return senha;
	}

	public static void setSenha(String senha) {
		FabricaDeConexao.senha = senha;
	}
	
	
}
