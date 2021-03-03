          .globl __start
          .data 0x10000000
demana:   .asciiz "Escriviu-me alguna cosa: "
mostra:   .asciiz "Heu escrit: "
long:	  .asciiz "Llarg de la cosa: "
cadena:   .space 80             

          .text 0x00400000
__start:  la $a0, demana    
    
          la $a1, cadena    #Direccio 'cadena' que cal omplir   
          li $a2, 80    	#Llargaria de la 'cadena'
		  
          jal InputS

		  la $a0, mostra
		  la $a1, cadena
		  
		  jal OutputS
		  
		  la $a0,long
		  li$v0,4		  
		  syscall	#Treu per pantalla "Llarg de la cosa: "
		  
		  la $a0,cadena
		  jal StrLength	
		  
		  move $a0,$v0
		  li $v0,1	#imprimeix $v0 que es un comptador calculat en la funcio StrLength
          syscall
		  
		  li $v0,10	#Acaba programa
          syscall

InputS:   li $v0, 4 #Mostra lo guardat en la direccio $a0: "Escriviu-me alguna cosa: "
          syscall
		  
		#ANDREU: read_string guarda una cadena en memoria. Mira els coments, que son interessants
          li $v0, 8	#read_string	
          move $a0, $a1	#read_string necessita en $a0 la direccio de memoria des d'on llig
          move $a1, $a2 #i en $a1 el tamany total a llegir. 80 en el nostre cas, que son els espais que hem deixat.
          syscall	#Hem guardat lo introduit en la etiqueta 'cadena'
		  
          jr $ra

OutputS:  li $v0, 4	#Mostra lo guardat en la direccio $a0: "Heu escrit: "
          syscall
		  
          li $v0, 4	#Imprimira lo que hi ha en $a0 despres del move en el syscall
          move $a0, $a1	#la adreça de 'cadena' la mou a $a0
          syscall	#treu per pantalla el contigut de $a0, que es el text que hem introduit.
		  
          jr $ra
		  
StrLength: li $v0,0		#Posa en $v0 un zero. SEGUEIX BAIX, NO TORNA

BuscaNull: 	lbu $t0,0($a0)	#Carrega un caracter de la cadena en $t0
			beqz $t0,StrLengthEnd #Si el contingut de $t0 es 'null' acaba
			addiu $a0,$a0,1	#Avança al següent Byte (caracter de la cadena)
			addiu $v0, $v0,1 #Incrementa contador $v0 per a dir al final tamany
			b BuscaNull

StrLengthEnd:
			addiu $v0,$v0,-1
			jr $ra		#ARA TORNA $v0 que es un comptador del numero de caracters de la cadena