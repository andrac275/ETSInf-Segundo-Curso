.globl __start
.text 0x00400000

__start:
	li $at,20

	li $v0,10		#exit
	syscall