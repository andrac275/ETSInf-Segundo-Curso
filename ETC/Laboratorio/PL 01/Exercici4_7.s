#Exercisi 4. Apartat 5
.globl __start
.data 0x10000000
	X:	.space 4

.text 0x00400000
__start:

#	la $t0,X 
#	sw $zero,0($t0)

la $t0,X 
sh $zero,0($t0)
sh $zero,2($t0)
