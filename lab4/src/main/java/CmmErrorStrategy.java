import org.antlr.v4.runtime.*;

public class CmmErrorStrategy extends DefaultErrorStrategy {
    /** Instead of recovering from exception e, rethrow it wrapped
     * in a generic RuntimeException so it is not caught by the
     * rule function catches.
     * RuntimeException.
     Exception e is the "cause" of the
     */
    @Override
    public void recover(Parser recognizer, RecognitionException e) {
        // throw new RuntimeException(e);
    }
    /** Make sure we don't attempt to recover inline; if the parser
     *
     successfully recovers, it won't throw an exception.
     * @return
     */
    @Override
    public Token recoverInline(Parser recognizer)
             // throws RecognitionException
    {
        throw new RuntimeException(new InputMismatchException(recognizer));
    }
    /** Make sure we don't attempt to recover from problems in subrules. */
    @Override
    public void sync(Parser recognizer) { }

    @Override
    public void reportNoViableAlternative(Parser parser,
                                          NoViableAltException e)
            // throws RecognitionException
    {
// ANTLR generates Parser subclasses from grammars and
// Parser extends Recognizer. Parameter parser is a
// pointer to the parser that detected the error
        String msg = "array size must be an integer constant"; // nonstandard msg
        parser.notifyErrorListeners(e.getOffendingToken(), msg, e);
    }
}
