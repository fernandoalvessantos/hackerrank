public class SelfBalanceTreeHackerRank {

    public static void main(String[] args) {


        //int[] valores = {3, 2, 4, 5, 6};
        int[] valores = {14, 25, 21, 10, 23, 7,26, 12, 30, 16, 19};

        Node raiz = null;
        for (int i = 0; i < valores.length; i++) {
            raiz = insert(raiz, valores[i]);
        }
        String arvore = imprime(raiz);

        System.out.println(arvore);
    }


    public static Node insert(Node raiz, int valor) {
        if (raiz == null) {
            raiz = new Node();
            raiz.ht = ajustaAltura(raiz);
            raiz.val = valor;
            return raiz;
        }

        if (valor < raiz.val) {
            raiz.left = insert(raiz.left, valor);
        } else {
            raiz.right = insert(raiz.right, valor);
        }
        raiz.ht = ajustaAltura(raiz);
        int balanco = buscaBalanco(raiz);

        if(balanco < -1){
            raiz = girarParaEsquerda(raiz);
            //raiz.altura = ajustaAltura(raiz);
        }

        if (balanco > 1) {
            raiz = girarParaDireita(raiz);
            //raiz.altura = ajustaAltura(raiz);
        }

        return raiz;
    }

    private static int buscaBalanco(Node raiz) {
        return buscaAltura(raiz.left) - buscaAltura(raiz.right);
    }

    private static Node girarParaDireita(Node raiz) {
        Node nodeEsquerdo = raiz.left;
        raiz.left = nodeEsquerdo.right;
        nodeEsquerdo.right = raiz;
        raiz.ht = ajustaAltura(raiz);
        nodeEsquerdo.ht = ajustaAltura(nodeEsquerdo);
        return nodeEsquerdo;
    }

    private static Node girarParaEsquerda(Node raiz) {
        Node nodeDireito = raiz.right;
        raiz.right = nodeDireito.left;
        nodeDireito.left = raiz;
        raiz.ht = ajustaAltura(raiz);
        nodeDireito.ht = ajustaAltura(nodeDireito);
        return nodeDireito;
    }

    private static int ajustaAltura(Node raiz) {
        if (raiz == null) {
            return -1;
        } else {
            if (raiz.val == 0) {
                return 0;
            } else {
                int direita = buscaAltura(raiz.right);
                int esquerda = buscaAltura(raiz.left);
                int altura = 1;
                altura += direita >=esquerda ? direita : esquerda;
                return altura;
            }
        }
    }



    private static int buscaAltura(Node node) {
        if (node == null) {
            return -1;
        } else {
            return node.ht;
        }
    }

    public static class Node {
        int val;
        int ht;
        Node left;
        Node right;
    }

    private static String imprime(Node raiz) {
        String arvore = "";
        if (raiz == null)
            return "";
        arvore += "\npai: " + raiz.val + " " + "altura: " + raiz.ht + " BF: "+ buscaBalanco(raiz)+" ";
        if (raiz.left != null)
            arvore += "esquerda: " + raiz.left.val + " ";
        if (raiz.right != null)
            arvore += "direita: " + raiz.right.val + " ";
        arvore += imprime(raiz.left);
        arvore += imprime(raiz.right);
        return arvore + "\n";
    }
}
