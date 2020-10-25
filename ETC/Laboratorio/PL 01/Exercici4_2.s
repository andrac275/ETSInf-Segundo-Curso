#Exercisi 4. Apartat 2
.globl __start
.text 0x00400000
__start:
li $t0, 100

ori $t1, $zero, 0x64
andi $t1, $zero, 0x64
addi $t1, $zero, 0x64

ori $t1, $zero, 100
#addi $t1, 0x64, $zero	#Esta peta
xori $t1, $zero, 100
andi $t1, $zero, 100
addi $t1, $zero, 100