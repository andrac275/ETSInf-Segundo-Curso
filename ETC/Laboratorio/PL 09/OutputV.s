          .globl __start
          .data 0x10000000
M:        .space 8
		  
 
          .text 0x00400000
__start:  li $a0,'K'
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

OutputV:  #   void OutputV (char $a0, int *$a1)

          li $v0, 11        # print_char
          syscall           # Imprime $a0
		  
          li $v0, 11        # print_char
          li $a0, '='       # Carácter ‘=’ en $a0
          syscall           # Imprime ‘=’
		  
          li $v0, 1         # print_int
          lw $a0, 0($a1)    	# ESTA ES LA SOLUCIO Exercisi1. Entero a imprimir en $a0
          syscall           # Imprime $a0
		  
          li $v0, 11        # print_char
          li $a0, 10        # Carácter ASCII ‘\n’ en $a0
          syscall           # Imprime ‘\n’
		  
          jr $ra            # Retorno de Output

