// Generated from /home/cyc/apps/idea/lab3/src/main/java/CmmParser.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CmmParser}.
 */
public interface CmmParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CmmParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(CmmParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(CmmParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#extDef}.
	 * @param ctx the parse tree
	 */
	void enterExtDef(CmmParser.ExtDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#extDef}.
	 * @param ctx the parse tree
	 */
	void exitExtDef(CmmParser.ExtDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#extDecList}.
	 * @param ctx the parse tree
	 */
	void enterExtDecList(CmmParser.ExtDecListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#extDecList}.
	 * @param ctx the parse tree
	 */
	void exitExtDecList(CmmParser.ExtDecListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#specifier}.
	 * @param ctx the parse tree
	 */
	void enterSpecifier(CmmParser.SpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#specifier}.
	 * @param ctx the parse tree
	 */
	void exitSpecifier(CmmParser.SpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#structSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterStructSpecifier(CmmParser.StructSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#structSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitStructSpecifier(CmmParser.StructSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#optTag}.
	 * @param ctx the parse tree
	 */
	void enterOptTag(CmmParser.OptTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#optTag}.
	 * @param ctx the parse tree
	 */
	void exitOptTag(CmmParser.OptTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#tag}.
	 * @param ctx the parse tree
	 */
	void enterTag(CmmParser.TagContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#tag}.
	 * @param ctx the parse tree
	 */
	void exitTag(CmmParser.TagContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#varDec}.
	 * @param ctx the parse tree
	 */
	void enterVarDec(CmmParser.VarDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#varDec}.
	 * @param ctx the parse tree
	 */
	void exitVarDec(CmmParser.VarDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#funDec}.
	 * @param ctx the parse tree
	 */
	void enterFunDec(CmmParser.FunDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#funDec}.
	 * @param ctx the parse tree
	 */
	void exitFunDec(CmmParser.FunDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#varList}.
	 * @param ctx the parse tree
	 */
	void enterVarList(CmmParser.VarListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#varList}.
	 * @param ctx the parse tree
	 */
	void exitVarList(CmmParser.VarListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#paramDec}.
	 * @param ctx the parse tree
	 */
	void enterParamDec(CmmParser.ParamDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#paramDec}.
	 * @param ctx the parse tree
	 */
	void exitParamDec(CmmParser.ParamDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#compSt}.
	 * @param ctx the parse tree
	 */
	void enterCompSt(CmmParser.CompStContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#compSt}.
	 * @param ctx the parse tree
	 */
	void exitCompSt(CmmParser.CompStContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#stmtList}.
	 * @param ctx the parse tree
	 */
	void enterStmtList(CmmParser.StmtListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#stmtList}.
	 * @param ctx the parse tree
	 */
	void exitStmtList(CmmParser.StmtListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(CmmParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(CmmParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#defList}.
	 * @param ctx the parse tree
	 */
	void enterDefList(CmmParser.DefListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#defList}.
	 * @param ctx the parse tree
	 */
	void exitDefList(CmmParser.DefListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#def}.
	 * @param ctx the parse tree
	 */
	void enterDef(CmmParser.DefContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#def}.
	 * @param ctx the parse tree
	 */
	void exitDef(CmmParser.DefContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#decList}.
	 * @param ctx the parse tree
	 */
	void enterDecList(CmmParser.DecListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#decList}.
	 * @param ctx the parse tree
	 */
	void exitDecList(CmmParser.DecListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#dec}.
	 * @param ctx the parse tree
	 */
	void enterDec(CmmParser.DecContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#dec}.
	 * @param ctx the parse tree
	 */
	void exitDec(CmmParser.DecContext ctx);
	/**
	 * Enter a parse tree produced by the {@code callargs}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterCallargs(CmmParser.CallargsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code callargs}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitCallargs(CmmParser.CallargsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code plusminus}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterPlusminus(CmmParser.PlusminusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code plusminus}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitPlusminus(CmmParser.PlusminusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code or}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterOr(CmmParser.OrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code or}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitOr(CmmParser.OrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignop}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterAssignop(CmmParser.AssignopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignop}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitAssignop(CmmParser.AssignopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notminus}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterNotminus(CmmParser.NotminusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notminus}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitNotminus(CmmParser.NotminusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code float}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterFloat(CmmParser.FloatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code float}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitFloat(CmmParser.FloatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code relop}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterRelop(CmmParser.RelopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code relop}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitRelop(CmmParser.RelopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code int}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterInt(CmmParser.IntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code int}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitInt(CmmParser.IntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code call}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterCall(CmmParser.CallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code call}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitCall(CmmParser.CallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code divstar}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterDivstar(CmmParser.DivstarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code divstar}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitDivstar(CmmParser.DivstarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayOrStruct}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterArrayOrStruct(CmmParser.ArrayOrStructContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayOrStruct}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitArrayOrStruct(CmmParser.ArrayOrStructContext ctx);
	/**
	 * Enter a parse tree produced by the {@code and}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterAnd(CmmParser.AndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code and}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitAnd(CmmParser.AndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ler}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterLer(CmmParser.LerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ler}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitLer(CmmParser.LerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code id}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterId(CmmParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code id}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitId(CmmParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(CmmParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(CmmParser.ArgsContext ctx);
}