import java.util.Scanner;

public class Quicksort3 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Integer qtd = scan.nextInt();
        Integer[] arranjo = new Integer[qtd];
        for (int i=0;i<arranjo.length;i++){
            arranjo[i]= scan.nextInt();
        }
        quicksort(arranjo, 0, arranjo.length-1);
    }

    public static void quicksort(Integer[] arranjo, int menorIndice, int maiorIndice){
        if( menorIndice < maiorIndice){
         int p = partition(arranjo, menorIndice, maiorIndice);
            quicksort(arranjo, menorIndice, p-1);
            quicksort(arranjo, p+1, maiorIndice);
        }

    }

    public static int partition(Integer[] arranjo, int menorPartition, int maiorPartition){
        int valorPivot = arranjo[maiorPartition];
        int i = menorPartition;
        for(int index = menorPartition;index <= maiorPartition;index++){
            if (arranjo[index] < valorPivot ) {
                troca(arranjo, i,index);
                i = i +1;
            }

        }
        troca(arranjo, i, maiorPartition);
        imprime(arranjo);
        return i;
    }

    private static void troca(Integer[] arranjo, int i, int j) {
        int aux = arranjo[i];
        arranjo[i] = arranjo[j];
        arranjo[j] = aux;
    }

    public static void imprime(Integer[] arranjo){
        String saida  = "";
        for (int i= 0; i<arranjo.length;i++){
            saida = saida + arranjo[i] + " ";
        }
        System.out.println(saida);
    }
}
