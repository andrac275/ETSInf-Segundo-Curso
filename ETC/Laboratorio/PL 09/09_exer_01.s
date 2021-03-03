          .globl __start
          .data 0x10000000
M:        .space 4
 
          .text 0x00400000
__start:  li $a0,'M'
          la $a1, M
          jal InputV

          # Terminar el proceso
          li $v0,10
          syscall

InputV:   li $v0, 11
          syscall
          li $v0, 11
          li $a0, '='
          syscall
          li $v0, 5
          syscall
          sw $v0, 0($a1)
          jr $ra

Output:   # Output acepta parámetros directamente en registros: 
          #   void Output (char $a0, int $a1)
          # Hay que cambiarla por OutputV, que opera sobre variables en memoria:
          #   void OutputV (char $a0, int *$a1)

          li $v0, 11        # print_char
          syscall           # Imprime $a0
          li $v0, 11        # print_char
          li $a0, '='       # Carácter ‘=’ en $a0
          syscall           # Imprime ‘=’
          li $v0, 1         # print_int
          move $a0, $a1     # Entero a imprimir en $a0
          syscall           # Imprime $a0
          li $v0, 11        # print_char
          li $a0, 10        # Carácter ASCII ‘\n’ en $a0
          syscall           # Imprime ‘\n’
          jr $ra            # Retorno de Output

MultR:    # MultR acepta parámetros y retorna el resultado directamente en registros:
          #    int Mult (int $a0, int $a1) 
          # Hay que cambiarla por MultV, que opera sobre variables en memoria:
          #    void MultV (int *$a0, int *$a1, int *$a2)

          mult $a0, $a1     # Multiplica argumentos
          mflo $v0          # Resultado en $v0
          jr $ra            # Retorno de Mult

