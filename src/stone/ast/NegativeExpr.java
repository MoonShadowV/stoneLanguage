package stone.ast;

import stone.StoneException;
import stone.chap6.Environment;

import java.util.List;

public class NegativeExpr extends ASTList {
    public NegativeExpr(List<ASTree> children) {
        super(children);
    }
    public ASTree operand(){
        return child(0);
    }

    @Override
    public Object eval(Environment env) {
        Object value = operand().eval(env);
        if(value instanceof Integer){
            return new Integer(-(((Integer)value).intValue()));
        }else{
            throw new StoneException("bad type for - ",this);
        }
    }

    @Override
    public String toString() {
        return "-" + operand();
    }
}
