#Este programa diu quin dels dos nombres introduits es el major.
.globl __start
.text 0x00400000

__start:
		li $v0,5	#Introduces un valor. Imaginemos el 7.
        syscall
        move $a0, $v0	#Mueve lo que hay en v0 a a0.	En a0 se guarda un 7
        
		li $v0,5	#Introduces segundo valor. Imaginemos el 3. Lo deja en v0
        syscall

        blt $v0,$a0,show	#Si v0 < a0 (si 3 < 7)
        move $a0,$v0
show:
          li $v0,1
          syscall

acabar:
          li $v0,10
          syscall
