#Pregunta 5 del test de Andreu
#Si escric majuscules, les escriu per consola, si escric minuscules
#no torna res per pantalla
.globl __start
.text 0x00400000

__start:
		li $t0,'A'	#Guarda valor numeric Ascii de A
        li $t1,'Z'	#Guarda valor numeric Ascii de Z
loop:
        li $v0,12	#Llig per teclat. El guarda en v0
        syscall
        blt $v0,$t0,loop	#si v0 < valorAscii'A', torna al loop 
        bgt $v0,$t1,loop	#si v0 < valorAscii'Z', torna al loop 
        move $a0,$v0	#Si el valor esta entre A i Z, el guarda en a0
        li $v0,11	#Treu per pantalla el valor de $a0
        syscall
        b loop	

acabar:
          li $v0,10
          syscall
