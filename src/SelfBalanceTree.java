public class SelfBalanceTree {

    public static void main(String[] args) {


        //int[] valores = {3, 2, 4, 5, 6};
        int[] valores = {14, 25, 21, 10, 23, 7,26, 12, 30, 16, 19};

        Nodo raiz = null;
        for (int i = 0; i < valores.length; i++) {
            raiz = insere(raiz, valores[i]);
        }
        String arvore = imprime(raiz);

        System.out.println(arvore);
    }


    public static Nodo insere(Nodo raiz, int valor) {
        if (raiz == null) {
            raiz = new Nodo();
            raiz.altura = ajustaAltura(raiz);
            raiz.valor = valor;
            return raiz;
        }

        if (valor < raiz.valor) {
            raiz.esquerda = insere(raiz.esquerda, valor);
        } else {
            raiz.direita = insere(raiz.direita, valor);
        }
        raiz.altura = ajustaAltura(raiz);
        raiz.balanco = buscaBalanco(raiz);

        if(raiz.balanco < -1){
            raiz = girarParaEsquerda(raiz);
            //raiz.altura = ajustaAltura(raiz);
        }

        if (raiz.balanco > 1) {
            raiz = girarParaDireita(raiz);
            //raiz.altura = ajustaAltura(raiz);
        }

        return raiz;
    }

    private static int buscaBalanco(Nodo raiz) {
        return buscaAltura(raiz.esquerda) - buscaAltura(raiz.direita);
    }

    private static Nodo girarParaDireita(Nodo raiz) {
        Nodo nodoEsquerdo = raiz.esquerda;
        raiz.esquerda = nodoEsquerdo.direita;
        nodoEsquerdo.direita = raiz;
        raiz.altura = ajustaAltura(raiz);
        nodoEsquerdo.altura = ajustaAltura(nodoEsquerdo);
        return nodoEsquerdo;
    }

    private static Nodo girarParaEsquerda(Nodo raiz) {
        Nodo nodoDireito = raiz.direita;
        raiz.direita = nodoDireito.esquerda;
        nodoDireito.esquerda = raiz;
        raiz.altura = ajustaAltura(raiz);
        nodoDireito.altura = ajustaAltura(nodoDireito);
        return nodoDireito;
    }

    private static int ajustaAltura(Nodo raiz) {
        if (raiz == null) {
            return -1;
        } else {
            if (raiz.valor == 0) {
                return 0;
            } else {
                int direita = buscaAltura(raiz.direita);
                int esquerda = buscaAltura(raiz.esquerda);
                int altura = 1;
                altura += direita >=esquerda ? direita : esquerda;
                return altura;
            }
        }
    }



    private static int buscaAltura(Nodo nodo) {
        if (nodo == null) {
            return -1;
        } else {
            return nodo.altura;
        }
    }

    private static String imprime(Nodo raiz) {
        String arvore = "";
        if (raiz == null)
            return "";
        arvore += "\npai: " + raiz.valor + " " + "altura: " + raiz.altura + " BF: "+ buscaBalanco(raiz)+" ";
        if (raiz.esquerda != null)
            arvore += "esquerda: " + raiz.esquerda.valor + " ";
        if (raiz.direita != null)
            arvore += "direita: " + raiz.direita.valor + " ";
        arvore += imprime(raiz.esquerda);
        arvore += imprime(raiz.direita);
        return arvore + "\n";
    }

    public static class Nodo {
        int valor;
        int altura;
        int balanco;
        Nodo esquerda;
        Nodo direita;
    }
}
