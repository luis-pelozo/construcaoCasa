package controle;

import java.util.ArrayList;

import javax.lang.model.util.ElementScanner14;
import javax.swing.JOptionPane;
import visualizacao.EntradaSaida;
import modelo.*;


public class Controladora {

	private Casa casa = null;
	
	public void exibeMenu() {
		
		int opcao;
		do{ 
			opcao = EntradaSaida.solicitaOpcao();
			if (this.casa ==null && opcao ==1 || opcao ==2){
				JOptionPane.showMessageDialog(null, "A casa ainda não foi criada");
				
			}else{
					switch (opcao) {
				
					case 0:
						
						this.casa = new Casa();
						
						String descricao = EntradaSaida.solicitaDescricao("casa", 0);
						String cor       = EntradaSaida.solicitaCor();						
						int qtdeJanelas   = EntradaSaida.solicitaQtdeAberturas("janelas");
						while(qtdeJanelas<= 0){
							JOptionPane.showMessageDialog(null, "Informe uma quantidade maior que 0 ");
							qtdeJanelas = EntradaSaida.solicitaQtdeAberturas("janelas");
						
						}

						int qtdePortas    = EntradaSaida.solicitaQtdeAberturas("portas");						
						while(qtdePortas<= 0){
							JOptionPane.showMessageDialog(null, "Informe uma quantidade maior que 0 ");
							qtdePortas = EntradaSaida.solicitaQtdeAberturas("Portas");
						}						

						ArrayList<Aberturas> listaDePortas = new ArrayList<Aberturas>();
						for (int i=0; i<qtdePortas; i++) {
							Porta porta = new Porta();
							porta.setDescricao(EntradaSaida.solicitaDescricao("porta", (i+1)));
							porta.setEstado(EntradaSaida.solicitaEstado("porta"));
							listaDePortas.add(porta);
							
						}
						ArrayList<Aberturas> listaDeJanelas = new ArrayList<Aberturas>();
						for (int i=0; i<qtdeJanelas; i++) {
							Janela janela = new Janela();
							janela.setDescricao(EntradaSaida.solicitaDescricao("janela" , (i+1)));
							janela.setEstado(EntradaSaida.solicitaEstado("janela"));
							listaDeJanelas.add(janela);
						}
						
						this.casa.constroiCasa(descricao, cor, listaDePortas, listaDeJanelas);
						
						System.out.println("Descição da casa: "+ casa.getDescricao()+"\n");
						System.out.println("Cor da casa: "+ casa.getCor()+"\n");
						
						for(Aberturas porta: casa.getListaDePortas()) {
							System.out.println("Descrição da porta: "+ porta.getDescricao()+"\n");
							System.out.println("Estado da Porta: "   + porta.getEstado()+"\n");
						}
						
						for(Aberturas janela: casa.getListaDeJanelas()) {
							System.out.println("Descrição da janela: "+ janela.getDescricao()+"\n");
							System.out.println("Estado da janela: "+ janela.getEstado()+"\n");					
						}
					break;
					
					case 1:
						String tipoAbertura = EntradaSaida.solicitaTipoAbertura();
		
						ArrayList<Aberturas> listaDAberturas = new ArrayList<Aberturas>();
		
						if(tipoAbertura.equals("porta")){
							listaDAberturas = this.casa.getListaDePortas();
		
						}else{
							listaDAberturas = this.casa.getListaDeJanelas();
						}
		
						int posicao = EntradaSaida.solicitaAberturaMover(listaDAberturas);
						int novoEstado = 0;
		
						if(posicao!= 1){
							novoEstado = EntradaSaida.solicitaEstado(tipoAbertura);
							Aberturas abertura = this.casa.retornaAbertura(posicao, tipoAbertura);
							this.casa.moverAbertura(abertura, novoEstado);
							System.out.println("Novo estado da "+tipoAbertura+ ": "+ abertura.getEstado());
						
						}else{
							EntradaSaida.exibeMsgAbertura();
						}
					break;
					
					case 2:
						String informacoes = this.casa.geraInfoCasa();
						EntradaSaida.exibeInfoCasa(informacoes);
					break;					
				}
			}	
		}while (opcao !=3);
		
		EntradaSaida.exibeMsgEncerraPrograma();
		System.exit(0);
	}	
}
