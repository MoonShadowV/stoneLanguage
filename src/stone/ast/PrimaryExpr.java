package stone.ast;

import java.util.List;

//  非终结符 primary（基本构成元素）
//  用于表示括号括起的表达式、整型字面量、标识符（即变量名）或字符串字面量。
//  primary    : "(" expr ")" | NUMBER | IDENTIFIER | STRING
public class PrimaryExpr extends ASTList{
    public PrimaryExpr(List<ASTree> children) {
        super(children);
    }
    public static ASTree create(List<ASTree> children){
        return children.size() == 1 ? children.get(0) : new PrimaryExpr(children);
    }
}
