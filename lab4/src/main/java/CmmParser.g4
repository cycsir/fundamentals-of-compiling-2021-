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
    LP exp RP #ler
    | exp ((LB exp RB)|(DOT ID)) #arrayOrStruct
    | <assoc=right>(NOT|MINUS) exp #notminus
    | exp (DIV|STAR) exp #divstar
    | exp (PLUS|MINUS) exp #plusminus
    | exp RELOP exp #relop
    | exp AND exp #and
    | exp OR exp  #or
    | <assoc=right>exp ASSIGNOP exp #assignop
    | ID LP args RP #callargs
    | ID LP RP #call
    | ID #id
    | (OCT_INT|DEC_INT|HEX_INT) #int
    | FLOAT #float
    ;

args: exp (COMMA exp)*
    ;