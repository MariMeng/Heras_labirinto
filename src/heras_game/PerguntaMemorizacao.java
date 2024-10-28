package heras_game;

import javax.swing.*;

public class PerguntaMemorizacao extends Perguntas {
    private String respostaCorreta;

    public PerguntaMemorizacao(String enunciado, String respostaCorreta) {
        super(enunciado);
        this.respostaCorreta = respostaCorreta;
    }

    @Override
    public boolean fazerPergunta() {
        JOptionPane.showMessageDialog(null, enunciado + "\nVocê tem 15 segundos para memorizar...", "Memorização", JOptionPane.INFORMATION_MESSAGE);
        try {
            Thread.sleep(15000); // Simula os 15 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String resposta = JOptionPane.showInputDialog(null, "Agora, escreva a resposta:", "Pergunta", JOptionPane.QUESTION_MESSAGE);
        return resposta != null && resposta.equalsIgnoreCase(respostaCorreta);
    }

    @Override
    public boolean verificarResposta(String resposta) {
        return false; // Não é mais necessário
    }
}
