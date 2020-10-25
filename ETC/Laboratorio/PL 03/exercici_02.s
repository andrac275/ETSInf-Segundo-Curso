          .globl __start
          .text 0x00400000
__start:  
			li $a0, 'M'
		  jal Input
		  move $s0, $v0
		  
          li $a0, 'Q'
          jal Input
          move $s1, $v0
		  
		  move $a0,$s0
		  move $a1,$s1
		  
          jal Mult
		  move $a1,$v0	#Guardar en $a1 el resultat de la multiplicacio
		  
		  li $a0, 'R'		  
		  jal Output
		  
          li $v0,10	#Exit
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
