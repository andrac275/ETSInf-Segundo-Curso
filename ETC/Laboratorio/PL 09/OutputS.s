          .globl __start
          .data 0x10000000
demana:   .asciiz "Escriviu-me alguna cosa: "
mostra:   .asciiz "Heu escrit: "
cadena:   .space 80             

          .text 0x00400000
__start:  la $a0, demana    
    
          la $a1, cadena    #Direccio 'cadena' que cal omplir   
          li $a2, 80    	#Llargaria de la 'cadena'
		  
          jal InputS

		  la $a0, mostra
		  la $a1, cadena
		  
		  jal OutputS
		  
		  li $v0,10
          syscall

InputS:   li $v0, 4
          syscall
          li $v0, 8
          move $a0, $a1
          move $a1, $a2
          syscall
          jr $ra

OutputS:  li $v0, 4
          syscall
          li $v0, 4
          move $a0, $a1
          syscall
          jr $ra
		  