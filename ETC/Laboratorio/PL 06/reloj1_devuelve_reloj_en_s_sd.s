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
				li $a1, 1	#HH
				li $a2, 0	#MM
				li $a3, 0	#SS
				jal inicialitza_rellotge_alt

				la $a0, reloj
                jal devuelve_reloj_en_s_sd
				
				add $a0,$zero,$v0
				jal imprime_s
             
salir:          li $v0, 10              # Código de exit (10)
                syscall                 # Última instrucción ejecutada
                .end



				########################################################## 
                # Subrutina que devuelve el reloj en segundos
				# Utilizar sll y sumas
                # Entrada: $a0 con la dirección de la variable reloj
				# Salida:  $v0 con el valor de los segundos
                ########################################################## 
				
devuelve_reloj_en_s_sd:
				#Multiplicar HH per 3600
				lbu $t0, 2($a0)		# $t0 == HH
				sll $v0, $t0,11		#$v0 = HH x 2^11
				sll $t1, $t0,10		#$t1 = HH x 2^10
				addu $v0, $v0, $t1	#$v0= HH x (2^11 + 2^10)
				sll $t1,$t0, 9		#$t1 = HH x 2^9
				addu $v0, $v0, $t1	#$v0= HH x (2^11 + 2^10 + 2^9)
				sll $t1,$t0, 4		#$t1 = HH x 2^9
				addu $v0, $v0, $t1	#$v0= HH x (2^11 + 2^10 + 2^9 + 2^4)

				#Multiplicar MM per 60
				lbu $t0, 1($a0)		# $t0 == MM
				sll $t1, $t0,5		#$v0 = MM x 2^5
				sll $t2, $t0,4		#$t1 = MM x 2^4
				addu $t1, $t1, $t2	#$v0= MM x (2^5 + 2^4)
				sll $t2,$t0, 3		#$t1 = MM x 2^3
				addu $t1, $t1, $t2	#$v0= MM x (2^5 + 2^4 + 2^3)
				sll $t2,$t0, 2		#$t1 = MM x 2^2
				addu $t1, $t1, $t2	#$v0= MM x (2^5 + 2^4 + 2^3 + 2^2)

				#No cal multiplicar SS
				lbu $t0, 0 ($a0)	#$t0=SS
				
				#Sumarho tot
				addu $v0, $v0, $t1	#$v0= HH x 3600 + MM x 60
				addu $v0, $v0, $t0	#$v0= HH x 3600 + MM x 60 + SS
				jr $ra

				########################################################## 
                # Subrutina que devuelve el reloj en segundos
                # Entrada: $a0 con la dirección de la variable reloj
				# Salida:  $v0 con el valor de los segundos
                ########################################################## 
				
devuelve_reloj_en_s:
				li $t3, 60			# Factor from h<->m and m<->s
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
