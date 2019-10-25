package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import utilitarios.Utilitario;
import jdbc.DaoBase;
import model.Operacao;

public class OperacaoDao extends DaoBase{

    public static void main(String[] args) {
    	try {
			new OperacaoDao().updateParaExecutada(106,"01/10/2019"); 
System.out.println("Feito");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
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
    
    public void inserirAberta(Operacao obj) throws Exception{

    	stmt= getConnection().prepareStatement("insert into tb_operacao  "
    			+ "(ativo,vl_corrente, vl_compra,stop,gain_parc,"
    			+ "quantidade,gain, situacao) "
    			+ " values(?, ?, ?,?, ?, ?, ?,?)");
    	stmt.setString(1, obj.getAtivo());
    	stmt.setBigDecimal(2, obj.getVlorCorrente());
    	stmt.setBigDecimal(3, obj.getStart());
    	stmt.setBigDecimal(4, obj.getStop());			
    	stmt.setBigDecimal(5, obj.getGainParcial());
    	stmt.setBigDecimal(6, obj.getQuantidade());
    	stmt.setBigDecimal(7, obj.getGain());
    	stmt.setString(8,"A");
    	stmt.execute();
            
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {	e.printStackTrace();
			}
		}
		
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {	e.printStackTrace();
			}
		}
		
    }
    
    public void inserirExecutada(Operacao obj) throws Exception{

    	stmt= getConnection().prepareStatement("insert into tb_operacao  "
    			+ "(ativo,vl_corrente, vl_compra,stop,gain_parc,"
    			+ "quantidade,gain, situacao) "
    			+ " values(?, ?, ?,?, ?, ?, ?,?)");
    	stmt.setString(1, obj.getAtivo());
    	stmt.setBigDecimal(2, obj.getVlorCorrente());
    	stmt.setBigDecimal(3, obj.getStart());
    	stmt.setBigDecimal(4, obj.getStop());			
    	stmt.setBigDecimal(5, obj.getGainParcial());
    	stmt.setBigDecimal(6, obj.getQuantidade());
    	stmt.setBigDecimal(7, obj.getGain());
    	stmt.setString(8,"E");
    	stmt.execute();
            
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {	e.printStackTrace();
			}
		}
		
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {	e.printStackTrace();
			}
		}
		
    }
        
    public void updateParaExecutada(int sqOperacao, String dataCompra) throws Exception{
       	stmt= getConnection().prepareStatement(
       			//"update tb_operacao  set situacao='E' where sq_operacao=?");
       	"update tb_operacao  set situacao='E' ,dt_compra=? where sq_operacao=?"

       	);
       	
       	stmt.setDate(1, Utilitario.converteStringParaSQLData(dataCompra));
       	stmt.setInt(2,sqOperacao);

    	stmt.execute();
    	
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {	e.printStackTrace();
			}
		}
		
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {	e.printStackTrace();
			}
		}
    	
    }
    
    public void excluir() throws Exception{

    	stmt= getConnection().prepareStatement("delete from tb_operacao");
    	stmt.execute();
      
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {	e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {	e.printStackTrace();
			}
		}
    }
    
    public ResultSet buscarAbertas() throws Exception{

    	String query= "select  ativo, vl_compra,stop,gain_parc,"
    			+ " quantidade,gain,sq_operacao,situacao"
				+ " from tb_operacao  where situacao='A' ORDER BY ATIVO";		

    	stmt= getConnection().prepareStatement(query);
    	rs=stmt.executeQuery();
		
		return rs;
    }
    
    public ResultSet buscarExecutadas() throws Exception{

    	String query= "select  dt_compra, ativo, vl_compra,stop,gain_parc,"
    			+ " quantidade,gain,sq_operacao,situacao"
				+ " from tb_operacao  where situacao='E' ORDER BY ATIVO";		

    	stmt= getConnection().prepareStatement(query);
    	rs=stmt.executeQuery();
		
		return rs;
    }

}
