import java.util.Scanner;

public class Quicksort32 {

    public static Integer[] arranjo = null;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Integer qtd = scan.nextInt();
        arranjo = new Integer[qtd];
        for (int i=0;i<arranjo.length;i++){
            arranjo[i]= scan.nextInt();
        }

        int indicePivo =  qtd/2;
        organiza(0, indicePivo);
        organiza(indicePivo, arranjo.length-1);
        imprime(arranjo);
    }

    private static void organiza(int menor, int maior) {
        int valor = arranjo[maior];
        for(int i=menor; i<maior;i++){
            if(arranjo[i] > valor){
                troca(i, maior);
            }
        }
    }


    private static void troca(int i, int j) {
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
