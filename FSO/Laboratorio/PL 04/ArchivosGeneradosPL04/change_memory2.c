/* change_memory1.c */
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

int main(int argc, char *argv[])
{
    pid_t childpid;
    int status, x;
    char* argumentos[] = {"ls", "-R", "/"};

    childpid = fork();
    if (childpid == -1) {
        fprintf(stderr, "fork failed \n");
        exit(1);
    } else if (childpid == 0) {
        if (execvp(argv[1], &argv[1]) < 0) {    #Esta linia, Mira els arguments.
            fprintf(stderr, "Could not execute ls \n");
            exit(1);
        }
    }
    x = wait(&status);
    if (x != childpid)
        fprintf(stderr, "Child has been interrupted by a signal \n");
    exit(0);
}
