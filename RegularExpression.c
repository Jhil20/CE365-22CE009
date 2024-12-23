#include <stdio.h>

int main() {
    int i = 0;
    printf("Enter a string: ");
    char str[100];
    scanf("%[^\n]%*c", str);
    printf("%s\n", str);
    while(str[i]=='a'){
        i++;
    }
    if(str[i]=='b' && str[i+1]=='b' && str[i+2] =='\0'){
        printf("Valid string");        
    }
    else{
        printf("Invalid string");
    }
    return 0;
}