package heras_game;

import javax.swing.*;

public class PerguntaMultiplaEscolha extends Perguntas {
    private String[] opcoes;
    private int respostaCorreta; // índice da resposta correta

    public PerguntaMultiplaEscolha(String enunciado, String[] opcoes, int respostaCorreta) {
        super(enunciado);
        this.opcoes = opcoes;
        this.respostaCorreta = respostaCorreta;
    }

    @Override
    public boolean fazerPergunta() {
        StringBuilder opcoesString = new StringBuilder("<html>" + enunciado + "<br>");
        for (int i = 0; i < opcoes.length; i++) {
            opcoesString.append((i + 1)).append(": ").append(opcoes[i]).append("<br>");
        }
        opcoesString.append("</html>");
        
        String resposta = JOptionPane.showInputDialog(null, opcoesString.toString(), "Pergunta", JOptionPane.QUESTION_MESSAGE);
        if (resposta != null) {
            try {
                int respostaEscolhida = Integer.parseInt(resposta) - 1;
                return respostaEscolhida == respostaCorreta;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, insira um número.", "Erro", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean verificarResposta(String resposta) {
        return false; // Não é mais necessário
    }
}
