%option noyywrap
%{
    #include<stdio.h>
%}

%%
"auto"|"break"|"case"|"char"|"const"|"continue"|"default"|"do"|"double"|"else"|"enum"|"extern"|"float"|"for"|"goto"|"if"|"int"|"long"|"register"|"return"|"short"|"signed"|"sizeof"|"static"|"struct"|"switch"|"typedef"|"union"|"unsigned"|"void"|"volatile"|"while"   {printf("%s : keyword",yytext);}
[a-zA-Z_][a-zA-Z_0-9]*  {printf("%s is a indentifier",yytext);}
"//".*  {}
"/*"([^*])*"*/"    {}
"+"|"-"|"*"|"="|"/"|"%"|"++"|"--"|"=="|"!="|">="|"<="|">"|"<"|"&&"|"||"|"!"|"&"|"|"|"^"|"~"    {printf("%s is a operator",yytext);}
";"|","|"."|":"|"?"|"("|")"|"{"|"}"|"["|"]"|"/*"|"*/"|"//" {printf("%s is a special symbols",yytext);}
[0-9\-][0-9]+  {printf("%s is a integer constant",yytext);}
[0-9]+\.[0-9]   {printf("%s is floating point constant",yytext);}
\"([^\"]*)\"    {printf("%s is string constant",yytext);}
\'([a-zA-z^\'])\'   {printf("%s is char constant",yytext);}
[0-9@#$][a-zA-Z0-9_]*[@$#]* {printf("%s invalid indentifier",yytext);}
[\t\n ] {}
%%

int main()
{
yyin = fopen("input.c","r");
yylex();
return 0;
}