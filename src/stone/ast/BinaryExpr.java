package stone.ast;

import stone.StoneException;
import stone.chap6.Environment;

import java.util.List;

public class BinaryExpr extends ASTList {
    public BinaryExpr(List<ASTree> children) {
        super(children);
    }
    public ASTree left(){
        return child(0);
    }

    public ASTree right(){
        return child(2);
    }

    public String operator(){
        return ((ASTLeaf)child(1)).getToken().getText();
    }

    @Override
    public Object eval(Environment env) {
        String operator = operator();
        if("=".equals(operator)){
            Object right = right().eval(env);
            return computeAssign(env,right);
        }else{
            Object left = left().eval(env);
            Object right = right().eval(env);
            return computeOp(left,operator,right);
        }
    }

    //var assignment
    protected Object computeAssign(Environment env,Object rightValue){
        ASTree left = left();
        if(left instanceof Name){
            env.put(((Name)left).name(),rightValue);
            return rightValue;
        }else {
            throw new StoneException("bad assignment ",this);
        }
    }

    protected Object computeOp(Object left,String operator,Object right){
        if(left instanceof Integer && right instanceof Integer){
            return computeNumber((Integer) left,operator,(Integer) right);
        }else if(operator.equals("+")){
            return String.valueOf(left) + String.valueOf(right);
        }else if(operator.equals("==")){
            if(left == null){
                return right == null ? TRUE : FALSE;
            }else{
                return left.equals(right) ? TRUE : FALSE;
            }
        }else {
            throw new StoneException("bad type ",this);
        }
    }

    protected int computeNumber(Integer left,String operator,Integer right){
//        int l = left.intValue();
//        int r = right.intValue();
        if(operator.equals("+")){
            return left + right;
        }
        else if(operator.equals("-")){
            return left - right;
        }
        else if(operator.equals("*")){
            return left * right;
        }
        else if(operator.equals("/")){
            return left / right;
        }
        else if(operator.equals("%")){
            return  left % right;
        }
        else if(operator.equals("==")){
            return left.equals(right) ? TRUE : FALSE;
        }
        else if(operator.equals(">")){
            return left > right ? TRUE : FALSE;
        }
        else if(operator.equals("<")){
            return left < right ? TRUE : FALSE;
        }
        else {
            throw new StoneException("bad operator ", this);
        }
    }
}
