/* my_child.c */
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>


int main()
{
   int i;
   int aux;
   printf("\n");
   printf("Soy el PADRE mi PID es: %ld \n", (long)getpid());
   for (i=0; i < 5; i++){
       aux=fork();
       if (aux == 0){
        printf("Fill creat en iteracio= %d ", i);
        printf("mi PID es: %ld ", (long)getpid());
        printf("mi padre es PPID: %ld \n", (long)getppid());
        exit(i);  
        }     
    }
    sleep(10);
    exit(0);
    
   
}
