import java.util.Scanner;

public class Quicksort {

    public static Integer[] arranjo = null;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Integer qtd = scan.nextInt();
        arranjo = new Integer[qtd];
        for (int i=0;i<arranjo.length;i++){
            arranjo[i]= scan.nextInt();
        }

        for (int i=0;i<arranjo.length;i++){
            if(organiza())
                break;
        }
        imprime(arranjo);
    }

    private static boolean organiza() {
        for (int i=0;i<arranjo.length;i++){
            for (int j=i+1;j<arranjo.length;j++){
                if(arranjo[i] > arranjo[j]) {
                    troca(i, j);
                }
            }
        }
        imprime(arranjo);
        boolean retorno = true;
        for (int i=0;i<arranjo.length;i++) {
            for (int j = i + 1; j < arranjo.length; j++) {
                if(arranjo[i] > arranjo[j]) {
                    retorno = false;
                    break;
                }

            }
            if(!retorno)
                break;
        }
        return retorno;
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
