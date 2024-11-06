#include <sched.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>

void main (){
    pid_t pids[3], pid_padre;
    for (int i=0; i<3; i++) {
        pids[i] = fork();

        if (pids[i] <0) {
            printf ("no se ha podido crear el proceso...");
            exit (-1);
        }
        if (pids[i] == 0)   ////////// Nos encontramos en el proceso hijo
	    {
		    printf ("Soy el hijo %d, Mi padre es: %d, mi PID es %d \n", (i+1), getppid(), getpid());
            exit(0);
	    }
    }
    printf("Proceso padre %d",getpid());
    // El proceso padre espera a que terminen todos los hijos
    for (int i = 0; i < 3; i++) {
        wait(NULL);
    }
}