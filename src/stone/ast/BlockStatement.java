package stone.ast;

import stone.chap6.Environment;

import java.util.ArrayList;
import java.util.List;

public class BlockStatement extends ASTList {
    public BlockStatement(List<ASTree> children) {
        super(children);
    }

    @Override
    public Object eval(Environment env) {
        Object result = 0;
        for (ASTree t:this) {
            if(!(t instanceof NullStatement)){
                result = t.eval(env);
            }
        }
        return result;
    }
}
