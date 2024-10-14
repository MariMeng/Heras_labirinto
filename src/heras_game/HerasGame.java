package heras_game;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HerasGame extends JPanel implements ActionListener, KeyListener {

	private static final int BLOCO_TAM = 40;
	private static final int LAB_COMPRI = 17;
	private static final int LAB_LARG = 17;

	private Labirinto labirinto;
	private Heras heras;
	private Image fundo;
	private JButton start;
	private JButton som;
	private JButton moduloPergunta;
	private boolean iniciar;
	private JPanel tituloPainel;

	public HerasGame(int[][] dimensoes) {

		String[] esquerda = {
				"res/esq1.png",
				"res/esq2.png",
				"res/esq3.png",
				"res/esq4.png",
				"res/esq5.png"
		};

		String[] direita = {
				"res/dir1.png",
				"res/dir2.png",
				"res/dir3.png",
				"res/dir4.png",
				"res/dir5.png",
				"res/dir6.png"

		};

		setLayout(new CardLayout()); // para alternar entre o menu e o jogo
		iniciar = false;

		// para carregar a imagem de fundo

		fundo = new ImageIcon("res/fundoMenu").getImage();
		if (fundo == null) {
			System.err.println("Erro ao carregar imagem de fundo");
		}

		if (dimensoes.length != LAB_COMPRI || (LAB_LARG > 0 && dimensoes[0].length != LAB_COMPRI))
			throw new IllegalArgumentException("O tamanho do labirinto não corresponde");

		// configuração do painel de título

		tituloPainel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (fundo != null) {
					g.drawImage(fundo, 0, 0, getX(), getY(), this);// desenha o fundo
				}
				start.paint(g);
			}
		};

		tituloPainel.setLayout(new GridBagLayout());
		start = new JButton("Iniciar Jogo");
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!iniciar) {
					startGame();
				}
			}
		});

		// adicionar o botao no painel de titulo
		// Adiciona o botão ao painel de título
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.insets = new Insets(150, 20, 20, 20);
		tituloPainel.add(start, gbc);

		// Configuração do painel de jogo, como letras, fonte, do que ficara escirto na
		// tela:

		JPanel gamePainel = new JPanel() {// gampPainel é um painel de nterface grafica onde o jogo sera renderezado

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);// garante que o áinel seja limpo antes de desenhar qualquerc coisa
				if (iniciar) {
					labirinto.renderizar(g);
					heras.renderizar(g);
					g.setColor(Color.WHITE);
					g.setFont(new Font("Arial", Font.PLAIN, 20));
					g.drawString("Cristais: " + labirinto.cristaisPegos(), 10, 20);
					g.setColor(Color.WHITE);
					g.drawString("Posicao: " + heras.getX() + "," + heras.getY(), LAB_LARG - 150, 20);

				}

			}

		};

		// Inicializando labirinto e heras
		add(tituloPainel, "Titulo");
		add(gamePainel, "Game");

		labirinto = new Labirinto(LAB_COMPRI, LAB_LARG, BLOCO_TAM, dimensoes);
		heras = new Heras(1, 1, 40, 1, direita, esquerda, 180);

		// Timer para atualizar o jogo:

		Timer timer = new Timer(5, this);
		timer.start();
		addKeyListener(this); // adiciona o ouvnte de eventos de teclado ao jogo
		setFocusable(true); // permite que receba eventos do teclado

	}

	private void startGame() {
		iniciar = true;
		((CardLayout) getLayout()).show(this, "Game");
		Timer timer = new Timer(2000, this);
		timer.start();
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		heras.definindoDirecao(null);
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		heras.definindoDirecao(null);
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (iniciar) {
			heras.tick(labirinto);
			labirinto.atualizar(heras.getX() / BLOCO_TAM, heras.getY() / BLOCO_TAM);
			repaint();
		}

		// TODO Auto-generated method stub

	}

	// MENU
	public static void main(String[] args) {
		JFrame frame = new JFrame("Heras");
		HerasGame game = new HerasGame();
		frame.add(game);

		// TODO Auto-generated method stub

	}

}
