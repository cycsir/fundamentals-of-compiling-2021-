// Generated from /home/cyc/apps/idea/lab3/src/main/java/CmmParser.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CmmParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CmmParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CmmParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(CmmParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#extDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExtDef(CmmParser.ExtDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#extDecList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExtDecList(CmmParser.ExtDecListContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#specifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpecifier(CmmParser.SpecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#structSpecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructSpecifier(CmmParser.StructSpecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#optTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOptTag(CmmParser.OptTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#tag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTag(CmmParser.TagContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#varDec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDec(CmmParser.VarDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#funDec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunDec(CmmParser.FunDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#varList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarList(CmmParser.VarListContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#paramDec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamDec(CmmParser.ParamDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#compSt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompSt(CmmParser.CompStContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#stmtList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtList(CmmParser.StmtListContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(CmmParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#defList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefList(CmmParser.DefListContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDef(CmmParser.DefContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#decList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecList(CmmParser.DecListContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#dec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDec(CmmParser.DecContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callargs}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallargs(CmmParser.CallargsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code plusminus}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlusminus(CmmParser.PlusminusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code or}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr(CmmParser.OrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignop}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignop(CmmParser.AssignopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notminus}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotminus(CmmParser.NotminusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code float}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloat(CmmParser.FloatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code relop}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelop(CmmParser.RelopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code int}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt(CmmParser.IntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code call}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall(CmmParser.CallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code divstar}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivstar(CmmParser.DivstarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayOrStruct}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayOrStruct(CmmParser.ArrayOrStructContext ctx);
	/**
	 * Visit a parse tree produced by the {@code and}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd(CmmParser.AndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ler}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLer(CmmParser.LerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code id}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(CmmParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs(CmmParser.ArgsContext ctx);
}