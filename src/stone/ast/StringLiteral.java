package stone.ast;

import stone.Token;
import stone.chap6.Environment;

public class StringLiteral extends ASTLeaf {
    public StringLiteral(Token token) {
        super(token);
    }
    public String value(){
        return getToken().getText();
    }
    @Override
    public Object eval(Environment env) {
        return value();
    }
}
