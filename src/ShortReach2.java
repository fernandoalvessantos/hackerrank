import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ShortReach2 {
    // Complete the shortestReach function below.

    /**
     * 1
     * 4 4
     * 1 2 24
     * 1 4 20
     * 3 1 3
     * 4 3 12
     * 1
     */
    public static ArrayList<Nodo> nodos = new ArrayList<>();
    public static ArrayList<Integer> visitados = new ArrayList<>();
    public static Nodo destino = null;
    public static Nodo start = null;
    public static ArrayList<Integer> custos = new ArrayList<>();

    static int[] shortestReach(int numberNodes, int[][] edges, int startNode) {
        for(int i = 1; i<=numberNodes;i++){
            nodos.add(new Nodo(i));
        }
        for (int way = 0; way<edges.length;way++){
            Nodo n1 = getNodo(edges[way][0]);
            n1.addRota(edges[way][1], edges[way][2]);
            Nodo n2 = getNodo(edges[way][1]);
            n2.addRota(edges[way][0], edges[way][2]);
        }

        start = getNodo(startNode);

        int[] retorno = new int[numberNodes -1];
        int i=0;
        for(Nodo nodo: nodos){
            if(nodo.valor == startNode)
                continue;
            destino  = nodo;
            custos.clear();
            visitados.clear();
            int custoViagem = 0;
            custoViagem = buscaCustoViagem(start);
            if(custoViagem == 0)
                custoViagem = -1;
            retorno[i] = custoViagem;
            i++;
        }

        return retorno;
    }

    private static int buscaCustoViagem(Nodo nodo) {
        visitados.add(nodo.valor);
        int custoSoma = 0;
        int custo = 0;
        for (Integer chave : nodo.destinos.keySet()){
            if(destino.valor == chave){
                custo = nodo.destinos.get(chave);
            }else {
                if(!visitados.contains(chave)) {
                    custos.add(nodo.destinos.get(chave));
                    buscaCustoViagem(getNodo(chave));
                }
            }
        }
        custoSoma = somaCusto(custos);
        if(custo == 0 && custoSoma ==0)
            return 0;
        if(custo == 0 )
            return custoSoma;
        if(custoSoma == 0)
            return custo;
        return custo > custoSoma ? custoSoma : custo;
    }

    private static int somaCusto(ArrayList<Integer> custos) {
        int soma = 0;
        for ( Integer i: custos
             ) {
            soma += i;
        }
        return soma;
    }


    public static Nodo getNodo(int valor){
        for (Nodo n: nodos) {
            if(n.valor == valor){
                return n;
            }
        }
        return null;
    }

    public static class Nodo{
        int valor;
        Map<Integer, Integer> destinos = new HashMap<>();

        Nodo(int valor){
            this.valor = valor;
        }

        void addRota(int destino, int custo){
            destinos.put(destino, custo);
        }

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output"));

        int qtdTestes = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < qtdTestes; tItr++) {
            String[] nm = scanner.nextLine().split(" ");

            int numberNodes = Integer.parseInt(nm[0]);

            int numberEdges = Integer.parseInt(nm[1]);

            int[][] edges = new int[numberEdges][3];

            for (int i = 0; i < numberEdges; i++) {
                String[] edgesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 3; j++) {
                    int edgesItem = Integer.parseInt(edgesRowItems[j]);
                    edges[i][j] = edgesItem;
                }
            }

            int startNode = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] result = shortestReach(numberNodes, edges, startNode);

            for (int i = 0; i < result.length; i++) {
                bufferedWriter.write(String.valueOf(result[i]));

                if (i != result.length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
