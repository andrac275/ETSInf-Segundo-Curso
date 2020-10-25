#include <stdio.h> 

#define TAM_FILA 100
#define NUM_FILAS 10
struct FILA {
    float datos[TAM_FILA];
    float suma;
};


void suma_fila(struct FILA *pf) {
// B) Implementar suma_fila
    int i;
    for (i=0; i < TAM_FILA; i++){
        pf -> suma = pf -> suma + pf -> datos[i];
        //pf++;
    }
}

// Inicia las filas con el valor i*j
void inicia_filas(struct FILA *px, int x) {
    int j;
        for (j = 0; j < TAM_FILA; j++) {
            px -> datos[j] = (float)x*j;
        }
        px++;
}

int main() { 
    int i;
    struct FILA filas[NUM_FILAS];
    float suma_total;
    
    suma_total = 0;
    
    for (i = 0; i < NUM_FILAS; i++) {
        inicia_filas(&filas[i], i);
        suma_fila(&filas[i]);
        printf("La suma de la fila %u es %f\n", i,  filas[i].suma);
        suma_total = suma_total + filas[i].suma;
    }
    
    printf("La suma final es %f\n", suma_total); 
}

