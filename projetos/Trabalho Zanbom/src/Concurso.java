
import java.util.Scanner;

public class Concurso {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("qual a quantidade de pessoas? ");
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

        // leitura dos dados
        for (int i = 0; i < n; i++) {
            System.out.println("digite os dados da " + (i + 1) + "a pessoa:");
            System.out.print("Nome: ");
            nomes[i] = sc.nextLine();
            System.out.print("Nota etapa 1: ");
            nota1[i] = sc.nextDouble();
            System.out.print("Nota etapa 2: ");
            nota2[i] = sc.nextDouble();
            sc.nextLine(); // consumir o \n apos o double

            // calcula a média
            medias[i] = (nota1[i] + nota2[i]) / 2;

            // verifica a maior mdia
            if (medias[i] > maiorMedia) {
                maiorMedia = medias[i];
                pessoaMaiorMedia = nomes[i];
            }

            // verifica se a pessoa foi aprovada
            if (medias[i] >= 70) {
                aprovados++;
                somaMediasAprovados += medias[i];
            }
        }

        // exibe a tabela
        System.out.println("\n### TABELA ###");
        for (int i = 0; i < n; i++) {
            System.out.printf("%s, %.1f, %.1f, MEDIA = %.2f\n", nomes[i], nota1[i], nota2[i], medias[i]);
        }

        // pessoas aprovadas
        System.out.println("\n### PESSOAS APROVADAS ###");
        if (aprovados > 0) {
            for (int i = 0; i < n; i++) {
                if (medias[i] >= 70) {
                    System.out.println(nomes[i]);
                }
            }
        }

        //porcentagem de aprovacao
        double porcentagemAprovacao = (double) aprovados / n * 100;
        System.out.printf("\nporcentagem de aprovação: %.1f %%\n", porcentagemAprovacao);

        //pessoa com maior média
        System.out.println("maior media: " + pessoaMaiorMedia);

        //media dos aprovados ou de nenhum aprovado
        if (aprovados > 0) {
            double mediaAprovados = somaMediasAprovados / aprovados;
            System.out.printf("nota media dos aprovados: %.2f\n", mediaAprovados);
        } else {
            System.out.println("nao ha candidatos aprovados.");
        }

        sc.close();
    }
}
