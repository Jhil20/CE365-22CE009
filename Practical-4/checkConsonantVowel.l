%option noyywrap
%{
	#include<stdio.h>
	int v=0, c=0;
%}

%%
[aeiouAEIOU]	{v++;}
[a-zA-z]	{c++;}
[\t\n]		;
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