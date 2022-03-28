grammar Cymbol;

expr: '(' expr ')'
    | expr '[' expr ']' // 左递归
    | <assoc = right> expr '^' expr //
    |
    ;