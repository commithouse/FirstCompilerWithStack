import java.util.*;

interface Operation {
    int execute(int a, int b);
}

class Add implements Operation {
    public int execute(int a, int b) {
        return a + b;
    }
}

class Sub implements Operation {
    public int execute(int a, int b) {
        return a - b;
    }
}

class ExpressionProcessor {
    private final Map<String, Operation> operations = new HashMap<>();

    private Stack<String> pilha = new Stack<>();

    public ExpressionProcessor() {
        operations.put("add", new Add());
        operations.put("sub", new Sub());
    }

    public int process(String expression) {
        String[] parts = expression.split("[(),]");
        Operation operation = operations.get(parts[0]);
        int a = Integer.parseInt(parts[1]);
        int b = Integer.parseInt(parts[2]);
        return operation.execute(a, b);
    }

    public int processPilha(String expression) {
        String[] parts = expression.split("[(),]");
        int result = -9999;
        for(String o: parts){
            if(o!="") {
                pilha.push(o);
                int pilhacursize = pilha.size();
                if (pilha.size() > 2) {
                    if ( isNumeric(pilha.peek())
                            && isNumeric(pilha.get(pilhacursize-2))
                            && isExp(pilha.get(pilhacursize-3)))
                    {
                        //faz a operacao e tira da pilha
                        int b = Integer.parseInt(pilha.pop());
                        int a = Integer.parseInt(pilha.pop());
                        Operation operation = operations.get(pilha.pop());
                        result = operation.execute(a, b);
                        pilha.push(String.valueOf(result));

                    }
                }
            }
        }

        while(pilha.size()>1){
            if (pilha.size() > 2) {
                int pilhacursize = pilha.size();
                if ( isNumeric(pilha.peek())
                        && isNumeric(pilha.get(pilhacursize-2))
                        && isExp(pilha.get(pilhacursize-3)))
                {
                    //faz a operacao e tira da pilha
                    int b = Integer.parseInt(pilha.pop());
                    int a = Integer.parseInt(pilha.pop());
                    Operation operation = operations.get(pilha.pop());
                    result = operation.execute(a, b);
                    pilha.push(String.valueOf(result));

                }
            }
        }
        return result;

    }

    private static boolean isNumeric(String str){
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isExp(String str){
        if(str.equals("add")||str.equals("sub"))
            return true;
        else return false;
    }
}

public class Main {
    public static void main(String[] args) {
        ExpressionProcessor processor = new ExpressionProcessor();
        System.out.println(processor.processPilha("add(sub(5,2),add(1,3))"));  // Saída: 2
        // System.out.println(processor.processPilha("add(sub(1,2),5)"));  // Saída: 2
    }
}
