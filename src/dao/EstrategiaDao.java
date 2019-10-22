package dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.DaoBase;
import model.Estrategia;

public class EstrategiaDao extends DaoBase{
	
	
    public static void main(String[] args) {
    	new EstrategiaDao().buscarTodos();
    	
    }
    
    public void inserir(Estrategia neg) throws Exception{

    	//try {
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
/*        } catch (Exception ex) {
            ex.printStackTrace();
        }*/
            
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {	e.printStackTrace();
			}
		}
    }
    
    public void excluirTodas(){
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
    }
    
    public void excluir(int sq_estrategia) throws Exception{

    	stmt= connection.prepareStatement("delete from tb_Estrategia where sq_estrategia=?");	
    	stmt.setInt(1, sq_estrategia);
    	stmt.execute();

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {	e.printStackTrace();
			}
		}
    }
    
    public ArrayList<Estrategia> buscarTodos(){
    	
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
				
				System.out.println(obj.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
    }
    
    public ResultSet buscarTodosRS(){

		String query = "select sq_Estrategia, ativo,vl_compra,stop,gain, gain_parc,quantidade "
				+ " from tb_Estrategia ORDER BY ATIVO";		
		
		executaBusca(query);

		return rs;
    }
    
    public ResultSet buscarTodosRSParaOperacao(){

		String query = "select ativo,quantidade,vl_compra,gain,stop,gain_parc, sq_estrategia"
				+ " from tb_Estrategia ORDER BY ATIVO";		
		
		executaBusca(query);

		return rs;
    }
}
