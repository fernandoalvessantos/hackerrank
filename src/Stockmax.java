import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Stockmax {
/*
3
3
5 3 2
3
1 2 100
4
1 3 1 2
 */
/*
0
197
3
 */
    public static long stockmax(List<Integer> prices) {
        long resultado = 0;
        Integer maiorValor = 0;
        Integer ultimoLido = null;
        Integer ultimo = prices.get(prices.size()-1);
        Integer lucro = 0;
        Integer prejuizo = 0;
        for (Integer price : prices) {
            if(ultimoLido == null) {
                ultimoLido = price;
                continue;
            }
            if(maiorValor < price)
                maiorValor = price;

            if(price >= ultimoLido){
                lucro += price - ultimoLido;
            }else{
                prejuizo += ultimoLido - price;
            }
            ultimoLido = price;
        }
        //resultado = lucro - prejuizo;
        resultado = lucro < 0 ? 0 : lucro ;
        return resultado;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


        int t = Integer.parseInt(bufferedReader.readLine().trim());

        for (int tItr = 0; tItr < t; tItr++) {
            int n = Integer.parseInt(bufferedReader.readLine().trim());

            String[] pricesTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            List<Integer> prices = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                int pricesItem = Integer.parseInt(pricesTemp[i]);
                prices.add(pricesItem);
            }

            long result = Stockmax.stockmax(prices);

            System.out.println(String.valueOf(result));

        }
        bufferedReader.close();

    }


}


