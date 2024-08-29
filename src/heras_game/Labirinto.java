package heras_game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Labirinto {
	
	//variaveis blocosFantasma
	private boolean mostrarBlocos; //para verificar se o bloco está ou nçao sendo exibido
	private int tempoBlocoFantasma = 0; // para controlar o tempo em que os blocos ficarão visieis
	private int blocoFantasmaDelay; //definindo o intervalo de frame
	
	//variaveis abirinto
	private int labirinto[][]; //definindo a matriz do labirinto
	private int largura, altura; //definindo a altura e largura da tela e labirinto
	private int tamBloco; //tamanho de cada bloco do labirinto
	
	//variaveis de imagem
	private Image fundo;
	private Image monstro[];
	private Image bloco;
	private Image blocoBorda;
	private Image blocoFantasma[];
	private Image cristais[];
	
	//variaveis cristais
	private int contadorCristais; //quantos cristais foram pegos
	private int cristalFrameIndice; // indice de animação de cristal
	private int numAnimaCristal; //contador para controlar a animação dos cristais
	private static final int critalFramePiscar = 60; //define o intervalo de frames para alternar a visibilidade dos blocos fanstasmas
	private static final int cristalAtualiza = 2; //quantidade de aualizações por frame
	
	public Labirinto(int altura, int largura, int tamBloco, int[][] labirintoLayort) {
		this.altura = altura;
		this.largura = largura;
		this.tamBloco = tamBloco;
		this.labirinto = labirintoLayort;
		
		//verificando o tamanho do labirinto
		
	   
		blocoFantasma = new Image[7]; //inicializando o array dos frames de blcocos fantasmas
		cristais = new Image[8]; // inicializando o array dos frames de cristais
		
		//inicializando imagens com Toolkit
		bloco = Toolkit.getDefaultToolkit().getImage("res/");
		fundo = Toolkit.getDefaultToolkit().getImage("res/");
		blocoBorda = Toolkit.getDefaultToolkit().getImage("res/");
		
		//carregando os labirintos com imagens
		
		for(int i = 0;i< 7;i++) {
			cristais[i] = Toolkit.getDefaultToolkit().getImage("res/" + (i+1) + ".png");
		}
		
		for(int i = 0;i< 8;i++) {
			blocoFantasma[i] = Toolkit.getDefaultToolkit().getImage("res/" + (i+1) + ".png");
		}
		
		//esperando para todas imagens serem carregadas antes de serem usadas com mediaTracker
		MediaTracker tracker = new MediaTracker(new JPanel()); //inicializando
		
		tracker.addImage(bloco, 1); //adicionando o bloco como identificador 1 pelo traccker
		tracker.addImage(fundo, 0); //adicionando o fundo como identificador 0 pelo tracker
		
		for(Image c : cristais) {
			tracker.addImage(c, 2);  // adicionando as imagnes dos cristais como identicador 2, no loop para tratar todas como parte de um mesmo grupo
		}
		
		for(Image f : blocoFantasma) {
			tracker.addImage(f, 3);
		}
		
		try {
			tracker.waitForAll();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
	} //fim do construtor labirinto
	
	public void randerizar() {
		
	}
}
