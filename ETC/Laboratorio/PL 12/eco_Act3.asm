### PROGRAMA BASE PRACTICAS DE ENTRADA/SALIDA MIPS R2000

#-------------------------------------------------#
#
#  PR�CTICA 12: SINCRONIZACI�N POR PRUEBA DE ESTADO
# 
#-------------------------------------------------#

# ACTIVIDAD 3:  Completar las funciones:
#    char getchar() - obtiene el car�cter del teclado
#    void putchar(char c) - imprime un car�cter por la consola

# Segmento de datos

	.data		

#-------------------------------------------------#

# Segmento de c�digo ("text")
	.text
    	.globl __start	



__start:			

	li $a0, 'P'		# 
	jal putchar		# putchar('P')
	li $a0, '1'		# 
	jal putchar		# putchar('1')
	li $a0, '2'		# 
	jal putchar		# putchar('2')
	li $a0, 13		# car�cter de retorno ('\n')
	jal putchar		# putchar('\n')
	
bucle:
	jal getchar		# $v0 = getchar()
	move $a0, $v0		#
	li $t0, 0x1b      	# detecto ESC (0x1b = 27)
	beq $a0, $t0, fin
	jal putchar		# putchar($a0)
	b bucle
fin:	
	li $v0, 10
	syscall			# exit
	
	
	
getchar:			# $v0 = getchar()
### A COMPLETAR: c�digo de la funci�n
#ACI ANDREU:
	li $t0, 0xffff0000			#$t0 = adre�a base del teclat

getcharEspera:					#Repetir:
	lb $t1, 0($t0)				#	Llegir registre d'ordres /estat del teclat
	andi $t1, $t1, 1			#	Aillar el bit R 
	beqz $t1, getcharEspera		#Fins que R =1

	lbu $v0,4 ($t0)				#Fer $v0=Registre de dades del teclat

	jr $ra


putchar:			# putchar($a0)
### A COMPLETAR: c�digo de la funci�n
#ACI ANDREU:
	li $t0, 0xffff0008			#$t0 = adre�a base de la pantalla
	
putcharEspera:					#Repetir:
	lb $t1,0($t0)				#Llegir registre d'ordres /estat de la pantalla
	andi $t1, $t1, 1			#Aillar el bit R 
	beqz $t1, putcharEspera		#Fins que R no siga 1, fer el bucle
	
	sb $a0, 4 ($t0)				#Fer Registre de dades del teclat =$a0

	jr $ra						
