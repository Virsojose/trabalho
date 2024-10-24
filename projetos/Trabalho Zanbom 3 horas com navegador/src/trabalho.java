import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class trabalho {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Qual a quantidade de pessoas? ");
        int n = sc.nextInt();
        sc.nextLine(); // consumir o \n após o int

        String[] nomes = new String[n];
        double[] nota1 = new double[n];
        double[] nota2 = new double[n];
        double[] medias = new double[n];

        int aprovados = 0;
        double somaMediasAprovados = 0;
        String pessoaMaiorMedia = "";
        double maiorMedia = 0;

        // Leitura dos dados
        for (int i = 0; i < n; i++) {
            System.out.println("Digite os dados da " + (i + 1) + "a pessoa:");
            System.out.print("Nome: ");
            nomes[i] = sc.nextLine();
            System.out.print("Nota etapa 1: ");
            nota1[i] = sc.nextDouble();
            System.out.print("Nota etapa 2: ");
            nota2[i] = sc.nextDouble();
            sc.nextLine(); // consumir o \n após o double

            // Calcula a média
            medias[i] = (nota1[i] + nota2[i]) / 2;

            // Verifica a maior média
            if (medias[i] > maiorMedia) {
                maiorMedia = medias[i];
                pessoaMaiorMedia = nomes[i];
            }

            // Verifica se a pessoa foi aprovada
            if (medias[i] >= 70) {
                aprovados++;
                somaMediasAprovados += medias[i];
            }
        }

        // Criação da página HTML com os resultados
        try {
            // Salva o arquivo HTML no diretório do usuário
            String userDir = System.getProperty("user.home");
            File htmlFile = new File(userDir, "resultado_concurso.html");

            try (FileWriter htmlWriter = new FileWriter(htmlFile)) {
                htmlWriter.write("<html><head><title>Resultados do Concurso</title></head><body>");
                htmlWriter.write("<h1>Resultados do Concurso</h1>");

                // Exibe a tabela
                htmlWriter.write("<table border='1'>");
                htmlWriter.write("<tr><th>Nome</th><th>Nota Etapa 1</th><th>Nota Etapa 2</th><th>Média</th></tr>");
                for (int i = 0; i < n; i++) {
                    htmlWriter.write("<tr><td>" + nomes[i] + "</td><td>" + nota1[i] + "</td><td>" + nota2[i] + "</td><td>" + medias[i] + "</td></tr>");
                }
                htmlWriter.write("</table>");

                // Exibe as pessoas aprovadas
                htmlWriter.write("<h2>Pessoas Aprovadas</h2>");
                if (aprovados > 0) {
                    for (int i = 0; i < n; i++) {
                        if (medias[i] >= 70) {
                            htmlWriter.write("<p>" + nomes[i] + "</p>");
                        }
                    }
                } else {
                    htmlWriter.write("<p>Não há candidatos aprovados.</p>");
                }

                // Exibe a porcentagem de aprovação
                double porcentagemAprovacao = (double) aprovados / n * 100;
                htmlWriter.write("<p>Porcentagem de aprovação: " + String.format("%.1f", porcentagemAprovacao) + "%</p>");

                // Exibe a pessoa com maior média
                htmlWriter.write("<p>Maior média: " + pessoaMaiorMedia + "</p>");

                // Exibe a média dos aprovados ou a mensagem de nenhum aprovado
                if (aprovados > 0) {
                    double mediaAprovados = somaMediasAprovados / aprovados;
                    htmlWriter.write("<p>Nota média dos aprovados: " + String.format("%.2f", mediaAprovados) + "</p>");
                } else {
                    htmlWriter.write("<p>Não há candidatos aprovados.</p>");
                }

                htmlWriter.write("</body></html>");
            }

            // Abre o arquivo HTML no navegador
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                if (htmlFile.exists()) {
                    desktop.browse(htmlFile.toURI());
                }
            }

        } catch (IOException e) {
            System.out.println("Erro ao gerar o arquivo HTML: " + e.getMessage());
        }

        sc.close();
    }
}
