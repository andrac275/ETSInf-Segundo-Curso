#Pregunta 3 del test de Andreu
.globl __start
.text 0x00400000

__start:
        li $t0,'p' # ascii('p') = 112
         li $t1,4
loop:    move $a0,$t0
         li $v0,11
         syscall
         addi $t0,$t0,2
         addi $t1,$t1,-1
         bgtz $t1,loop

acabar:
          li $v0,10
          syscall
