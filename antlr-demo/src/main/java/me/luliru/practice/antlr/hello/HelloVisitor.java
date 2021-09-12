// Generated from /Users/luliru/IdeaProjects/mine/practice/antlr-demo/src/main/java/me/luliru/practice/antlr/hello/Hello.g4 by ANTLR 4.9.1
package me.luliru.practice.antlr.hello;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link HelloParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface HelloVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link HelloParser#s}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitS(HelloParser.SContext ctx);
}