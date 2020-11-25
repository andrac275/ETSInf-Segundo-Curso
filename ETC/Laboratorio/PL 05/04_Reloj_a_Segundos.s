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
                # Segmento de c�digo
                ##########################################################

                .globl __start
                .text 0x00400000

__start:	#############################################
				#Arrancar rellotge a l'hora 15:25:50
					la $a0, reloj
					li $a1, 15
					li $a2, 25
					li $a3, 50
					jal inicialitza_rellotge_alt
				
				la $a0,reloj
				jal imprime_reloj	#Imprimeix Rellotge

				###############################################				
				#TornaRellotgeEnSegon. El rellotge anterior el passa a segons.
				la $a0 reloj
				jal tornaRellotgeEnSegon
				move $a0, $v0
				
				jal imprime_s	#Esta funcio predefinida torna el nombre de segons
								# se una manera mes guapeta.

salir:          li $v0, 10              # C�digo de exit (10)
                syscall                 # �ltima instrucci�n ejecutada
                .end

				########################################################## 
                # Subrutina que devuelve el reloj en segundos
                # Entrada: $a0 con la direcci�n de la variable reloj
				# Salida:  $v0 con el valor de los segundos
                ########################################################## 
				
tornaRellotgeEnSegon:
				li $t3, 60			# Factor from h->m and m->s
				lb $t0, 2($a0)		# Load hours
				li $t1, 0x1f		# Clean hours
				and $t0, $t0, $t1
				mult $t0, $t3		# Hours to minutes
				mflo $t2
				lb $t0, 1($a0)		# Load minutes
				li $t1, 0x3f		# Clean minutes
				and $t0, $t0, $t1
				add $t0, $t0, $t2	# minutes = hours * 60 + minutes
				mult $t0, $t3		# Minutes to seconds
				mflo $t2
				lb $t0, 0($a0)		# Load seconds
				li $t1, 0x3f		# Clean seconds
				and $t0, $t0, $t1
				add $v0, $t0, $t2	# seconds = minutes * 60 + seconds
				jr $ra	
				
				########################################################## 
                # Subrutina que guarda un nuevo valor de reloj en memoria
                # Entrada: $a0 con la direcci�n de la variable reloj
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
                # Entrada: $a0 con la direcci�n de la variable reloj
                ########################################################## 

imprime_reloj:  move $t0, $a0
                la $a0, cad_asteriscos  # Direcci�n de la cadena
                li $v0, 4               # C�digo de print_string
                syscall

                la $a0, cad_horas       # Direcci�n de la cadena
                li $v0, 4               # C�digo de print_string
                syscall

                lbu $a0, 2($t0)         # Lee el campo HH
                li $v0, 1               # C�digo de print_int
                syscall

                la $a0, cad_minutos     # Direcci�n de la cadena
                li $v0, 4               # C�digo de print_string
                syscall

                lbu $a0, 1($t0)         # Lee el campo MM
                li $v0, 1               # C�digo de print_int
                syscall

                la $a0, cad_segundos    # Direcci�n de la cadena
                li $v0, 4               # C�digo de print_string
                syscall

                lbu $a0, 0($t0)         # Lee el campo SS
                li $v0, 1               # C�digo de print_int
                syscall

                la $a0, cad_asteriscos  # Direcci�n de la cadena
                li $v0, 4               # C�digo de print_string
                syscall
                jr $ra

                ########################################################## 
                # Subrutina que imprime los segundos calculados
                # Entrada: $a0 con los segundos a imprimir
                ########################################################## 

imprime_s:      move $t0, $a0
                la $a0, cad_asteriscos  # Direcci�n de la cadena
                li $v0, 4               # C�digo de print_string
                syscall


                la $a0, cad_reloj_en_s  # Direcci�n de la cadena
                li $v0, 4               # C�digo de print_string
                syscall

                move $a0, $t0           # Valor entero a imprimir
                li $v0, 1               # C�digo de print_int
                syscall

                la $a0, cad_asteriscos  # Direcci�n de la cadena
                li $v0, 4               # C�digo de print_string
                syscall
                jr $ra
                
                ########################################################## 
                # Subrutina que incrementa el reloj en una hora
                # Entrada: $a0 con la direcci�n del reloj
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
