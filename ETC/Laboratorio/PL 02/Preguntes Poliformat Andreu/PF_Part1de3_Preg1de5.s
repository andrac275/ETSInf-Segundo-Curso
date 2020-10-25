#Este programa diu quin dels dos nombres introduits es el major.
.globl __start
.text 0x00400000

__start:
bnez $t0,eti 	
blt $t0,$t1,eti

eti:
          li $v0,10
          syscall
