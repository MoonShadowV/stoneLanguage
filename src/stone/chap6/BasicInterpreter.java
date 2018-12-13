package stone.chap6;

import stone.*;
import stone.ast.ASTree;
import stone.ast.NullStatement;

public class BasicInterpreter {
    public static void main(String[] args) throws ParseException{
        run(new BasicParser(),new BasicEnv());
    }

    public static void run(BasicParser basicParser,Environment env)
        throws ParseException
    {
        Lexer lexer = new Lexer(new CodeDialog());
        while (lexer.peek(0) != Token.EOF){
            ASTree t = basicParser.parse(lexer);
            if(!(t instanceof NullStatement)){
                Object r = t.eval(env);
                System.out.println("=> "+ r);
            }
        }
    }
}
