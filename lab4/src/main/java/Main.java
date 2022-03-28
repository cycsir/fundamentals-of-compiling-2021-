import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.*;


public class Main
{
    public static boolean isFalse = false;
    public static void main(String[] args) throws IOException {
        StringBuilder output = new StringBuilder();
        // output.append("\n");
        String path = args[0];
        final boolean[] isError = {false};
        // BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));
        CharStream input = CharStreams.fromFileName(path);
        CmmLexer lexer = new CmmLexer(input){
            @Override
            public void notifyListeners(LexerNoViableAltException e) {
                // TODO
                String errorText = _input.getText(Interval.of(_tokenStartCharIndex, _input.index()));
                int line = this._tokenStartLine;
                String msg = "Error type A at Line " + line + ": " + "Mysterious text " + errorText;
                System.err.println(msg);
                isError[0] = true;
            }
        };


        System.err.println(output);
        CommonTokenStream tokens = new CommonTokenStream(lexer);  //将词法分析产生的词法符号放入缓冲区
        CmmParser parser = new CmmParser(tokens);

        parser.removeErrorListeners();
        parser.addErrorListener(CmmErrorListener.INSTANCE);
        // parser.setErrorHandler(new CmmErrorStrategy());
        //
        ParseTree tree = parser.program();
        //
        if(isFalse){
            return;
        }

        CmmSemanticVisitor visitor = new CmmSemanticVisitor();
        visitor.visit(tree);


    }
}
