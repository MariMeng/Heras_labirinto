package heras_game;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

public class SistemaPerguntas {
    private ArrayList<Perguntas> perguntas;
    private boolean perguntaAtiva = false;
    public SistemaPerguntas() {
        perguntas = new ArrayList<>();

        
        // Adiciona perguntas de múltipla escolha
        perguntas.add(new PerguntaMultiplaEscolha("Qual a fórmula de Bhaskara?",
                new String[]{"x = -b ± √(b² - 4ac) / 2a", "x = b² - 4ac", "x = (b² + 4ac) / 2a", "x = -b / 2a"}, 0));
        
        perguntas.add(new PerguntaMultiplaEscolha("Qual das opções não pode ser considerada uma transformação física?",
                new String[]{"A mudança de estado da água de líquido para vapor.\r\n",
                		"A mistura de areia e sal.\r\n","A oxidação do ferro (ferrugem).\r\n",
                		"A dissolução do açúcar em água."}, 2));
        perguntas.add(new PerguntaMultiplaEscolha("Qual é o valor de x na equação 3x + 6 = 15?",
                new String[]{"x = 3", "x = 4", "x = 5", "x = 6"}, 0));

        // Pergunta sobre literatura
        perguntas.add(new PerguntaMultiplaEscolha("Qual obra é considerada um marco do romantismo brasileiro?",
                new String[]{"Dom Casmurro", "O Guarani", "Memórias Póstumas de Brás Cubas", "Iracema"}, 0));

        // Pergunta sobre química
        perguntas.add(new PerguntaMultiplaEscolha("Qual é a fórmula química da água?",
                new String[]{"H2O", "O2", "CO2", "NaCl"}, 0));
        perguntas.add(new PerguntaMultiplaEscolha("Qual é a unidade de medida de força no Sistema Internacional?",
                new String[]{"Joule", "Newton", "Pascal", "Watt"}, 1));

        perguntas.add(new PerguntaMultiplaEscolha("Qual organela é responsável pela produção de energia nas células?",
                new String[]{"Cloroplasto", "Mitocôndria", "Ribossomo", "Lisossomo"}, 1));
        // Adiciona perguntas de memorização
     //   perguntas.add(new PerguntaMemorizacao("Como calcular a área de um paralelepípedo? Memorize a fórmula: A = 2(ab + ac + bc)",
    //            "A = 2(ab + ac + bc)"));
    }

    public boolean fazerPergunta() {
        if (!perguntaAtiva) { // Verifica se não há uma pergunta ativa
            Random random = new Random();
            Perguntas pergunta = perguntas.get(random.nextInt(perguntas.size()));
            perguntaAtiva = true; // Marca a pergunta como ativa

            boolean respostaCorreta = pergunta.fazerPergunta(); // Executa a pergunta

            if (respostaCorreta) {
                JOptionPane.showMessageDialog(null, "Resposta correta!", "Resultado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Game Over! Resposta incorreta.", "Resultado", JOptionPane.ERROR_MESSAGE);
            }

            perguntaAtiva = false; // Reinicia a marcação após responder
            return respostaCorreta; // Retorna o resultado da resposta
        } else {
            // Se uma pergunta já está ativa, informe o usuário
            JOptionPane.showMessageDialog(null, "Uma pergunta já está ativa. Responda antes de continuar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false; // Retorna false se uma pergunta já estiver ativa
        }
    }



}
