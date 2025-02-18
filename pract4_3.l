%option noyywrap
%{
	#include<stdio.h>
	int word=0, character=0, line=0;
%}

%%
[\n]	{line++;}
[ \t]	{c+=yyleng;}
\w+		;
.		;
%%

int main()
{
	printf("Enter String : ");
	yylex();
	printf("No of Vowel : %d",v);
	printf("No of consonants : %d",c);
	return 0;
}