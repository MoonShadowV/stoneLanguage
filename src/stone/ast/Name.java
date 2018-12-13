package stone.ast;

import stone.StoneException;
import stone.Token;
import stone.chap6.Environment;

public class Name extends ASTLeaf {
    public Name(Token token) {
        super(token);
    }
    public String name(){
        return getToken().getText();
    }

    @Override
    public Object eval(Environment env) {
        Object value = env.get(name());
        if(value == null){
            throw new StoneException("undefined name: "+name(),this);
        }else {
            return value;
        }
    }
}
