          .globl __start
          .text 0x00400000
__start:  
			li $a0, 'M'
		  jal Input
		  move $s0, $v0
		  
          li $a0, 'Q'
          jal Input
          move $s1, $v0	

		  #Exercici 3:Comprobar si $a1 (que es Q) es negatiu. Si ho es, cambiar signe.
		  move $a0,$s0
		  move $a1,$s1
		  bltz $a1, Swap
		  
		  #Multiplicacio
Calculo:  jal Mult
		  move $a1,$v0	#Guardar en $a1 el resultat de la multiplicacio
		  
		  #R+Output
		  li $a0, 'R'		  
		  jal Output
		  
		  li $a0,'\n'	
		  li $v0,11
		  syscall	
		  
		  #Exercici 4: Comprobar si M i Q son 0 per acabar
		  beqz $s0, Q0
		  b __start		#Si M no es 0, tornem al inici.
		  
Q0:		  beqz $s1, Acabar	#Si M es 0, veure si Q tambe ho es. Si es, Acabar
		  b __start	#Si no es, tornar a començar
		  
Acabar:   li $v0,10	#Exit
          syscall
		  
Mult:     li $v0, 0
          beqz $a1, MultRet
MultFor:  add $v0, $v0, $a0
          addi $a1, $a1, -1
          bne $a1, $zero, MultFor
MultRet:  jr $ra

Input:		li $v0,11		#print_char
			syscall		#$a0 ja te el caracter
			
			li $a0,'='	
			li $v0,11
			syscall		#Imprimir segona lletra, que es el igual.
			
			li $v0,5	#int_read
			syscall	
			jr $ra

Output:		li $v0,11
			syscall		#Imprimir la R, que esta guardada en $a0 des del main
			
			li $a0,'='	
			li $v0,11
			syscall		#Imprimir segona lletra, que es el igual.
			
			move $a0,$a1	#en $a1 teniem el resultat de la multi. Passarla a $a0 per printejar
			li $v0,1	#int_print
			syscall
			jr $ra

Swap:		sub $a0, $0, $a0
			sub $a1, $0, $a1
			b Calculo