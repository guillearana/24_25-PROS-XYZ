#include <stdio.h>   // Librería estándar para funciones de entrada/salida (como printf)
#include <unistd.h>  // Librería para funciones relacionadas con procesos (como fork)
#include <sys/types.h> // Librería para definir tipos de datos relacionados con procesos (como pid_t)
#include <sys/wait.h>  // Librería para la función wait() que espera la finalización de los procesos hijos

int main() {
    pid_t pid;            // Variable para almacenar el PID del proceso hijo
    int variable = 6;     // Inicializar la variable con el valor 6

    printf("Valor inicial de la variable: %d\n", variable);

    // Crear el proceso hijo
    pid = fork();

    if (pid < 0) {
        // Error al crear el proceso hijo
        perror("Error en fork");
        return 1;
    }

    if (pid == 0) {
        // Este bloque se ejecuta solo en el proceso hijo
        variable -= 5;  // El hijo resta 5 a la variable
        printf("Variable en Proceso Hijo: %d\n", variable);
    } else {
        // Este bloque se ejecuta solo en el proceso padre
        variable += 5;  // El padre incrementa 5 a la variable
        wait(NULL);     // Espera a que el hijo termine
        printf("Variable en Proceso Padre: %d\n", variable);
    }

    return 0;  // El programa termina exitosamente
}
