package me.luliru.practice.antlr.dsl.test;

import me.luliru.practice.antlr.dsl.DslLexer;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.Token;

/**
 * UsingLexer
 * Created by luliru on 2021/9/5.
 */
public class UsingLexer {

    public static void main(String[] args) {
        String sql= "Select 'abc' as a, `hahah` as c  From a aS table;";
        ANTLRInputStream input = new ANTLRInputStream(sql);  //将输入转成antlr的input流
        DslLexer lexer = new DslLexer(input);  //词法分析
        for (Token token : lexer.getAllTokens()) {
            System.out.printf("%-20s %s\n", DslLexer.VOCABULARY.getSymbolicName(token.getType()), token.getText());
        }
    }
}
