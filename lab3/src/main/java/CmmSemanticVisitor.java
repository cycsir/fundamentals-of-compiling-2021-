
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class CmmSemanticVisitor<T extends Object> extends AbstractParseTreeVisitor<T> implements CmmParserVisitor<T>{
    private Type specifierType; // 全局变量

    private boolean isStruct = false;

    private boolean isFunction = false;

    private Type funcReturnType;

    private int returnLine;

    private HashMap<String, Object> symbolTable = new LinkedHashMap<>();
    private HashMap<String, Object> arrayTable = new LinkedHashMap<>();

    @Override
    public T visitProgram(CmmParser.ProgramContext ctx) {
        return visitNode(ctx);
    }

    @Override
    public T visitExtDef(CmmParser.ExtDefContext ctx) {
       /**
        * extDef: specifier extDecList SEMI  // 变量声明
        *     | specifier SEMI //
        *     | specifier funDec compSt // 函数声明
        *  ;
        *
        * */
       Type type = (Type) ctx.specifier().accept(this);
       Type temp = specifierType;
       specifierType = type;
       if (ctx.extDecList() != null){
           ctx.extDecList().accept(this);
           specifierType = temp;
           return (T) type;
       }
       else if (ctx.funDec()!=null){
           Function function = (Function) ctx.funDec().accept(this);
           specifierType = temp;
           if (function != null){
               funcReturnType = null;
               ctx.compSt().accept(this);
               if (funcReturnType != null && function.returnType.kind != (funcReturnType.kind)){
                   notifyError(8, returnLine, "返回值类型错误 "+funcReturnType+" "+function.returnType);
               }
           }
           return (T) function;
        }
       return null;
    }

    @Override
    public T visitExtDecList(CmmParser.ExtDecListContext ctx) {
        ArrayList<Field> fieldList = new ArrayList<>();
        List<CmmParser.VarDecContext> contexts = ctx.varDec();
        for (int i = 0; i<contexts.size();i++){
            Field field = (Field) contexts.get(i).accept(this);
            if (field != null){
                fieldList.add(field);
            }
        }
        return (T)fieldList;
    }

    @Override
    public T visitSpecifier(CmmParser.SpecifierContext ctx) {
        /**
         * specifier : TYPE
         *     | structSpecifier
         *     ;
         * */
        Type type = new Type();
        if (ctx.TYPE() != null){
            TerminalNode typeNode = ctx.TYPE();
            if (typeNode.getText().equals("int")){
                type.kind = Kind.INT;

            }
            else if (typeNode.getText().equals("float")){
                type.kind = Kind.FLOAT;
            }
            return (T)type;
            // ctx.TYPE().accept(this);
        }
        if(ctx.structSpecifier() != null){
            type = (Type) ctx.structSpecifier().accept(this);
            if (type!=null){
                type.kind = Kind.STRUCTURE;
            }

        }
        return (T) type;
    }

    @Override
    public T visitStructSpecifier(CmmParser.StructSpecifierContext ctx) {
        /**
         * structSpecifier : STRUCT optTag LC defList RC
         *     | STRUCT tag
         *     ;
         * */
        if (ctx.optTag()!=null){
            // 定义结构体
            Structure structure = new Structure();
            String optTag = (String) ctx.optTag().accept(this);
            if (symbolTable.containsKey(optTag)){
                notifyError(16, ctx.getStart().getLine(), "结构体名字与其他名字重复");
                return null;
            }
            else {
                structure.name = optTag;
                isStruct = true;
                ArrayList<Field> fields = (ArrayList<Field>) ctx.defList().accept(this);
                isStruct = false;
                structure.memberList = fields;
                symbolTable.put(optTag, structure);
                return (T) structure;
            }
        }
        else if (ctx.tag()!=null) {
            // 声明结构体变量
            String tag = (String) ctx.tag().accept(this);
            T structure1 = (T) symbolTable.get(tag);
            if (!symbolTable.containsKey(tag) || !(structure1 instanceof Structure)) {
                notifyError(17, ctx.getStart().getLine(), "直接使用未定义过的结构体来定义变量"); //
                return null;
            }
            return (T) structure1;
        }
        return null;
    }

    @Override
    public T visitOptTag(CmmParser.OptTagContext ctx) {
        /**
         * optTag : ID?
         *     ;
         * */
        if (ctx.ID() == null){
            return null;
        }
        else {
            return (T) ctx.ID().getText();
        }
    }

    @Override
    public T visitTag(CmmParser.TagContext ctx) {
        /**
         * tag : ID
         *     ;
         * */
        if (ctx.ID() == null){
            return null;
        }
        else {
            return (T) ctx.ID().getText();
        }
    }

    @Override
    public T visitVarDec(CmmParser.VarDecContext ctx) {
        // varDec : ID (LB (OCT_INT|DEC_INT|HEX_INT) RB)*
        Type type = specifierType;
        String name = ctx.ID(0).getText();
        Field field = new Field();
        List<TerminalNode> nodes = null;
        if (ctx.HEX_INT()!=null){
            nodes = ctx.HEX_INT();
        }else if (ctx.OCT_INT()!=null){
            nodes = ctx.OCT_INT();
        }else if (ctx.DEC_INT()!=null){
            nodes = ctx.DEC_INT();
        }

        Array array = new Array();
        if (ctx.LB(0) != null){
            // 有中括号，是个数组，从最后面开始计算数组的元素类型

            for (TerminalNode node: nodes){
                type = new Array(type, CmmSemanticVisitor.parseInt(node.getText()));
                array = new Array(type, CmmSemanticVisitor.parseInt(node.getText()));
            }

            array.name = name;
            array.baseElementType = specifierType;
            array.baseElementType.kind = specifierType.kind;

            field.name = name;
            field.type = array;
            field.type.kind = Kind.ARRAY;
        }
        else {
            field.name = name;
            field.type = type;
        }
        if (symbolTable.containsKey(field.name)){
            if (isStruct){
                notifyError(15,ctx.start.getLine(),"结构体中域名重复定义");
            }else{
                notifyError(3,ctx.start.getLine(),"变量重复定义");
            }
            return null;
        }else {
            symbolTable.put(field.name, field);
            arrayTable.put(array.name, array);
        }
        return (T) field;
    }

    @Override
    public T visitFunDec(CmmParser.FunDecContext ctx) {
        /**
         * funDec : ID LP varList RP
         *     | ID LP RP
         *     ;
         * */
        TerminalNode node = ctx.ID();
        Function function = new Function();
        if (symbolTable.containsKey(node.getText())){
            notifyError(4,ctx.ID().getSymbol().getLine(),"函数重复定义");
            return null;
        }
        else {
            if (ctx.varList() != null){
                ArrayList<Field> fields = (ArrayList<Field>) ctx.varList().accept(this);
                function.paramList = fields;
            }
            function.name = node.getText();
            function.returnType = specifierType;
            symbolTable.put(node.getText(), function);
        }
        return (T)function;
    }

    @Override
    public T visitVarList(CmmParser.VarListContext ctx) {
        /**
         * varList : paramDec (COMMA paramDec)*
         *     ;
         * */
        List<CmmParser.ParamDecContext> contexts = ctx.paramDec();
        ArrayList<Field> fields = new ArrayList<>();
        for (int i = 0; i<contexts.size(); i++){
            Field field = (Field) contexts.get(i).accept(this);
            if (field != null){
                fields.add(field);
            }
        }
        return (T)fields;
    }

    @Override
    public T visitParamDec(CmmParser.ParamDecContext ctx) {
        /**
         * paramDec : specifier varDec;
         * */
        Type t = specifierType;
        specifierType = (Type) ctx.specifier().accept(this);

        // 将读取的type上传到全局变量，在写下来的声明中使用
        Field field = (Field) ctx.varDec().accept(this);
        specifierType = t;
        return (T)field;
    }

    @Override
    public T visitCompSt(CmmParser.CompStContext ctx) {
        /**
         * compSt: LC defList stmtList RC;
         * */
        ctx.defList().accept(this);
        ctx.stmtList().accept(this);
        return null;
    }

    @Override
    public T visitStmtList(CmmParser.StmtListContext ctx) {
        List<CmmParser.StmtContext> contexts = ctx.stmt();
        for (int i = 0; i<contexts.size(); i++){
            contexts.get(i).accept(this);
        }
        return null;
    }

    @Override
    public T visitStmt(CmmParser.StmtContext ctx) {
        /**
         * stmt : exp SEMI
         *     | compSt
         *     | RETURN exp SEMI
         *     | IF LP exp RP stmt
         *     | IF LP exp RP stmt ELSE stmt
         *     | WHILE LP exp RP stmt
         *     ;
         * */
        if (ctx.compSt()!=null){
            ctx.compSt().accept(this);
        }
        if (ctx.exp()!=null){
            Type type = (Type) ctx.exp().accept(this);
            if (ctx.RETURN()!=null){
                funcReturnType = type;
                returnLine = ctx.getStart().getLine();
            }
        }
        if (ctx.stmt()!=null){
            List<CmmParser.StmtContext> contexts = ctx.stmt();
            for (int i = 0; i<contexts.size(); i++){
                contexts.get(i).accept(this);
            }
        }
        return null;
    }

    @Override
    public T visitDefList(CmmParser.DefListContext ctx) {
        /**
         * defList : def*
         *     ;
         * */
        ArrayList<Field> fields = new ArrayList<>();
        List<CmmParser.DefContext> contexts = ctx.def();
        for (int i = 0; i<contexts.size(); i++){
            ArrayList<Field> tempFields = (ArrayList<Field>) contexts.get(i).accept(this);
            for (Field field:tempFields){
                if (field != null){
                    fields.add(field);
                }
            }
        }
        return (T) fields;
    }

    @Override
    public T visitDef(CmmParser.DefContext ctx) {
        /**
         * def : specifier decList SEMI
         *     ;
         * */
        Type t = specifierType;
        specifierType = (Type) ctx.specifier().accept(this);
        ArrayList<Field> fields = null;
        if (ctx.decList()!=null){
            fields = (ArrayList<Field>) ctx.decList().accept(this);
        }
        specifierType = t;
        return (T) fields;
    }

    @Override
    public T visitDecList(CmmParser.DecListContext ctx) {
        /**
         * decList :dec (COMMA dec)*
         *     ;
         * */
        ArrayList<Field> fields = new ArrayList<>();
        List<CmmParser.DecContext> contexts = ctx.dec();
        for (int i = 0; i<contexts.size(); i++){
            Field field = (Field) contexts.get(i).accept(this);
            if (field != null){
                fields.add(field);
            }
        }
        return (T) fields;
    }

    @Override
    public T visitDec(CmmParser.DecContext ctx) {
        /**
         * dec : varDec ASSIGNOP exp
         *     | varDec
         *     ;
         * */
        Field varDecField = (Field) ctx.varDec().accept(this);
        if (varDecField == null){
            return null;
        }
        if (ctx.ASSIGNOP() == null){

        }
        else {
            if (isStruct){
                notifyError(15,ctx.getStart().getLine(),"在定义时对域进行初始化");
            }
            else {
                Type expType = (Type) ctx.exp().accept(this);
                if (expType != null && expType.kind != varDecField.type.kind){
                    notifyError(5,ctx.getStart().getLine(),"赋值号两边的表达式类型不匹配");
                }
            }
        }
        return (T) varDecField;
    }

// exp

    @Override
    public T visitCallargs(CmmParser.CallargsContext ctx) {
        /**
         *  ID LP args RP #callargs
         * */
        T t = ctx.ID().accept(this);
        Type type = new Type();
        if (t == null){
            notifyError(2,ctx.getStart().getLine(),"函数在调用时未经定义");
            return null;
        }
        else if (!(t instanceof Type)||!(t instanceof Function)){
            notifyError(11,ctx.getStart().getLine(),"对普通变量使用“()”（函数调用）操作符");
            return null;
        }
        else {
            ArrayList<Type> list = (ArrayList<Type>) ctx.args().accept(this);
            if (!((Function)t).isEquals(list)){
                notifyError(9,ctx.getStart().getLine(),"函数调用时实参与形参的数目或类型不匹配");
                return null;
            }
            type = ((Function)t).returnType;
            type.isRightValue = true;
        }
        return (T)type;
    }

    @Override
    public T visitPlusminus(CmmParser.PlusminusContext ctx) {
        /**
         *   exp (PLUS|MINUS) exp #plusminus
         * */
        T exp0 = ctx.exp(0).accept(this);
        if (exp0 == null){
            return null;
        }
        T exp1 = ctx.exp(1).accept(this);
        if (exp1 == null){
            return null;
        }
        if ((((Type)exp0).kind != ((Type)exp1).kind)
        ||  ((Type)exp0).kind != Kind.INT&&((Type)exp0).kind != Kind.FLOAT){
            notifyError(7,ctx.getStart().getLine(),"操作数类型不匹配或操作数类型与操作符不匹配");
            return null;
        }
        Type type = new Type();
        type.kind = ((Type) exp0).kind;
        type.isRightValue = true;
        return (T)type;
    }

    @Override
    public T visitOr(CmmParser.OrContext ctx) {
        /**
         *    exp OR exp  #or
         * */
        T exp0 = ctx.exp(0).accept(this);
        if (exp0 == null){
            return null;
        }
        T exp1 = ctx.exp(1).accept(this);
        if (exp1 == null){
            return null;
        }
        if ((((Type)exp0).kind != ((Type)exp1).kind)
                ||  ((Type)exp0).kind != Kind.INT){
            notifyError(7,ctx.getStart().getLine(),"操作数类型不匹配或操作数类型与操作符不匹配");
            return null;
        }
        Type type = new Type();
        type.kind = ((Type) exp0).kind;
        type.isRightValue = true;
        return (T)type;
    }

    @Override
    public T visitAssignop(CmmParser.AssignopContext ctx) {
        /**
         *   <assoc=right>exp ASSIGNOP exp #assignop
         * */
        T exp0 = ctx.exp(0).accept(this);
        if (exp0 == null){
            return null;
        }
        T exp1 = ctx.exp(1).accept(this);
        if (exp1 == null){
            return null;
        }
        // TODO

        if (exp0 instanceof Type){
            if (((Type) exp0).isRightValue){
                notifyError(6,ctx.getStart().getLine(),"赋值号左边出现一个只有右值的表达式");
                return null;
            }
        }
        else if (exp0 instanceof Field){
            if (((Field) exp0).type.isRightValue){
                notifyError(6,ctx.getStart().getLine(),"赋值号左边出现一个只有右值的表达式");
                return null;
            }
        }

        if (((Type)exp0).kind == Kind.ARRAY){
            // 数组元素赋值
            String arrayName = ((Type)exp0).name;
            Array array = (Array) arrayTable.get(arrayName);
            Kind kind = null;
            if (((Type)exp1).kind == Kind.ARRAY){
                String arrayName1 = ((Type)exp1).name;
                Array array1 = (Array) arrayTable.get(arrayName1);
                if (array1 != null&&array1.baseElementType!=null){
                kind = array1.baseElementType.kind;
                }
            }
            else {
                kind = ((Type)exp1).kind;
            }
            if (array.baseElementType != null && array.baseElementType.kind != kind){
                notifyError(5,ctx.getStart().getLine(),"赋值号两边的表达式类型不匹配");
                return null;
            }
        }
        else if (((Type)exp0).kind != ((Type)exp1).kind){
            notifyError(5,ctx.getStart().getLine(),"赋值号两边的表达式类型不匹配");
            return null;
        }
        // 结构体赋值 structure = structure1

        if (((Type)exp0).kind == Kind.STRUCTURE){
            String structureName = ctx.exp().get(0).getText();
            Field field = (Field)symbolTable.get(structureName);

            String structureName1 = ctx.exp().get(1).getText();
            Field field1 = (Field)symbolTable.get(structureName1);

            if (field == null && field1 != null || field != null && field1 == null){
                notifyError(5,ctx.getStart().getLine(),"赋值号两边的表达式类型不匹配");
                return null;
            }
            if (field != null && field1 != null){
                Structure structure = (Structure) field.type;
                Structure structure1 = (Structure) field1.type;
                if (! structure.isEquals(structure1)){
                    notifyError(5,ctx.getStart().getLine(),"赋值号两边的表达式类型不匹配");
                    return null;
                }
            }
        }
        Type type = (Type) exp0;
        type.isRightValue = false;
        return (T)type;
    }

    @Override
    public T visitNotminus(CmmParser.NotminusContext ctx) {
        /**
         *    <assoc=right>(NOT|MINUS) exp #notminus
         * */
        T exp0 = ctx.exp().accept(this);
        if (exp0 == null){
            return null;
        }
        if (((Type)exp0).kind != Kind.INT){
            notifyError(7,ctx.getStart().getLine(),"操作数类型不匹配或操作数类型与操作符不匹配");
            return null;
        }
        Type type = new Type();
        type.kind = ((Type) exp0).kind;
        type.isRightValue = true;
        return (T)type;
    }

    @Override
    public T visitFloat(CmmParser.FloatContext ctx) {
        Type f = new Type();
        f.isRightValue = true;
        f.kind = Kind.FLOAT;
        return (T)f;
    }

    @Override
    public T visitRelop(CmmParser.RelopContext ctx) {
        /**
         * exp RELOP exp #relop
         * */
        T exp0 = ctx.exp(0).accept(this);
        if (exp0 == null){
            return null;
        }
        T exp1 = ctx.exp(1).accept(this);
        if (exp1 == null){
            return null;
        }
        if ((((Type)exp0).kind == ((Type)exp1).kind) && ((Type)exp0).kind != Kind.INT && ((Type)exp0).kind != Kind.FLOAT){
            notifyError(7,ctx.getStart().getLine(),"操作数类型不匹配或操作数类型与操作符不匹配");
            return null;
        }
        if ((((Type)exp0).kind != ((Type)exp1).kind)){
            notifyError(7,ctx.getStart().getLine(),"操作数类型不匹配或操作数类型与操作符不匹配");
            return null;
        }
        Type type = new Type();
        type.kind = Kind.INT;
        type.isRightValue = true;
        return (T)type;
    }

    @Override
    public T visitInt(CmmParser.IntContext ctx) {
        Type t = new Type();
        t.kind = Kind.INT;
        t.isRightValue = true;
        return (T) t;
    }

    @Override
    public T visitCall(CmmParser.CallContext ctx) {
        /**
         * ID LP RP #call
         * */
        T t = ctx.ID().accept(this);
        Type type = new Type();
        if (t == null){
            notifyError(2,ctx.getStart().getLine(),"函数在调用时未经定义");
            return null;
        }
        else if (!(t instanceof Type)||!(t instanceof Function)){
            notifyError(11,ctx.getStart().getLine(),"对普通变量使用“()”（函数调用）操作符");
            return null;
        }
        else {
            if (!((Function)t).isEquals(null)){
                notifyError(9,ctx.getStart().getLine(),"函数调用时实参与形参的数目或类型不匹配");
                return null;
            }
            type = ((Function)t).returnType;
            type.isRightValue = true;
        }
        return (T)type;
    }

    @Override
    public T visitDivstar(CmmParser.DivstarContext ctx) {
        /**
         *  exp (DIV|STAR) exp #divstar
         * */
        T exp0 = ctx.exp(0).accept(this);
        T exp1 = ctx.exp(1).accept(this);
        if (exp0 == null && exp1 == null){
            return null;
        }
        if (exp0 == null && exp1 != null){
            return (T) exp1;
        }
        if (exp0 != null && exp1 == null){
            return (T) exp0;
        }
//        T exp0 = ctx.exp(0).accept(this);
//        if (exp0 == null){
//            return null;
//        }
//        T exp1 = ctx.exp(1).accept(this);
//        if (exp1 == null){
//            return null;
//        }
        if (((Type)exp0).kind != ((Type)exp1).kind
                ||  ((Type)exp0).kind != Kind.INT){
            notifyError(7,ctx.getStart().getLine(),"操作数类型不匹配或操作数类型与操作符不匹配");
            return null;
        }
        Type type = new Type();
        type.kind = ((Type) exp0).kind;
        type.isRightValue = true;
        return (T)type;
    }

    @Override
    public T visitArrayOrStruct(CmmParser.ArrayOrStructContext ctx) {
        /**
         *  exp ((LB exp RB)|(DOT ID)) #arrayOrStruct
         * */

        if (ctx.DOT()!=null){
            // 结构体
            T exp = ctx.exp(0).accept(this);
            if (exp == null){
                return null;
            }
            T id = ctx.ID().accept(this);
            if (((Type)exp).kind != Kind.STRUCTURE){
                notifyError(13,ctx.getStart().getLine(),"对非结构体型变量使用“.”操作符");
                return null;
            }

            if (id == null || !(id instanceof  Field)){
                notifyError(14,ctx.getStart().getLine(),"访问结构体中未定义过的域"+ ctx.ID().getText());
                return null;
            }

            String structureName = ctx.exp().get(0).getText();
            Field field = (Field)symbolTable.get(structureName);
            if (field != null){
                Structure structure = (Structure) field.type;
                if (structure.getField(ctx.ID().getText()) == null){
                    notifyError(14,ctx.getStart().getLine(),"访问结构体中未定义过的域");
                    return null;
                }
            }
            Type type = ((Field)id).type;
            type.isRightValue = false;
            return (T) type;
        }
        else {
            // 数组
            T exp0 = ctx.exp(0).accept(this);
            if (exp0 == null){
                return null;
            }
            T exp1 = ctx.exp(1).accept(this);
            if (exp1 == null){
                return null;
            }
            if (((Type)exp0).kind != Kind.ARRAY){
                notifyError(10,ctx.getStart().getLine(),"对非数组型变量使用“[…]”（数组访问）操作符");
                return null;
            }
            if (((Type)exp1).kind != Kind.INT){
                notifyError(12,ctx.getStart().getLine(),"数组访问操作符“[…]”中出现非整数");
                return null;
            }

//            String name = ((Type)exp0).name;
//            Array array = (Array) arrayTable.get(name);
            Type type = (Type)exp0;
            type.isRightValue = false;
            return (T)type;
        }
    }

    @Override
    public T visitAnd(CmmParser.AndContext ctx) {
        /**
         *    exp AND exp  #and
         * */
        T exp0 = ctx.exp(0).accept(this);
        if (exp0 == null){
            return null;
        }
        T exp1 = ctx.exp(1).accept(this);
        if (exp1 == null){
            return null;
        }
        if ((((Type)exp0).kind != ((Type)exp1).kind)
                ||  ((Type)exp0).kind != Kind.INT){
            notifyError(7,ctx.getStart().getLine(),"操作数类型不匹配或操作数类型与操作符不匹配");
            return null;
        }
        Type type = new Type();
        type.kind = ((Type) exp0).kind;
        type.isRightValue = true;
        return (T)type;
    }

    @Override
    public T visitLer(CmmParser.LerContext ctx) {
        /**
         *  LP exp RP #ler
         */
        return ctx.exp().accept(this);
    }

    @Override
    public T visitId(CmmParser.IdContext ctx) {
        /**
         * ID #id
         * */

        String name = ctx.getText();
        if (!symbolTable.containsKey(name)){
            notifyError(1,ctx.getStart().getLine(),"变量使用时未定义");
            return null;
        }
        T t = (T) symbolTable.get(name);
        Type type = new Type();
        if (t instanceof Type){
            if (((Type)t).kind == Kind.FUNCTION || t instanceof Function){
                ((Type) t).isRightValue = true;
            }
            else {
                ((Type) t).isRightValue = false;
            }
            type.kind = ((Type) t).kind;
            type.isRightValue = ((Type) t).isRightValue;
        }
        else if(t instanceof Field){
            ((Field) t).type.isRightValue = false;
            type.kind = ((Field) t).type.kind;
            type.isRightValue = ((Field) t).type.isRightValue;
        }
        type.name = name;
        return (T) type;
    }



    @Override
    public T visitArgs(CmmParser.ArgsContext ctx) {
        /**
         * args: exp (COMMA exp)*
         *     ;
         * */
        ArrayList<Type> types = new ArrayList<>();
        List<CmmParser.ExpContext> contexts = ctx.exp();
        for (int i = 0; i<contexts.size(); i++){
            Type t = (Type) contexts.get(i).accept(this);
            types.add(t);
        }
        return (T) types;
    }

    private T visitNode(ParserRuleContext ctx){
        return (T)visitChildren(ctx);
    }

    private void notifyError(int type, int line, String msg){
        System.err.println("Error type " + type + " at Line " + line + ": " + msg);
    }

    @Override
    public T visitTerminal(TerminalNode node) {
        Type t = new Type();
        if(node.getSymbol().getType()==CmmLexer.ID){
            return (T)symbolTable.get(node.getSymbol().getText());
        }
        return (T) t;
    }

    public static int parseInt(String numText){
        if(numText.startsWith("0x")||numText.startsWith("0X")){
            return Integer.parseInt(numText.substring(2),16);
        }else if(numText.startsWith("0")){
            return Integer.parseInt(numText,8);
        }else{
            return Integer.parseInt(numText,10);
        }
    }
}


