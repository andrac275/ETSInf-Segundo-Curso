// redir_output.c
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

int main (int argc,char *argv[]) {
    int fd;
    char *arch = "ls_eixida.txt";   //CAMBIAR EL NOM DEL ARXIU
    mode_t fd_mode = S_IRWXU;// file premissions

    fd = open(arch,O_RDONLY | O_CREAT,fd_mode);
    if (dup2(fd,STDIN_FILENO) == -1) { 
        printf("Error calling dup2\n");
        exit(-1);
    }

    execl("/bin/cat","cat", arch,NULL);   //TREURE LA EIXIDA DEL Cat que 
                                //per defecte la treu pel descriptor 1
    fprintf(stdout,"out: Output redirected\n");
    fprintf(stderr,"error: not redirected\n");
    fprintf(stderr,"Check file %s\n",arch);
    close(fd);
    return(0);
}


