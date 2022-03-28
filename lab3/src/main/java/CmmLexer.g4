lexer grammar CmmLexer;

channels{
    COMMENT
}



SPACE: [ \t\r\n]+ -> channel(HIDDEN);
INLINE_COMMENT: '//' .*?'\n' -> channel(COMMENT); // ?-->非贪婪匹配
MUTI_COMMENT: '/*' .*?'*/' -> channel(COMMENT);

TYPE: 'int'|'float';
STRUCT: 'struct';
RETURN: 'return';
IF: 'if';
ELSE: 'else';
WHILE: 'while';


FLOAT: ((((([0-9]*)DOT([0-9]+))|(([0-9]+)DOT([0-9]*)))([eE](PLUS|MINUS)?[0-9]+)?)|(([0-9]+)([eE](PLUS|MINUS)?[0-9]+)))([FfLl]?);
//

// INT: DEC_INT|OCT_INT|HEX_INT;
OCT_INT: '0'[0-7]+;
DEC_INT: [1-9][0-9]*|'0';
HEX_INT: ('0x'|'0X')[0-9a-fA-F]+;

//
SEMI: ';';
COMMA:',';
ASSIGNOP: '=';

RELOP: '>'|'<'|'>='|'<='|'=='|'!=';
PLUS: '+';
MINUS: '-';
STAR: '*';
DIV: '/';
AND: '&&';
OR: '||';
NOT: '!';
DOT: '.';

LP: '(';
RP: ')';
LB: '[';
RB: ']';
LC: '{';
RC: '}';

ID: [_a-zA-Z][_a-zA-Z0-9]*;


