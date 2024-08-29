package heras_game;

import java.awt.Graphics;
import java.awt.Color;

public class Parede {
    int x, y, largura, altura;

    public Parede(int x, int y, int altura, int largura) {
        this.x = x;
        this.y = y;
        this.altura = altura;
        this.largura = largura;
    }

    public void randerizar(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, largura, altura);
    }

}
