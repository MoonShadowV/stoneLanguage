package stone.ast;

import stone.Token;
import stone.chap6.Environment;

public class NumberLiteral extends ASTLeaf {
    public NumberLiteral(Token token) {
        super(token);
    }
    public int value(){
        return getToken().getNumber();
    }

    @Override
    public Object eval(Environment env) {
        return value();
    }
}
