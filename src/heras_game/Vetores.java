package heras_game;

import java.awt.event.KeyEvent;

public class Vetores {
    public int x, y;

    public Vetores(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Vetores fromKeyEvent(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                return new Vetores(0, -1);
            case KeyEvent.VK_UP:
                return new Vetores(0, -1);
            case KeyEvent.VK_A:
                return new Vetores(-1, 0);
            case KeyEvent.VK_LEFT:
                return new Vetores(-1, 0);
            case KeyEvent.VK_D:
                return new Vetores(1, 0);
            case KeyEvent.VK_RIGHT:
                return new Vetores(1, 0);
            case KeyEvent.VK_S:
                return new Vetores(0, 1);
            case KeyEvent.VK_DOWN:
                return new Vetores(0, 1);
            default:
                return new Vetores(0, 0);
        }
    }

    public Vetores somar(Vetores v) {
        return new Vetores(this.x + v.x, this.y + v.y);
    }

    public Vetores multiplicar(int escala) {
        return new Vetores(this.x * escala, this.y * escala);
    }

    public Vetores dividir(Vetores v) {
        return new Vetores(this.x - v.x, this.y - v.y);
    }

    public boolean iguais(Vetores v) {
        return this.x == v.x && this.y == v.y;
    }

}
