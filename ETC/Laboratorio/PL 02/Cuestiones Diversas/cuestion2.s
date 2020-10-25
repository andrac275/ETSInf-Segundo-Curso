.globl __start
.text 0x00400000

__start:
	li $s0,'0'
	li $s1,'9'
	li $s2,'f'
	
bucle:
	li $v0,12
	syscall
	
	beq $v0,$s2,fin	#Si apretes 'f' vas a fin i se acabo.
	bge $v0,$s0, mayor	# Si el valor ascii introduit per teclat v0 es major o igual
		#que el codi ascii de s0 (0 en decimal), ves a la etiqueta 'mayor'.
	b bucle	#si no ho es, torna al bucle fins que ho siga o apreten 'f'
			
mayor:
		bge $v0,$s1, bucle	# Si el valor v0 de abans es major que el ascii 57 (9 decimal)
			#torna al bucle. Si no ho es, aleshores implica que has escrit un nombre entre
			#0 i 9 i se pot printejar amb les següents linies.			
		move $a0,$v0
		li $v0,11
		syscall
		b bucle	

fin:
	li $v0,10 #exit
	syscall