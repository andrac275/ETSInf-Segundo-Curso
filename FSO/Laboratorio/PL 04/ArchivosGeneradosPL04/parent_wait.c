/* my_child.c */
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>


int main()
{
   int i;
   int aux;
   pid_t hola;
   printf("\n");
   printf("Soy el PADRE mi PID es: %ld \n", (long)getpid());
   for (i=0; i < 5; i++){
       aux=fork();
       if (aux == 0){ //Si es un fill el crea
        printf("Fill creat en iteracio= %d ", i);
        printf("mi PID es: %ld ", (long)getpid());
        printf("mi padre es PPID: %ld \n", (long)getppid());
        exit(i);  
        } 
        else { // Si es un pare, espera al fill amb el wait.
            hola = wait(NULL);     //El null es per ignorar l'estat dels fills. Mirar anex pagina 12 de 13.       
        }
    }
  
    sleep(10);
    exit(0);
    
   
}
