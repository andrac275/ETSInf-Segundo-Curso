          .globl __start
          .data 0x10000000
M:        .space 4
N:        .space 4
R:        .space 4
 
          .text 0x00400000
__start:  li $a0,'M'
          la $a1, M
          jal InputV
		  
		  li $a0,'N'
          la $a1, N
          jal InputV
		  
		  la $a0,M	#Carrega la direccio del nombre M
          la $a1, N	#Carrega la direccio del nombre N
		  la $a2,R	#Carrega la direccio de on guardarem R
		  
          jal MultV
		  
		  li $a0,'R'
          la $a1, R
          jal OutputV

          # Terminar el proceso
          li $v0,10
          syscall

InputV:   li $v0, 11	#Treu el que hi ha en $a0... M,N,R en cada pasada a InputV
          syscall
          li $v0, 11
          li $a0, '='
          syscall
		  
          li $v0, 5	#Llig un enter
          syscall
		  
          sw $v0, 0($a1)	#Guarda el enter en la adreça que hi ha en el registre $a1
          jr $ra

OutputV:  #   void OutputV (char $a0, int *$a1)

          li $v0, 11        # print_char
          syscall           # Imprime $a0
		  
          li $v0, 11        # print_char
          li $a0, '='       # Carácter ‘=’ en $a0
          syscall           # Imprime ‘=’
		  
          li $v0, 1         # print_int
          lw $a0, 0 ($a1)     # Entero a imprimir en $a0
          syscall           # Imprime $a0
		  
          li $v0, 11        # print_char
          li $a0, 10        # Carácter ASCII ‘\n’ en $a0
          syscall           # Imprime ‘\n’
		  
          jr $ra            # Retorno de Output

MultV:    # MultR acepta parámetros y retorna el resultado directamente en registros:
          #    int Mult (int $a0, int $a1) 
          # Hay que cambiarla por MultV, que opera sobre variables en memoria:
          #    void MultV (int *$a0, int *$a1, int *$a2)

		  lw $t0,0($a0)	#Carrega CONTINGUT de la direccio del nombre M
		  lw $t1,0($a1) #Carrega CONTINGUT de la direccio del nombre N
		  

          mult $t0, $t1     # Multiplica argumentos M*N
          mflo $t0          # Resultado de M*N en $t0
		  
		  sw$t0,0($a2)	#Guarda el resultat $t0 en la direccio de R
		  
          jr $ra            # Retorno de Mult

