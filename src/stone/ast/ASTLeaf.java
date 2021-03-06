package stone.ast;

import stone.StoneException;
import stone.Token;
import stone.chap6.Environment;

import java.util.ArrayList;
import java.util.Iterator;

public class ASTLeaf extends ASTree{
    private static ArrayList<ASTree> empty = new ArrayList<>();
    protected Token token;

    public Token getToken() {
        return token;
    }

    public ASTLeaf(Token token) {

        this.token = token;
    }

    @Override
    public ASTree child(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public int numChildren() {
        return 0;
    }

    @Override
    public Iterator<ASTree> children() {
        return empty.iterator();
    }

    @Override
    public String toString() {
        return token.getText();
    }

    @Override
    public String location() {
        return "at line "+ token.getLineNumber();
    }

    @Override
    public Object eval(Environment env) {
        return new StoneException("cannot eval: "+ toString(),this);
    }
}
