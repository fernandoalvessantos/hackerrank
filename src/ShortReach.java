import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ShortReach {
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


    static int[] shortestReach(int numberNodes, int[][] edges, int startNode) {
        ArrayList<Nodo> nodos = new ArrayList<>();
        for(int i = 1; i<=numberNodes;i++){
            nodos.add(new Nodo(i));
        }
        for (int way = 0; way<edges.length;way++){
            Nodo n1 = getNodo(edges[way][0], nodos);
            n1.addRota(edges[way][1], edges[way][2]);
            Nodo n2 = getNodo(edges[way][1], nodos);
            n2.addRota(edges[way][0], edges[way][2]);
        }

        Nodo start = getNodo(startNode, nodos);

        int[] retorno = new int[numberNodes -1];
        int i=0;
        for(Nodo nodo: nodos){
            if(nodo.valor == startNode)
                continue;
            int custoViagem = getCustoViagem(start, nodo.valor, nodos, new ArrayList<int[]>());
            retorno[i] = custoViagem;
            i++;
        }

        return retorno;
    }

    static Integer getCustoViagem(Nodo start, int target, ArrayList<Nodo> nodos, ArrayList<int[]> jaViajados) {
        if (jaContemRota(start.valor, target, jaViajados))
            return null;

        Integer custoTemp = null;
        if (start.destinos.containsKey(target)) {
            adicionaVisitaRota(start.valor, target, jaViajados);
            custoTemp = start.destinos.get(target);
        }
        Integer soma = null;
        for (Map.Entry<Integer, Integer> destino : start.destinos.entrySet()) {
            if (jaContemRota(destino.getKey(), target, jaViajados))
                continue;
            Integer custo = getCustoViagem(getNodo(destino.getKey(), nodos), target, nodos, jaViajados);
            adicionaVisitaRota(destino.getKey(), target, jaViajados);
            if (custo != null) {
                if (soma == null)
                    soma = 0;
                soma = soma + custo;
            }
        }
        if(custoTemp == null && soma == null) {
            return null;
        }else{
            if(custoTemp != null && soma == null)
                return custoTemp;
            if(soma != null && custoTemp == null)
                return soma;
        }
        return custoTemp > soma ? soma : custoTemp;

    }

    private static void adicionaVisitaRota(int valor, int target, ArrayList<int[]> jaViajados) {
        int[] rota = new int[2];
        rota[0] = valor;
        rota[1] = target;
        jaViajados.add(rota);
    }

    private static boolean jaContemRota(int valor, int target, ArrayList<int[]> jaViajados) {
        for (int[] rota : jaViajados) {
            if(rota[0] == valor && rota[1] == target)
                return true;
        }
        return false;
    }

    static Nodo getNodo(int valor, ArrayList<Nodo> nodos){
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
