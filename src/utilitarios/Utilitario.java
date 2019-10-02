package utilitarios;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import dao.EstrategiaDao;



public class Utilitario {
	
	public static String pathCorrente=System.getProperty("user.dir")+System.getProperty("file.separator");;
	
	
	   public static void main(String[] args) {
	    	String texto="4,4";
			
			System.out.println(converteStringParaFloat(texto));
	    }
	
	
	
	public static String converteDateParaString (Date data){
		SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");  		   
		return out.format(data);
	}

	
	public static Date converteStringParaDate(String texto){
		
		Date ret=null;
		if(texto.trim().length()==10 ){
		
			try {
				ret= new SimpleDateFormat("dd/MM/yyyy").parse(texto);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else{
			ret=null;
		}
		return ret;

	}
	
	public static Float converteStringParaFloat(String texto){
		
		texto=texto.replace(",", ".");
		Float f= Float.parseFloat(texto);
		
		return f.floatValue();
	}
	
	public static Date adicionaDiasEmDate(Date data, int qtDias){
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		c.add(Calendar.DATE, qtDias);
		return c.getTime();
	}
	
	public static java.sql.Date converteDataParaSQLData(Date data){
		return new java.sql.Date(data.getTime());
			
	}
	
	public static double converteObjectParaDouble(Object obj){
		
		String s=(String)obj;
		System.out.println("salvando  "+ s);
		return Double.parseDouble(s);	
	}

	
	public static java.sql.Date converteStringParaSQLData(String data){
		if(data==null)
			return null;
		else
			return converteDataParaSQLData(converteStringParaDate(data));			
	}
	
	public static BigDecimal converteStringParaBigDecimal(String texto){
		/*
		para os formatos "123.567,89"
		*/

		//texto=texto.replace(".", "");
		texto=texto.replace(",", ".");
		return new BigDecimal(texto);
	}
	
	
	
	
	public static int converteBigDecimalParaInt(BigDecimal bd){
		return Integer.valueOf(bd.toString());
		
		
	}
	
	
	
	
	
	public static Float converteObjectParaFloat(Object text){
		String texto=(String)text;
		texto=texto.replace(".", "");
		texto=texto.replace(",", ".");
		
		
		String f=(String)texto;
		return Float.parseFloat(f);
		
	}
	
	public static String converteFloatParaString(float numero){
		
		String texto=String.valueOf(numero);
		texto=texto.replace(".", ",");		
		System.out.println("converteeno "+texto);
		return texto; 
	}
	
	public static BigDecimal converteObjectParaBigDecimal(Object text){
		/*
		para os formatos "123.567,89"
		*/
		String texto=(String)text;
		texto=texto.replace(".", "");
		texto=texto.replace(",", ".");
		return new BigDecimal(texto.trim());
	}
	
     public static String escolherArquivo() {
    	
    	String arquivo=null;
    	
        try {

            //Configurando o meu JFileChooser
            JFileChooser jfc = new JFileChooser();
            
            jfc.setSelectedFile(new File("certidao.doc"));
            
            //Configura o filtro de seleção
            jfc.setFileFilter(new FileNameExtensionFilter("Arquivo Word (*.doc)", "doc"));
            
            //Configura o Label do botão de seleção
            jfc.setApproveButtonText("Selecionar");
            //Configura o Título da caixa de Dialogo
            jfc.setDialogTitle("Selecionar");
            //Configura a possíbilidade de selecionar vários arquivos
            jfc.setAcceptAllFileFilterUsed(false);

            int returnVal = jfc.showOpenDialog(null);
                        
            if (!(returnVal == JFileChooser.APPROVE_OPTION)) { // Teste o usuário seleionou algo
                return null;
            }
            
            arquivo = jfc.getSelectedFile().getAbsolutePath();
            
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return arquivo;
    }
    

    
    public static MaskFormatter buscaMascaraProcesso(){
		MaskFormatter mascaraProcesso=null;
		
		try{
			mascaraProcesso = new MaskFormatter("#######-##.####.5.01.####");
			mascaraProcesso.setValidCharacters("0123456789");
		}catch (Exception e){
			System.out.println("Máscara de processo inválida!");
		}
		return mascaraProcesso;		
	}    

    
    public static MaskFormatter buscaMascaraData(){
		MaskFormatter mascaraProcesso=null;
		
		try{
			mascaraProcesso = new MaskFormatter("##/##/####");
			mascaraProcesso.setValidCharacters("0123456789");
		}catch (Exception e){
			System.out.println("Máscara de data inválida!");
		}
		return mascaraProcesso;		
	}
    
    public static MaskFormatter buscaMascaraValor(){
		MaskFormatter mascara=null;
		
		try{
			mascara = new MaskFormatter("###.###.###,##");
			mascara.setValidCharacters("0123456789");
		}catch (Exception e){
			System.out.println("Máscara  inválida!");
		}
		return mascara;		
	}
    
    public static boolean isDataValida(String dataTexto){
    	
    	boolean isValida=true;
    	
    	Date data = null;

    	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    	try {
    		format.setLenient(false);
    		data = format.parse(dataTexto);
    	} 
    	catch (ParseException e) {
    		isValida=false;
    	}
    	return isValida;
    	
    }

	public static boolean isNumeroValido(String textoDoNumero){
		
		/*Verfica se o número em foramto texto é válido
		 3.  1.245,66" -> invalido
		 845.497,6"-> valido
		 845.497, 6"-> invalido
		 045.497,6 "-> valido		
		*/
		
		boolean isValido=true;
		
		String temp= textoDoNumero.trim().replace(".","");
		String textoFormatado= temp.replace(",",".");
				
		try{
			BigDecimal valor=null;
			System.out.println(textoFormatado);
			valor =new BigDecimal(textoFormatado);			
		}catch(Exception e){
			isValido=false;
		}
		
		return isValido;
	}

	public static void populaModeloJTableComResultset(JTable tabela,ResultSet rs) {		

		try {
			int columns = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				Object[] row = new Object[columns];
				for (int i = 1; i <= columns; i++) {
					row[i - 1] = rs.getObject(i);
					 //System.out.println("inserindo "+ row.toString());
				}
				((DefaultTableModel) tabela.getModel()).insertRow(
						rs.getRow() - 1, row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String buscaDataAtual(){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
		return dateFormat.format(new Date());				 
	}
	
	public static String lerTextoDeArquivo(String arquivo){
		String texto=null;
		
	
		try {
			FileReader fr;
			fr = new FileReader(arquivo);
		    BufferedReader buffReader = new BufferedReader(fr);
			
		    String linha;
		    texto="";
		    
		    while ((linha = buffReader.readLine()) != null)	    {    	
		    	texto+=linha;
		    }
		    fr.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

		return texto;
		
	}
	public static void removerTodasLinhaJTable(JTable tabela){
		DefaultTableModel modelo=(DefaultTableModel) tabela.getModel();
		int rowCount = modelo.getRowCount();
		//Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
			modelo.removeRow(i);
		}
	}

	public static double indicePercentagem(int a,int total){
		/*retorna percentgem de a em realção a total em índice
		*/
		
		return Math.round(100*(double)a/total);
	}
	
    public static void resultSetToTableModel(ResultSet rs, JTable table) throws SQLException{
        //Create new table model
        DefaultTableModel tableModel = new DefaultTableModel();

        //Retrieve meta data from ResultSet
        ResultSetMetaData metaData = rs.getMetaData();

        //Get number of columns from meta data
        int columnCount = metaData.getColumnCount();

        //Get all column names from meta data and add columns to table model
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++){
        	
            tableModel.addColumn(metaData.getColumnLabel(columnIndex));
        }

        //Create array of Objects with size of column count from meta data
        Object[] row = new Object[columnCount];

        //Scroll through result set
        while (rs.next()){
            //Get object from column with specific index of result set to array of objects
            for (int i = 0; i < columnCount; i++){
                row[i] = rs.getObject(i+1);
            }
            //Now add row to table model with that array of objects as an argument
            tableModel.addRow(row);
        }

        //Now add that table model to your table and you are done :D
        table.setModel(tableModel);
    }
    
    
    public static void printClassName(Object obj) {
        System.out.println("The class of " + obj +
                           " is " + obj.getClass().getName());
    }
	
}


