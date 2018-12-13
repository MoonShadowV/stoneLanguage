package stone.ast;

import stone.chap6.Environment;

import java.util.List;

public class IfStatement extends ASTList{
    public IfStatement(List<ASTree> children) {
        super(children);
    }
    public ASTree condition(){
        return child(0);
    }
    public ASTree thenBlock(){
        return child(1);
    }
    public ASTree elseBlock(){
        return numChildren() > 2 ? child(2) : null;
    }

    @Override
    public String toString() {
        return "(if "+condition()+" "+thenBlock()+" else "+elseBlock()+")";
    }

    @Override
    public Object eval(Environment env) {
        Object condition = condition().eval(env);
        if(condition instanceof Integer && ((Integer) condition).intValue()!= FALSE){
            return thenBlock().eval(env);
        }else {
            ASTree b = elseBlock();
            if(b == null){
                return 0;
            }else {
                return b.eval(env);
            }
        }

    }
}
