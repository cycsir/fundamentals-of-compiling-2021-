parser grammar CmmParser;

options {
    tokenVocab=CmmLexer;
}
// High-level Definitions
// 改造前：
// program: extDefList;
// extDefList: extDef extDefList
   // |
   // ;
   //extDef: specifier extDecList SEMI
   // | specifier SEMI
   // | specifier funDec compSt
   // ;

program: extDef* EOF;   // 整个程序
extDef: specifier extDecList SEMI  // 全局变量、结构体、或函数
    | specifier SEMI
    | specifier funDec compSt
 ;
extDecList: varDec (COMMA varDec)*; //


// Specifiers 类型描述符：int float struct
specifier : TYPE
    | structSpecifier
    ;
structSpecifier : STRUCT optTag LC defList RC
    | STRUCT tag
    ;
optTag : ID?
    ;
tag : ID
    ;

// Declarators
varDec : ID (LB (OCT_INT|DEC_INT|HEX_INT|{notifyErrorListeners("Missing closing ')'");}FLOAT|{notifyErrorListeners("Missing closing ')'");}ID) RB)*
//					int line;
//					if (!_localctx.FLOAT().isEmpty()&&!_localctx.ID().isEmpty()){
//					    int line_float0 = _localctx.FLOAT(0).getSymbol().getLine();
//					    int line_id0 =  _localctx.ID(0).getSymbol().getLine();
//					    line = line_float0<line_id0?line_float0:line_id0;
//					}
//					else {
//						if(_la == FLOAT){
//						    line = _localctx.FLOAT(0).getSymbol().getLine();
//					    }
//						else {
//							TerminalNode node = _localctx.ID(1);
//							line = node.getSymbol().getLine();
//						}
//					}
//						String msg = "array size must be an integer constant ";
//						if (line > CmmErrorListener.errorLine) {
//							CmmErrorListener.errorLine = line;
//							System.err.println("Error type B at Line " + line + ": " + msg);
//							Main.isFalse = true;
//						}



    ;
funDec : ID LP varList RP
    | ID LP RP
    ;
varList : paramDec (COMMA paramDec)*
    ;
paramDec : specifier varDec;
// Statements
compSt : LC defList stmtList RC;
stmtList : stmt*
    ;
stmt : exp SEMI
    | compSt
    | RETURN exp SEMI
    | IF LP exp RP stmt
    | IF LP exp RP stmt ELSE stmt
    | WHILE LP exp RP stmt
    ;
// Local Definitions
defList : def*
    ;
def : specifier decList SEMI
    ;
decList :dec (COMMA dec)*
    ;
dec : varDec ASSIGNOP exp
    | varDec
    ;
// Expressions
exp:
    LP exp RP
    | exp ((LB exp RB)|(DOT ID))
    | <assoc=right>(NOT|MINUS) exp
    | exp (DIV|STAR) exp
    | exp (PLUS|MINUS) exp
    | exp RELOP exp
    | exp AND exp
    | exp OR exp
    | <assoc=right>exp ASSIGNOP exp
    | ID LP args RP
    | ID LP RP
    | ID
    | (OCT_INT|DEC_INT|HEX_INT)
    | FLOAT
    ;

args: exp (COMMA exp)*
    ;