package heras_game;

import java.awt.Graphics;

import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class Heras {

    private Vetores velocidade; // velocidade atual da heras
    private Vetores posicao; // posição atual da heras
    private int tamanho; // tamanho em pixels da heras
    private int velo; // a velocidade da heras

    // imagens da personagem
    private Image[] herasDireita;
    private Image[] herasEsquerda;

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
        herasDireita = new Image[4];
        herasEsquerda = new Image[4];

        for (int i = 0; i < 4; i++) {
            herasDireita[i] = Toolkit.getDefaultToolkit().getImage("res/direita" + (i + 1) + ".png");
        }

        for (int i = 0; i < 4; i++) {
            herasEsquerda[i] = Toolkit.getDefaultToolkit().getImage("res/esq" + (i + 1) + ".png");
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
        if (this.velocidade.iguais(direcao.multiplicar(velo))) {
            this.velocidade = new Vetores(0, 0);
        }
    }

    public void tick(Labirinto labirinto) {
        // Calcula a próxima posição com base na velocidade atual
        Vetores proximaPosicao = posicao.somar(velocidade);

        // Verifica se a próxima posição não está em uma parede
        if (!labirinto.ehParede(proximaPosicao.x, proximaPosicao.y)) {
            posicao = proximaPosicao;
        }

        // Verifica colisões e coleta de cristais
        int herasX = posicao.x;
        int herasY = posicao.y;
        labirinto.checarColisaoFantasma(herasX, herasY); // Colisão com fantasmas
        labirinto.coletarCristal(herasX, herasY); // Coleta de cristais

        // Gerencia a animação dos frames
        long now = System.currentTimeMillis();
        Image[] framesAtuais = direita ? herasDireita : herasEsquerda; // Escolhe o conjunto de frames com base na
                                                                       // direção

        // Verifica se o tempo para mudar de frame já passou
        if (now - ultimoFrameTempo >= intervaloFrame) {
            frameAtual = (frameAtual + 1) % framesAtuais.length; // Atualiza o frame atual
            ultimoFrameTempo = now; // Atualiza o tempo do último frame
        }
    }

    public void renderizar(Graphics g) {
        // Escolhe os frames com base na direção (direita ou esquerda)
        Image[] framesAtuais = direita ? herasDireita : herasEsquerda;

        // Desenha o frame atual na posição correta
        if (framesAtuais != null && framesAtuais.length > 0) {
            g.drawImage(framesAtuais[frameAtual], posicao.x * tamanho, posicao.y * tamanho, tamanho, tamanho, null);
        }
    }

    public int getX() {
        return posicao.x;
    }

    public int getY() {
        return posicao.y;
    }

}
