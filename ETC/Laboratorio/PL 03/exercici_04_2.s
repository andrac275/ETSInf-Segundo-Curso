          .globl __start
          .text 0x00400000
__start:  
		  li $a0, 'N'
		  jal Input
		  move $s0, $v0	#En $s0 guardem el numero del que volem la taula.
		  
			li $s1,0	#Contador del bucle en $s1
			li $s2,10	#Num iteracions maximes del bucle. Fins a "per 10"
		  
Taula:	  addi $s1, $s1, 1	#Avançar iteracions
		  bgt $s1, $s2, Acabar	#Si passem de $s2 multis, acabem.
		  
		  move $a0, $s0
		  move $a1, $s1
		  
Calculo:  jal Mult
		  move $s3,$v0	#Guardar en $s3 el resultat de la multiplicacio
		  
		  #Output
		  move $a0, $s0
		  move $a1, $s1
		  move $a2, $s3		  
		  jal Output
		  
		  b Taula
		  
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

Output:		li $v0,1
			syscall		#Imprimir la "taula del", que esta guardada en $a0 des del main
			
			li $a0,'x'	
			li $v0,11
			syscall		#Imprimir segona lletra, que es x.
			
			move $a0, $a1
			li $v0,1
			syscall		#Imprimir iteracio, que esta guardada en $a1 des del main
			
			li $a0,'='	
			li $v0,11
			syscall		#Imprimir el igual.
			
			move $a0,$a2	#en $a2 teniem el resultat de la multi des del main. Passarla a $a0 per printejar
			li $v0,1	#int_print
			syscall
			
			li $a0,'\n'	#Salto linia
		    li $v0,11
		    syscall
			
			jr $ra
