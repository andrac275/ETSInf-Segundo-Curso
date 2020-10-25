        .globl __start
        .text 0x00400000

__start:
        li $s0,32
        li $s1,127
		addi $s2,$s2, 4	#Cada 4 linies botara.
bucle:
		addi $s2,$s2, -1	#Restar 1 al contador de linia
		
        li $v0,1
        move $a0,$s0	#Mostra ENTER ascii
        syscall
		
        li $v0,11
        li $a0,9	#tabula
        syscall
		
        li $v0,11
        move $a0,$s0	#Mostra CARACTER ascii
        syscall
		
        li $v0,11
        li $a0,9	#tabula
        syscall
		
		beqz $s2,saltolin	#Si ja hem escrit 4, bota linia.
		
		addi $s0,$s0,1	#Sino... Avança a la següent lletra	ascii
        blt $s0,$s1,bucle	#i torna al principi fins que no acabes

		bgt $s0,$s1,acabar	#Botar al final, perque lo anterior, line 30, no se
							#compleix i ja acabem i no vull que fassa saltolin.

saltolin:
		addi $s2,$s2, 4	#Reiniciar contador linia.

        li $v0,11
        li $a0,10	#Salto linea
        syscall
		
		blt $s0,$s1,bucle	#Tornar al bucle si no hem acabat.

acabar:
        li $v0,10
        syscall
