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
   int hola;
   printf("\n");
   printf("Soy el PADRE mi PID es: %ld \n", (long)getpid());
   for (i=0; i < 4; i++){
       aux=fork();
       if (aux == 0){ //Si es un fill el crea
        printf("Fill creat en iteracio= %d ", i);
        printf("mi PID es: %ld ", (long)getpid());
        printf("mi padre es PPID: %ld \n", (long)getppid());
        sleep(10);
        exit(i);  
        } 
        else { // Si es un pare, espera al fill amb el wait.
            sleep(2);
            wait(&hola); //Se li passa una referencia perque ens interessa el valor de dins. El valor del 
            //exit del fill (linia 23) es el valor que li aplega al pare
            printf("Soc pare, el meu PID es: %ld i el stat_loc es: %d\n", (long)getpid(), WEXITSTATUS(hola));
        }
    }
  
    sleep(10);
    exit(0);
    
   
}
