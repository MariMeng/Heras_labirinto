package heras_game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

//import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Labirinto {

	// variaveis blocosFantasma
	private static final int CRISTAL_FRAME = 2; // define o intervalo de frames para alternar a visibilidade dos blocos
												// fanstasmas
	private static final int INVENCIBILIDADE = 240; // quantidade de aualizações por frame

	private boolean mostrarBlocos = true; // para verificar se o bloco está ou nçao sendo exibido
	private int tempoBlocoFantasma = 0; // para controlar o tempo em que os blocos ficarão visieis
	private int blocoFantasmaDelay = 60; // definindo o intervalo de frame

	// variaveis abirinto
	private int labirinto[][]; // definindo a matriz do labirinto
	private int largura, altura; // definindo a altura e largura da tela e labirinto
	private int tamBloco; // tamanho de cada bloco do labirinto

	// variaveis de imagem
	private Image fundo;
	private Image bloco;
	private Image blocoBorda;
	private Image[] blocoFantasma;
	private Image[] cristais;

	// variaveis cristais
	private int contadorCristais; // quantos cristais foram pegos
	private int cristalFrameIndice = 0; // indice de animação de cristal
	private int numAnimaCristal = 0; // contador para controlar a animação dos cristais

	private int invulnerabilidade; // temporadorado de invulnerabilidade
	private boolean estaInvisivel = false; // estado de invulnerabilidade

	public Labirinto(int largura, int altura, int tamBloco, int[][] labirintoLayort) {
		this.altura = altura;
		this.largura = largura;
		this.tamBloco = tamBloco;
		this.labirinto = labirintoLayort;

		// verificando o tamanho do labirinto

		blocoFantasma = new Image[7]; // inicializando o array dos frames de blcocos fantasmas
		cristais = new Image[7]; // inicializando o array dos frames de cristais

		// inicializando imagens com Toolkit
		bloco = Toolkit.getDefaultToolkit().getImage("res/bloco101.png");
		fundo = Toolkit.getDefaultToolkit().getImage("res/fundo11.png");
		blocoBorda = Toolkit.getDefaultToolkit().getImage("res/bloco102.png");

		// carregando os labirintos com imagens

		for (int i = 0; i < 7; i++) {
			cristais[i] = Toolkit.getDefaultToolkit().getImage("res/cristal" + (i + 1) + ".png");
		}

		for (int i = 0; i < 7; i++) {
			blocoFantasma[i] = Toolkit.getDefaultToolkit().getImage("res/fantasma" + (i + 1) + ".png");
		}

		// esperando para todas imagens serem carregadas antes de serem usadas com
		// mediaTracker
		MediaTracker tracker = new MediaTracker(new JPanel()); // inicializando

		tracker.addImage(bloco, 1); // adicionando o bloco como identificador 1 pelo traccker
		tracker.addImage(fundo, 0); // adicionando o fundo como identificador 0 pelo tracker

		for (Image c : cristais) {
			tracker.addImage(c, 2); // adicionando as imagnes dos cristais como identicador 2, no loop para tratar
									// todas como parte de um mesmo grupo
		}

		for (Image f : blocoFantasma) {/////////
			tracker.addImage(f, 3);
		}

		try {
			tracker.waitForAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	} // fim do construtor labirinto

	// função para renderizar o labirinto/elementos visuais.
	public void renderizar(Graphics g) {

		// desenhado o fundo do jogo, centralizado e de acordo com o tamanho dos blocos
		g.drawImage(fundo, 0, 0, altura * tamBloco, largura * tamBloco, null);

		tempoBlocoFantasma++;// controla quando os blocos serão ocultados e exibidos

		// condição para que se o tempo exceder, ele mostra ou não o bloco
		if (tempoBlocoFantasma >= blocoFantasmaDelay) {
			tempoBlocoFantasma = 0;
			mostrarBlocos = !mostrarBlocos;
		}

		// lendo a matriz gerada do labirinto com os blocos e cristais
		for (int y = 0; y < altura; y++) {
			for (int x = 0; x < largura; x++) {
				if (labirinto[y][x] == 5) {
					g.drawImage(blocoBorda, x * tamBloco, y * tamBloco, tamBloco, tamBloco, null);
				}
				if (labirinto[y][x] == 1) {
					g.drawImage(bloco, x * tamBloco, y * tamBloco, tamBloco, tamBloco, null);
				}
				if (labirinto[y][x] == 3) {
					g.drawImage(cristais[cristalFrameIndice], x * tamBloco, y * tamBloco, tamBloco, tamBloco, null);
				}
				if (labirinto[y][x] == 4) {
					if (labirinto[y][x] == 4) {
						if (mostrarBlocos) {
							g.drawImage(blocoFantasma[(tempoBlocoFantasma / (blocoFantasmaDelay / 7)) % 7],
									x * tamBloco, y * tamBloco, tamBloco, tamBloco, null);
						}
					}

				}

			}
		}
	}

	public void checarColisaoFantasma(int x, int y) {
		if (x >= 0 && x < largura && y >= 0 && y < altura && labirinto[y][x] == 4 && mostrarBlocos && !estaInvisivel) {

			SistemaPerguntas sistemaPerguntas = new SistemaPerguntas();
			boolean respostaCorreta = sistemaPerguntas.fazerPergunta();

			if (!respostaCorreta) {
				// Reiniciar jogo
				// reiniciarJogo(); // Método que reinicia o jogo se a resposta estiver errada
			} else {
				estaInvisivel = true; // Ativar a invulnerabilidade
				invulnerabilidade = 0; // Reiniciar o temporizador de invulnerabilidade
			}
			// Adicionar comportamento adicional aqui, como penalidades ao jogador
		}
	}

	public void atualizar(int x, int y) {
		// atualiza o frame do cristal (animação)
		numAnimaCristal++;
		// condição para que se o tempo do frame atual exceder, ele mostra o próximo
		if (numAnimaCristal >= CRISTAL_FRAME) {
			numAnimaCristal = 0;
			cristalFrameIndice = (cristalFrameIndice + 1) % cristais.length;
		}

		if (estaInvisivel) {
			invulnerabilidade++;
			if (invulnerabilidade >= INVENCIBILIDADE) {
				estaInvisivel = false;
				invulnerabilidade = 0;
			}

		}

		checarColisaoFantasma(x, y);
		// fazer o temporizador de tempo de vulnerabilidde depois de ser atacado
	}

	// Retorna verdadeiro se o personagem estiver saindo dos limites ou se for um
	// bloco sólido

	public boolean ehParede(int x, int y) {
		if (x < 0 || x >= largura || y < 0 || y >= altura) {
			return true;
		}

		return labirinto[y][x] == 1 || labirinto[y][x] == 5;
	}

	public boolean ehUmCristal(int x, int y) {
		if (x < 0 || x >= largura || y < 0 || y >= altura) {
			return false;
		}
		return labirinto[y][x] == 3;
	}

	public void coletarCristal(int x, int y) {
		if (ehUmCristal(x, y)) {
			labirinto[y][x] = 0;
			contadorCristais++;
		}
	}

	public int cristaisPegos() {
		return contadorCristais;
	}

	public int largura() {
		return this.largura;
	}

	public int altura() {
		return this.altura;
	}

}
