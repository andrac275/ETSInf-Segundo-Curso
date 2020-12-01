                ##########################################################
                # Segmento de datos
                ##########################################################

                .data 0x10000000
reloj:          .word 0                # HH:MM:SS (3 bytes de menor peso)

cad_asteriscos: .asciiz "\n  **************************************"
cad_horas:      .asciiz "\n   Horas: "
cad_minutos:    .asciiz " Minutos: "
cad_segundos:   .asciiz " Segundos: "
cad_reloj_en_s: .asciiz "\n   Reloj en segundos: "

                ##########################################################
                # Segmento de código
                ##########################################################

                .globl __start
                .text 0x00400000

__start:        
				#Arrancar rellotge
				la $a0, reloj
				li $a1, 23	#HH
				li $a2, 59	#MM
				li $a3, 59	#SS
				jal inicialitza_rellotge_alt
				#Mostra el rellotge amb el valor anterior
				la $a0,reloj
				jal imprime_reloj

				#Augmenta 1 segon
				la $a0,reloj
				jal passa_segon
				#Mostra el rellotge després d'augmentar 1 segon.
				jal imprime_reloj
             
salir:          li $v0, 10              # Código de exit (10)
                syscall                 # Última instrucción ejecutada
                .end

		###################################
		#Entrada $a0 -> adreça del rellotge
		#Nota: 23:59:59 -> 00:00:00
		###################################
passa_segon:	lbu $t0, 0($a0)				#$t0=SS
				addiu $t0, $t0, 1			#$t0=SS++
				li $t1, 60
				beq $t0, $t1, inc_minuts	#$i SS = 60 incrementa MM
				sb$t0,0($a0)				#Escriu SS++
				jr $ra
				
inc_minuts:		sb $zero, 0 ($a0)			#Escriu SS = 0		
				lbu $t0, 1($a0)				#$t0=MM
				addiu $t0, $t0, 1			#$t0=MM++
				li $t1, 60
				beq $t0, $t1, inc_hores		#$i MM = 60 incrementa HH
				sb$t0,1($a0)				#Escriu MM++
				jr $ra
			
inc_hores:		sb $zero, 1 ($a0)			#Escriu MM = 0		
				lbu $t0, 2($a0)				#$t0=HH
				addiu $t0, $t0, 1			#$t0=HH++
				li $t1, 24
				beq $t0, $t1, inc_dia		#$i HH = 24 dia nou
				sb$t0,2($a0)				#Escriu HH++
				jr $ra	

inc_dia:		sb $zero, 2($a0)			#Escriu HH=0
				jr $ra

				
				########################################################## 
                # Subrutina que guarda un nuevo valor de reloj en memoria
                # Entrada: $a0 con la dirección de la variable reloj
				# 		   $a1 con el valor de las horas
				#		   $a2 con el valor de los minutos
				#          $a3 con el valor de los segundos
                ########################################################## 

inicialitza_rellotge_alt:
				sb $a1, 2($a0)
				sb $a2, 1($a0)
				sb $a3, 0($a0)
				jr $ra				

                ########################################################## 
                # Subrutina que imprime el valor del reloj
                # Entrada: $a0 con la dirección de la variable reloj
                ########################################################## 

imprime_reloj:  move $t0, $a0
                la $a0, cad_asteriscos  # Dirección de la cadena
                li $v0, 4               # Código de print_string
                syscall

                la $a0, cad_horas       # Dirección de la cadena
                li $v0, 4               # Código de print_string
                syscall

                lbu $a0, 2($t0)         # Lee el campo HH
                li $v0, 1               # Código de print_int
                syscall

                la $a0, cad_minutos     # Dirección de la cadena
                li $v0, 4               # Código de print_string
                syscall

                lbu $a0, 1($t0)         # Lee el campo MM
                li $v0, 1               # Código de print_int
                syscall

                la $a0, cad_segundos    # Dirección de la cadena
                li $v0, 4               # Código de print_string
                syscall

                lbu $a0, 0($t0)         # Lee el campo SS
                li $v0, 1               # Código de print_int
                syscall

                la $a0, cad_asteriscos  # Dirección de la cadena
                li $v0, 4               # Código de print_string
                syscall
                jr $ra

                ########################################################## 
                # Subrutina que imprime los segundos calculados
                # Entrada: $a0 con los segundos a imprimir
                ########################################################## 

imprime_s:      move $t0, $a0
                la $a0, cad_asteriscos  # Dirección de la cadena
                li $v0, 4               # Código de print_string
                syscall


                la $a0, cad_reloj_en_s  # Dirección de la cadena
                li $v0, 4               # Código de print_string
                syscall

                move $a0, $t0           # Valor entero a imprimir
                li $v0, 1               # Código de print_int
                syscall

                la $a0, cad_asteriscos  # Dirección de la cadena
                li $v0, 4               # Código de print_string
                syscall
                jr $ra
                
                ########################################################## 
                # Subrutina que incrementa el reloj en una hora
                # Entrada: $a0 con la dirección del reloj
                # Salida: reloj incrementado en memoria
                # Nota: 23:MM:SS -> 00:MM:SS
                ########################################################## 
                
pasa_hora:      lbu $t0, 2($a0)         # $t0 = HH
                addiu $t0, $t0, 1       # $t0 = HH++
                li $t1, 24
                beq $t0, $t1, H24       # Si HH==24 se pone HH a cero
                sb $t0, 2($a0)          # Escribe HH++
                j fin_pasa_hora
H24:            sb $zero, 2($a0)        # Escribe HH a 0
fin_pasa_hora:  jr $ra
