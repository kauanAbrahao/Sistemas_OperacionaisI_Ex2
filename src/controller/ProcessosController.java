package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class ProcessosController {
	
	public ProcessosController(){
		super();
	}
	
	//------------------//--------------------------//---------------------//---------------
	public String identificaSO() {
		
		String so = System.getProperty("os.name");
		if (so.contains("Windows") || so.contains("Linux")) {
			JOptionPane.showMessageDialog(null, "Detectamos que você utiliza o " + so + ". Seu Sistema Operacional é compatível com o Process Killer!");
		}
		else {
			JOptionPane.showMessageDialog(null, "Detectamos que você utiliza o " + so + ". Infelizmente, seu Sistema Operacional n�o � compat�vel com"
					+ " o Process Killer. As funcionalidades do programa podem estar comprometidas");
		}
		return so;
	}
	
	//----------------//---------------------------//------------------------//----------------
	public void mostraProcessos(String so) {
		if (so.contains("Windows")) {
		try {
			Process p = Runtime.getRuntime().exec("tasklist");
			InputStream fluxo = p.getInputStream();//
			InputStreamReader leitor = new InputStreamReader(fluxo);//
			BufferedReader buffer = new BufferedReader(leitor); //essas linhas s�o padr�es para leitura de processos.
			String linha = buffer.readLine();
			while (linha != null) {
				System.out.println(linha);
				linha = buffer.readLine();
			}
			fluxo.close();
			leitor.close();
			buffer.close();
			JOptionPane.showMessageDialog(null, "Os processos ativos estão listados em seu console!");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
		else {
			if (so.contains("Linux")) {
				Process p;
				try {
					p = Runtime.getRuntime().exec("ps aux");
					InputStream fluxo = p.getInputStream();//
					InputStreamReader leitor = new InputStreamReader(fluxo);//
					BufferedReader buffer = new BufferedReader(leitor); //essas linhas s�o padr�es para leitura de processos.
					String linha = buffer.readLine();
					while (linha != null) {
						System.out.println(linha);
						linha = buffer.readLine();
					}
					buffer.close();
					fluxo.close();
					leitor.close();
					
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Os processos ativos estão listados em seu console!");
				
				
			}
			else {
				JOptionPane.showMessageDialog(null, "Infelizmente, o programa não é compatível com seu Sistema Operacional");
			}
		}
	}
	
	//---------------------//----------------------//-------------------------//----------------------//--------------------//-------------
	public void pidMataProcesso(int pid, String so) {
		StringBuffer buffer = new StringBuffer();
		if (so.contains("Windows")) {
			String cmdPID = "TASKKILL /PID ";
			buffer.append(cmdPID);
			buffer.append(pid);
		}
		else {
			String cmdPID = "kill -9 ";
			buffer.append(cmdPID);
			buffer.append(pid);
		}
		
		
		callProcess(buffer.toString(), so);
		
	}
	
	//---------------//----------------//--------------------------------//-----------------------------------//-------------------//-------
	public void nomeMataProcesso(String nome, String so) {
		if (so.contains("Windows")) {
		String cmdNome = "TASKKILL /IM ";
		StringBuffer buffer = new StringBuffer();
		buffer.append(cmdNome);
		buffer.append(nome);
		String exe = buffer.toString();
		if (exe.contains(".exe") == false) {
			buffer.append(".exe");
		}
		
		callProcess(buffer.toString(), so);
	}
		else {
			if (so.contains("Linux")) {
				String cmdNome = ("pkill -f ");
				StringBuffer buffer = new StringBuffer();
				buffer.append(cmdNome);
				buffer.append(nome);
				callProcess(buffer.toString(),so);
			}
			
			else {
				
				JOptionPane.showMessageDialog(null, "Seu sistema não é compatível com esta aplicação!");
			}
			
			
		}
	}
	 
	
	//-----------------------//------------------------------//-----------------------------------//--------------------------------//---
	void callProcess(String process, String so) {
	
	if (so.contains("Windows")) {
		try {
			Runtime.getRuntime().exec(process);
		} catch (IOException e) {
			String msgError = e.getMessage();
			if (msgError.contains("740")) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("cmd /c ");
				buffer.append(process);
				
				try {
					Runtime.getRuntime().exec(process);
				} catch (IOException e1) {
					e1.printStackTrace();
				}	
			}
			else {
				e.printStackTrace();
			}
		}
	}
	else {
		try {
			Runtime.getRuntime().exec(process);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	JOptionPane.showMessageDialog(null, "Processo finalizado");
	}
}
