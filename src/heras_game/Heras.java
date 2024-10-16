package heras_game;

import java.awt.Graphics;

import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.JPanel;
import java.util.ArrayList;

public class Heras {

    private Vetores velocidade; // velocidade atual da heras
    private Vetores posicao; // posição atual da heras
    private int tamanho; // tamanho em pixels da heras
    private int velo; // a velocidade da heras

    // imagens da personagem
    private Image herasDireita[];
    private Image herasEsquerda[];

    private boolean direita; // se heras esta virado para a direita
    private int frameAtual;
    private long ultimoFrameTempo;
    private long intervaloFrame;

    public Heras(int x, int y, int tamanho, int velocidade, String[] direita, String[] esquerda, long intervaloFrame) {

        this.posicao = new Vetores(x, y);
        this.velocidade = new Vetores(0, 0);
        this.tamanho = tamanho;
        this.velo = velocidade;
        this.intervaloFrame = intervaloFrame;
        this.frameAtual = 0;
        this.ultimoFrameTempo = System.currentTimeMillis();
        this.direita = true;

        // inicializando as imagens com tracker
        herasDireita = new Image[7];
        herasEsquerda = new Image[7];

        for (int i = 0; i < 7; i++) {
            herasDireita[i] = Toolkit.getDefaultToolkit().getImage("res/");
        }

        for (int i = 0; i < 8; i++) {
            herasEsquerda[i] = Toolkit.getDefaultToolkit().getImage("res/");
        }

        MediaTracker tracker = new MediaTracker(new JPanel());

        for (Image i : herasDireita) {
            tracker.addImage(i, 2);
        }

        for (Image i : herasEsquerda) {
            tracker.addImage(i, 3);
        }

        try {
            tracker.waitForAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void definindoDirecao(Vetores direcao) {
        this.velocidade = direcao.multiplicar(velo);
        this.direita = direcao.x > 0;
    }

    public void pararMovimento(Vetores direcao) {
        if (this.velocidade.equals(direcao.multiplicar(velo))) {
            this.velocidade = new Vetores(0, 0);
        }
    }

    public void tick(Labirinto labirinto) {
        Vetores proximaPosicao = posicao.somar(velocidade);
        if (!labirinto.ehParede(proximaPosicao.x, proximaPosicao.y)) {
            posicao = proximaPosicao;
        }

        int herasX = posicao.x;
        int herasY = posicao.y;

        labirinto.coletarCristal(herasX, herasY);

        long now = System.currentTimeMillis();

    }

    public void renderizar(Graphics g) {

    }

    public int getX() {
        return posicao.x;
    }

    public int getY() {
        return posicao.y;
    }

}
