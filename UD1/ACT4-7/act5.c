#include <sched.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>

int main() {
    
    int n;
    printf("Introduce un numero: ");
    scanf("%d", &n);  // Le pide al usuario que ingrese un número entero y lo almacena en la variable 'n'.
    printf("Soy el proceso padre mi PID es: %d\n", getpid());
    pid_t pid;  // Declara una variable de tipo 'pid_t' para almacenar el PID del proceso hijo en cada iteración.

    for (int i = 0; i < n; i++) {  // Un bucle que se ejecuta 'n' veces, creando procesos en cadena.
        pid = fork();  // Se crea un proceso hijo con cada llamada a 'fork()'.
        
        if (pid < 0) {  // Si 'fork()' devuelve un valor negativo, hubo un error al crear el proceso.
            printf("No se ha podido crear el proceso...\n");  // Imprime un mensaje de error.
            exit(-1);  // Se termina el programa si no se puede crear un proceso hijo.
        }

        if (pid == 0) {  // Si 'fork()' devuelve 0, estamos dentro del proceso hijo.
            printf("Soy el hijo%d %d, Mi padre es: %d\n", i+1, getpid(), getppid());  // El hijo imprime su PID (getpid()) y el PID de su padre (getppid()).
            // El proceso hijo no realiza un 'break', lo que significa que continúa la ejecución del bucle y se convierte en "padre" para el siguiente proceso en la siguiente iteración.
        } 
        else {  // Si 'fork()' devuelve un valor positivo, estamos en el proceso padre.
            wait(NULL);  // El proceso padre espera a que su hijo termine.
            break;  // El proceso padre sale del bucle, impidiendo que cree más hijos.
        }
    }
    return 0;   
}
