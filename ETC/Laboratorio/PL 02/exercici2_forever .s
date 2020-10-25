          .globl __start
          .text 0x00400000

__start:
          li $s0, 0
bucle:
          li $v0,5
          syscall
		  beqz $v0,acabar	# <--Exercici2: Bota a la etiqueta acabar si el valor introduit es 0.
          addu $s0,$s0,$v0
          li $v0,1
          move $a0, $s0
          syscall
          li $v0,11
          li $a0, '\n'
          syscall
          b bucle
acabar:
          li $v0,10
          syscall
