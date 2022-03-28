import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.*;
import java.util.List;


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

//        while (num < tokenList.size()){
//            cur_token = tokenList.get(num);
//            num++;
//            int line = cur_token.getLine();
//            String text = cur_token.getText();
//            int type = cur_token.getType();
//            String type_str = lexer.getVocabulary().getSymbolicName(type);
//
//
//            switch (type){
//                case CmmLexer.SPACE:
//                case CmmLexer.MUTI_COMMENT:
//                case CmmLexer.INLINE_COMMENT:
//                    continue;
//                case CmmLexer.OCT_INT:
//                    text = Integer.toString(Integer.parseInt(text, 8));
//                    type_str = "INT";
//                    break;
//                case CmmLexer.HEX_INT:
//                    text = Integer.toString(Integer.parseInt(text.substring(2), 16));
//                    type_str = "INT";
//                    break;
//                case CmmLexer.DEC_INT:
//                    type_str = "INT";
//                    break;
//                case CmmLexer.FLOAT:
//                    double d = Double.parseDouble(text);
//                    text = String.format("%.6f", d);
//                    break;
//                default:
//                    break;
//            }
//            String msg = type_str + " " + text + " at Line " + line + ".\n";
//            output.append(msg);
//        }
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
        ParseTreeWalker walker = new ParseTreeWalker();
        CmmWalkListener listener = new CmmWalkListener();
        walker.walk(listener, tree);



    }
}
