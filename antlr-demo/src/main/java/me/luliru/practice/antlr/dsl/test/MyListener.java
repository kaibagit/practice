package me.luliru.practice.antlr.dsl.test;

import me.luliru.practice.antlr.dsl.DslBaseListener;
import me.luliru.practice.antlr.dsl.DslLexer;
import me.luliru.practice.antlr.dsl.DslParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * MyListener
 * Created by luliru on 2021/9/11.
 */
public class MyListener extends DslBaseListener {

    public void enterTableName(DslParser.TableNameContext ctx) {
        System.out.println(ctx.getText());
    }

    public static void main(String[] args) {
        String sql= "Select 'abc' as a, `hahah` as c  From a aS table;";
        ANTLRInputStream input = new ANTLRInputStream(sql);  //将输入转成antlr的input流
        DslLexer lexer = new DslLexer(input);  //词法分析
        CommonTokenStream tokens = new CommonTokenStream(lexer);  //转成token流
        DslParser parser = new DslParser(tokens); // 语法分析
        DslParser.StaContext tree = parser.sta();  //获取某一个规则树，这里获取的是最外层的规则，也可以通过sql()获取sql规则树......
        System.out.println(tree.toStringTree(parser)); //打印规则树


        // 构建通用的，能够触发回调函数的语法分析树遍历器
        ParseTreeWalker walker = new ParseTreeWalker();
        // 遍历语法分析过程中生成的语法分析树，触发回调
        walker.walk(new MyListener(), tree);
    }
}
