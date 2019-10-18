package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Estrategia;
import jdbc.DaoBase;

public class OperacaoDao extends DaoBase{

    public static void main(String[] args) {
    	new OperacaoDao().buscarTodosRS();    
    }
        
    public void inserir(Estrategia neg){

    	try {
			stmt= connection.prepareStatement("insert into tb_operacao  "
					+ "(ativo,vl_compra,stop,gain,gain_parc,quantidade) "
					+ " values(?,?, ?,?,?,?)");
			stmt.setString(1, neg.getAtivo());
			stmt.setBigDecimal(2, neg.getStart());
			stmt.setBigDecimal(3, neg.getStop());
			stmt.setBigDecimal(4, neg.getGain());
			stmt.setBigDecimal(5, neg.getGainParcial());
			stmt.setBigDecimal(6, neg.getQuantidade());
					
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
    }
    
    public void excluir(){
    	try {
			stmt= connection.prepareStatement("delete from tb_operacao");
	
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
    }
    
    public ResultSet buscarTodosRS(){

		String query = "select  ativo,vl_corrente, vl_compra,stop,gain_parc, quantidade,gain"
				+ " from tb_operacao ORDER BY ATIVO";		
		
		executaBusca(query);

		return rs;
    }
    
    
}
