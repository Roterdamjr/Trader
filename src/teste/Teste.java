package teste;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import utilitarios.Utilitario;

public class Teste {

    
    public static void main(String[] args) {
    	//new Teste().executar();
    	System.out.println(Utilitario.buscaDataAtual());
    	
    	
    }
    
    private void executar(){
    	Date dataCorrente = Calendar.getInstance().getTime();
  	 String dataRet=new SimpleDateFormat("dd/MM/yyyy").format(dataCorrente);

    }
}
