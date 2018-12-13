package stone.ast;

import stone.chap6.Environment;

import java.util.List;

public class WhileStatement extends ASTList {
    public WhileStatement(List<ASTree> children) {
        super(children);
    }
    public ASTree condition(){
        return child(0);
    }
    public ASTree body(){
        return child(1);
    }

    @Override
    public String toString() {
        return "(while"+condition()+" "+body()+")";
    }

    @Override
    public Object eval(Environment env) {
        Object result = 0;
        for(;;){
            Object condition = condition().eval(env);
            if(condition instanceof Integer && ((Integer) condition).intValue() == FALSE){
                return result;
            }else {
                result = body().eval(env);
            }
        }
    }
}
