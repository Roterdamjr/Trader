package dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.DaoBase;
import model.Estrategia;

public class EstrategiaDao extends DaoBase{

	public void getConnection(){

		try{
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("org.sqlite.JDBC");
		}catch(Exception e){
			throw new RuntimeException(e);
		} 
		
		try{
			//return DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe",	"trader","trader");
			connection= DriverManager.getConnection("jdbc:sqlite:trader.db");
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public void closeConnection(){
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {	e.printStackTrace();
			}
		}
	}
    
    public void inserir(Estrategia neg) throws Exception{
    	getConnection();
    	stmt= connection.prepareStatement("insert into tb_Estrategia  "
    			+ "(ativo,vl_compra,stop,gain,gain_parc,quantidade) "
    			+ " values(?,?, ?,?,?,?)");
    	    	
    	stmt.setString(1, neg.getAtivo());
    	stmt.setBigDecimal(2, neg.getStart());
    	stmt.setBigDecimal(3, neg.getStop());
    	stmt.setBigDecimal(4, neg.getGain());
    	stmt.setBigDecimal(5, neg.getGainParcial());
    	stmt.setBigDecimal(6, neg.getQuantidade());

    	stmt.execute();

    	if (stmt != null) {
    		try {
    			stmt.close();
    		} catch (SQLException e) {	e.printStackTrace();
    		}
    	}
    	
    	closeConnection();
    }
    
    public void excluirTodas(){
    	getConnection();
    	
    	try {
			stmt= connection.prepareStatement("delete from tb_Estrategia");	
			stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {	e.printStackTrace();
			}
		}
		closeConnection();
    }
    
    public void excluir(int sq_estrategia) throws Exception{
    	getConnection();
    	stmt= connection.prepareStatement("delete from tb_Estrategia where sq_estrategia=?");	
    	stmt.setInt(1, sq_estrategia);
    	stmt.execute();

    	System.out.println("Excluir: "+ sq_estrategia);
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {	e.printStackTrace();
			}
		}
		closeConnection();
    }
    
    public ArrayList<Estrategia> buscarTodos(){
    	getConnection();
		ArrayList<Estrategia> lista = new ArrayList<Estrategia>();
				
		String query = "select ativo,vl_compra,stop,gain, gain_parc,quantidade"
				+ " from tb_Estrategia ORDER BY ATIVO";		
		
		executaBusca(query);

		try {
			while (rs.next()) {
				Estrategia obj=new Estrategia();
				obj.setAtivo(rs.getString(1));
				obj.setGain(rs.getBigDecimal(2));
				obj.setGainParcial(rs.getBigDecimal(3));
				obj.setStart(rs.getBigDecimal(4));
				obj.setStop(rs.getBigDecimal(5));
				lista.add(obj);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		closeConnection();
		
		return lista;
    }
    
    public ResultSet buscarTodosRS(){
    	getConnection();
		String query = "select sq_Estrategia, ativo,vl_compra,stop,gain, gain_parc,quantidade "
				+ " from tb_Estrategia ORDER BY ATIVO";		
		
		executaBusca(query);

		//closeConnection();
		
		return rs;
    }
    
    public ResultSet buscarTodosRSParaOperacao(){
    	getConnection();
		String query = "select ativo,quantidade,vl_compra,gain,stop,gain_parc, sq_estrategia"
				+ " from tb_Estrategia ORDER BY ATIVO";		
		
		executaBusca(query);

		//closeConnection();
		
		return rs;
    }

    
    
    public static void main(String[] args) {
    	new EstrategiaDao().buscarTodos();
  
    	
    }
}
