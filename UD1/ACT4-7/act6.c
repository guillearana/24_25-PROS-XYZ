#include <stdio.h>   // Librería estándar para funciones de entrada/salida (como printf)
#include <unistd.h>  // Librería para funciones relacionadas con procesos (como fork, getpid, getppid)
#include <sys/types.h> // Librería para definir tipos de datos relacionados con procesos (como pid_t)
#include <sys/wait.h>  // Librería para la función wait() que espera la finalización de los procesos hijos

int main() {
    pid_t pid_hijo1, pid_hijo2, pid_hijo3;  // Declaración de tres variables para almacenar los PIDs de los hijos

    // Crear el primer hijo (hijo 1)
    pid_hijo1 = fork();  // fork() crea un nuevo proceso duplicando el proceso padre

    if (pid_hijo1 == 0) {
        // Este bloque de código se ejecuta solo en el proceso hijo 1
        printf("Yo soy el hijo 1, mi padre es PID=%d, yo soy PID=%d\n", getppid(), getpid());
    } else {
        // Este bloque se ejecuta en el proceso padre (ya que fork() no retornó 0)

        // Crear el segundo hijo (hijo 2)
        pid_hijo2 = fork();  // Segundo fork para crear el hijo 2
        if (pid_hijo2 == 0) {
            // Este bloque se ejecuta solo en el proceso hijo 2
            printf("Yo soy el hijo 2, mi padre es PID=%d, yo soy PID=%d\n", getppid(), getpid());

            // Crear el tercer hijo dentro del hijo 2 (hijo 3)
            pid_hijo3 = fork();  // El hijo 2 crea un tercer hijo
            if (pid_hijo3 == 0) {
                // Este bloque se ejecuta solo en el proceso hijo 3
                printf("Yo soy el hijo 3, mi padre es PID=%d, yo soy PID=%d\n", getppid(), getpid());
                // El proceso hijo 3 tiene como padre al hijo 2
            } else {
                // Espera a que el hijo 3 termine antes de continuar
                wait(NULL);  // El hijo 2 espera a que el hijo 3 termine
            }
        } else {
            // Este bloque se ejecuta en el proceso padre original

            // wait(NULL) se utiliza para que el proceso padre espere a que terminen sus hijos
            wait(NULL);  // Espera a que termine el proceso hijo 1
            wait(NULL);  // Espera a que termine el proceso hijo 2 (y por tanto el hijo 3 también)
        }
    }

    return 0;  // El programa termina exitosamente
}
