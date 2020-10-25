#include <stdio.h>

int main(int argc, char *argv[]) {   
     // A completar
    
    int contador;
    for (contador = 0; contador < argc; contador++){
        printf("Argumento %d es %s\n", contador, argv[contador]);        
    }
}

