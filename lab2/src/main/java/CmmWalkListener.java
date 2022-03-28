import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.regex.Pattern;

public class CmmWalkListener implements CmmParserListener{
    public static int tabs = 0;

    public void addTabs(){
        tabs++;
    }
    public void deTabs(){
        if (tabs > 0){
            tabs--;
        }
    }
    @Override
    public void enterProgram(CmmParser.ProgramContext ctx) {

    }

    @Override
    public void exitProgram(CmmParser.ProgramContext ctx) {

    }

    @Override
    public void enterExtDef(CmmParser.ExtDefContext ctx) {

    }

    @Override
    public void exitExtDef(CmmParser.ExtDefContext ctx) {

    }

    @Override
    public void enterExtDecList(CmmParser.ExtDecListContext ctx) {

    }

    @Override
    public void exitExtDecList(CmmParser.ExtDecListContext ctx) {

    }

    @Override
    public void enterSpecifier(CmmParser.SpecifierContext ctx) {

    }

    @Override
    public void exitSpecifier(CmmParser.SpecifierContext ctx) {

    }

    @Override
    public void enterStructSpecifier(CmmParser.StructSpecifierContext ctx) {

    }

    @Override
    public void exitStructSpecifier(CmmParser.StructSpecifierContext ctx) {

    }

    @Override
    public void enterOptTag(CmmParser.OptTagContext ctx) {

    }

    @Override
    public void exitOptTag(CmmParser.OptTagContext ctx) {

    }

    @Override
    public void enterTag(CmmParser.TagContext ctx) {

    }

    @Override
    public void exitTag(CmmParser.TagContext ctx) {

    }

    @Override
    public void enterVarDec(CmmParser.VarDecContext ctx) {

    }

    @Override
    public void exitVarDec(CmmParser.VarDecContext ctx) {

    }

    @Override
    public void enterFunDec(CmmParser.FunDecContext ctx) {

    }

    @Override
    public void exitFunDec(CmmParser.FunDecContext ctx) {

    }

    @Override
    public void enterVarList(CmmParser.VarListContext ctx) {

    }

    @Override
    public void exitVarList(CmmParser.VarListContext ctx) {

    }

    @Override
    public void enterParamDec(CmmParser.ParamDecContext ctx) {

    }

    @Override
    public void exitParamDec(CmmParser.ParamDecContext ctx) {

    }

    @Override
    public void enterCompSt(CmmParser.CompStContext ctx) {

    }

    @Override
    public void exitCompSt(CmmParser.CompStContext ctx) {

    }

    @Override
    public void enterStmtList(CmmParser.StmtListContext ctx) {

    }

    @Override
    public void exitStmtList(CmmParser.StmtListContext ctx) {

    }

    @Override
    public void enterStmt(CmmParser.StmtContext ctx) {

    }

    @Override
    public void exitStmt(CmmParser.StmtContext ctx) {

    }

    @Override
    public void enterDefList(CmmParser.DefListContext ctx) {

    }

    @Override
    public void exitDefList(CmmParser.DefListContext ctx) {

    }

    @Override
    public void enterDef(CmmParser.DefContext ctx) {

    }

    @Override
    public void exitDef(CmmParser.DefContext ctx) {

    }

    @Override
    public void enterDecList(CmmParser.DecListContext ctx) {

    }

    @Override
    public void exitDecList(CmmParser.DecListContext ctx) {

    }

    @Override
    public void enterDec(CmmParser.DecContext ctx) {

    }

    @Override
    public void exitDec(CmmParser.DecContext ctx) {

    }

    @Override
    public void enterExp(CmmParser.ExpContext ctx) {

    }

    @Override
    public void exitExp(CmmParser.ExpContext ctx) {

    }

    @Override
    public void enterArgs(CmmParser.ArgsContext ctx) {

    }

    @Override
    public void exitArgs(CmmParser.ArgsContext ctx) {

    }

    @Override
    public void visitTerminal(TerminalNode terminalNode) {
        String output = "";
        StringBuilder tab_str = new StringBuilder();
        for (int i = 0; i < tabs; i++){
            tab_str.append("  ");
        }
        int type = terminalNode.getSymbol().getType();
        String type_str = CmmParser.VOCABULARY.getSymbolicName(type);
        String text = terminalNode.getText();

        switch (type) {
            case CmmLexer.ID:
            case CmmLexer.TYPE:
                break;
            case CmmLexer.OCT_INT:
                text = Integer.toString(Integer.parseInt(text, 8));
                type_str = "INT";
                break;
            case CmmLexer.HEX_INT:
                text = Integer.toString(Integer.parseInt(text.substring(2), 16));
                type_str = "INT";
                break;
            case CmmLexer.DEC_INT:
                type_str = "INT";
                break;
            case CmmLexer.FLOAT:
                double d = Double.parseDouble(text);
                text = String.format("%.6f", d);
                break;
            case Recognizer.EOF:
                return;
            default:
                text = "";
                break;
        }
        if (text.equals("")){
            output = tab_str+type_str;
        }
        else {
            output = tab_str+type_str+ ": "+text;
        }
        System.err.println(output);

        //TODO
    }

    @Override
    public void visitErrorNode(ErrorNode errorNode) {
            //TODO
    }

    @Override
    public void enterEveryRule(ParserRuleContext parserRuleContext) {
            //TODO
        String output = "";
        String regex = "\\varepsilon";
        StringBuilder tab_str = new StringBuilder();
        for (int i = 0; i < tabs; i++){
            tab_str.append("  ");
        }
        int type = parserRuleContext.getRuleIndex();
        int line = parserRuleContext.getStart().getLine();
        String type_str = CmmParser.ruleNames[type];
        type_str = type_str.substring(0, 1).toUpperCase()+type_str.substring(1);
        output += tab_str + type_str + " ("+ line +")";
        if (parserRuleContext.getText().length() != 0){
            System.err.println(output);
        }
        addTabs();
    }

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) {
            //TODO
        deTabs();
    }
}
