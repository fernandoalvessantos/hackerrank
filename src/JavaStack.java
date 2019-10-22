import java.util.Scanner;
import java.util.Stack;

public class JavaStack {
    public static void main(String []argh)
    {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String input=sc.next();
            Stack<Character> stack = new Stack<>();
            boolean ok = true;
            if(input.isEmpty()){
                System.out.println(true);
            }else {
                for (int i = 0; i < input.length(); i++) {
                    char str = input.charAt(i);
                    if ('{' == str || '(' == str || '[' == str) {
                        stack.push(str);
                    } else {
                        if(stack.isEmpty()){
                            ok = false;
                            break;
                        }
                        if ('}' == str) {
                            if (stack.peek() == '{') {
                                stack.pop();
                            } else {
                                ok = false;
                            }
                        }
                        if (']' == str) {
                            if (stack.peek() == '[') {
                                stack.pop();
                            } else {
                                ok = false;
                            }
                        }
                        if (')' == str) {
                            if (stack.peek() == '(') {
                                stack.pop();
                            } else {
                                ok = false;
                            }
                        }
                    }
                }
                System.out.println(ok && stack.isEmpty());
            }

        }

    }
}
