### ESTRUCTURA DE COMPUTADORES (ETS Eng. Inf.)
###
## marzo de 2014
##
## PRÁCTICA 13: SINCRONIZACIÓN POR INTERRUPCIONES
##
## Programa usuario para la práctica

#------- Segmento de datos ---------#
	.data		
T1:	.asciiz "Trabajando...\n"

T2:	.asciiz "\nFin\n"

#------- Segmento de código ---------#
	.text
    	.globl __start	

main:	la $a0,T1
	li $v0,4
	syscall
	li $t0, 30		#<-Andreu: Ajustar el nombre de segons. El inmediat
					# es el numero millons de iteracions que fa.
					#Amb 50 tarda uns 15 segons en acabar en el meu ordinador.

bucleE: li $t1, 1000000  

bucleI:	addi $t1,$t1,-1	
	bgtz $t1, bucleI

	addi $t0,$t0,-1  
	bgtz $t0, bucleE 

	la $a0,T2
	li $v0,4
	syscall

	jr $ra

	.end