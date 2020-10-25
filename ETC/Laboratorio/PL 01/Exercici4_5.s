#Exercisi 4. Apartat 5
.globl __start
.data 0x10000000
	N:	.space 4

.text 0x00400000
__start:
	la $t0, N
	li $t0, 200000
