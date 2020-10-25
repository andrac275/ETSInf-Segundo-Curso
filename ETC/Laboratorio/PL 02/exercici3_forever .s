          .globl __start
          .text 0x00400000

__start:
          li $s0, 0	#Acumulador de la suma
		  li $s1, 0	#Contador iteracions
bucle:
          li $v0,1	#Crida print_int
		  add $a0, $s1, 1	#Guardar en $a0, que es on deu anar el printint
          syscall
		  addi $s1,$s1,1	#Incrementar en 1 el contador
		  
		  li $v0,11		#print_char pag 3/14
		  li $a0,'>'	#Caracter a imprimir '>'
		  syscall
		  
		  li $v0,5		#Llegir enter teclat
          syscall
		  
		  beqz $v0,acabar	# Ordre per acabar el bucle

          addu $s0,$s0,$v0	#Actualitzar acumulador
		  
          #li $v0,1		#print_int
          #move $a0, $s0	# Mostra per pantalla el valor acumulat
          #syscall
		  
          #li $v0,11		#print_char pag 3/14
          #li $a0, '\n'	#Caracter a imprimir 'salto de linea'
          #syscall
		  
          b bucle
acabar:
		#Mostrar la suma	
		  li $v0,11		#print_char pag 3/14
		  li $a0,'='	#Caracter a imprimir '='
		  syscall
		  
		  li $v0,1	#Crida print_int
		  move $a0, $s0	#Mou el acumulador de suma s0 a a0, que el la ixida de printin
		  syscall
		  
		  li $v0,11		#print_char pag 3/14
          li $a0, '\n'	#Caracter a imprimir 'salto de linea'
          syscall
		  
		#Mostrar numero de iteraciones  
		  li $v0,11		#print_char pag 3/14
		  li $a0,'n'	#Caracter a imprimir 'n'
		  syscall
		  
		  li $v0,11		#print_char pag 3/14
		  li $a0,'='	#Caracter a imprimir '='
		  syscall
		  
		  li $v0,1		#print_int
		  move $a0,$s1	#Meneja a a0 la quantitat de iteracions que s'han fet.	  
		  syscall
		  
          li $v0,10		#exit
          syscall
