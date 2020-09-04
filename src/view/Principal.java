package view;

import javax.swing.JOptionPane;

import controller.ProcessosController;

public class Principal {
	public static void main(String[] args) {
		boolean manter = true;
		
		ProcessosController metodo = new ProcessosController();
		JOptionPane.showMessageDialog(null, "Bem vindo ao sistema Process Killer!");
		
		String so = metodo.identificaSO();
		
		while (manter) {
			int input = Integer.parseInt(JOptionPane.showInputDialog("Você tem diversas opções. Escolha: " + "\n" + "1 - Listar os processos ativos"
		+ "2 - Matar o processo através do PID " + "\n" + "3 - Matar o processo através do Nome" + "\n" + "0 - Finalizar"));
			
			switch(input) {
			
			case 1:
				metodo.mostraProcessos(so);
				break;
			
			case 2:
				int pid = Integer.parseInt(JOptionPane.showInputDialog("Insira o PID do processo: "));
				metodo.pidMataProcesso(pid, so);
				break;
				
			case 3:
				String nome = JOptionPane.showInputDialog("Insira o Nome do processo: ");
				metodo.nomeMataProcesso(nome, so);
				break;
			 
			case 0:
				manter = false;
				JOptionPane.showMessageDialog(null, "Finalizado");
				break;
				
			default: JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente");
			break;
				
			}
		}
	}
}
