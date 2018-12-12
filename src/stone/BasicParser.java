package stone;

import java.util.HashSet;

import stone.Parser.Operators;
import static stone.Parser.rule;
import stone.ast.*;

public class BasicParser {
    //保留字
    HashSet<String> reserved = new HashSet<>();
    //操作符
    Operators operators = new Operators();

    Parser expr_0 = rule();
    //primary    : "(" expr ")" | NUMBER | IDENTIFIER | STRING
    Parser primary = rule(PrimaryExpr.class)
            .or(
                    rule().sep("(").ast(expr_0).sep(")"),
                    rule().number(NumberLiteral.class),
                    rule().identifier(Name.class,reserved),
                    rule().string(StringLiteral.class)
            );

    //factor     : "-" primary | primary
    Parser factor = rule().or(
            rule(NegativeExpr.class).sep("-").ast(primary),
            primary
    );

    //expr       : factor { OP factor }
    Parser expr = expr_0.expression(BinaryExpr.class,factor,operators);

    Parser statement_0 = rule();
    //block      : "{" [ statement ] {(";" | EOL) [ statement ]} "}"
    Parser block = rule(BlockStatement.class)
            .sep("{").option(statement_0)
            .repeat(rule().sep(";",Token.EOL).option(statement_0))
            .sep("}");

    //simple     : expr
    Parser simple = rule(PrimaryExpr.class).ast(expr);

    //statement  : "if" expr block [ "else" block ]
    //           | "while" expr block
    //           | simple
    Parser statement = statement_0.or(
            rule(IfStatement.class).sep("if").ast(expr).ast(block)
                    .option(rule().sep("else").ast(block)),
            rule(WhileStatement.class).sep("while").ast(expr).ast(block),
            simple
    );

    //program    : [ statement ] (";" | EOL)
    Parser program = rule()
            .or(statement,rule(NullStatement.class))
            .sep(";",Token.EOL);

    public BasicParser() {
        reserved.add(";");
//        reserved.add("{");
        reserved.add("}");
        reserved.add(Token.EOL);

        operators.add("=",1,Operators.RIGHT);
        operators.add("==", 2, Operators.LEFT);
        operators.add(">", 2, Operators.LEFT);
        operators.add("<", 2, Operators.LEFT);
        operators.add("+", 3, Operators.LEFT);
        operators.add("-", 3, Operators.LEFT);
        operators.add("*", 4, Operators.LEFT);
        operators.add("/", 4, Operators.LEFT);
        operators.add("%", 4, Operators.LEFT);
    }

    public ASTree parse(Lexer lexer) throws ParseException {
        return program.parse(lexer);
    }
}
