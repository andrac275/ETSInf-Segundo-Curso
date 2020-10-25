#include <stdio.h>

int main(int argc, char *argv[]) {   
     // A completar
    
    int i;
    for (i = 1; i < argc; i++){
        char v = argv[i][1];
        switch (v) {
            case 'c':
                printf("Argumento %d es Compilar\n", i); break;
            case 'E':
                printf("Argumento %d es Preprocesar\n", i); break;
            case 'i':
                printf("Argumento %d es Incluir %s\n", i, &argv[i][2]); break;
                
            default:
            printf("Argumento %d no esta implementat\n", i);  
        }//Cierre switch
    } //Cierre for
}

