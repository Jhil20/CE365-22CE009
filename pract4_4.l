%{
	#include<stdio.h>
	int upper=0, lower=0 , special=0, digit=0, flag=1, len=0,count=0;
%}

%%
[a-z]   {lower++; len++;}
[A-Z]    {upper++; len++;}
[*,;#$@]    {special++; len++;}
[0-9]   {digit++; len++;}
[\n]    {if(digit>0 && upper>0 && lower>0 && special>0 && len>0 && flag==1) 
            printf("Valid passsword");
        else
            printf("Invalid");
        digit=0;
        upper=0;
        lower=0;
        special=0;
        len=0;
        flag=1;
        }
.   {flag=0;}
%%
int main()
{
	printf("Enter String : ");
	yylex();
	return 0;
}
int yywrap()
{}
