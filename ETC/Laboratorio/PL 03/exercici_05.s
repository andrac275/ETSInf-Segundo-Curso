	.globl __start
		.text 0x00400000
__start: #10 es novalinia (Intro), 0 es null, 39 es ', 92 es \
Do:		li $v0,12	
		syscall		#Llig Caracter de teclat.
		move $a0,$v0
		
		jal PrintChar
		j Do
		li $v0,10
		syscall

PrintChar:
		move $t0,$a0	#####PrintChar		#Guarda en temporal el caracter introduit
		
		li $v0,11	#Crida sistema per imprimir caracter que estiga en $a0
		li $a0,39	#Possa la comilla a $a0 xk se puga imprimir
		syscall		#Imprimeix la comilla simple '
		
If:		bnez $t0, ElseIf	####If   #Introduir caracter null.
		li $a0,92	#\
		syscall		#Imprimeix la barra \
		
		li $a0,'0'	#0
		syscall		#Imprimeix el 0		\0
		jr EndIf
		
ElseIf:	li $t1,10	####ElseIf		#Polsar intro
		bne $t0,$t1, Else
		li $a0,92	#\
		syscall		#Imprimeix \
		li $a0,'n'	#n
		syscall		#Imprimeix n.    \n
		jr EndIf
		
Else:	move $a0,$t0	####Else.	#Default: Si no es null o intro, imprimeix el caracter possat.
		syscall		#El caracter el portem enmagatzemat en la $t0
		
EndIf:	li $a0,39	####EndIf.  
		syscall	#Posa un altra cometa 39 es cometa '
		jr $ra	#Torna al jal de la linia 8