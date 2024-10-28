package heras_game;

public abstract class Perguntas {
    protected String enunciado;

    public Perguntas(String enunciado) {
        this.enunciado = enunciado;
    }

    public abstract boolean verificarResposta(String resposta);
    public abstract boolean fazerPergunta(); // Novo método
}
