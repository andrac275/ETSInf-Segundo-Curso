                .globl __start
                .text 0x00400000

__start:        li.s $f0, -1.5        # Constante -1.5 
                li.d $f2, 78.325        # Constante 78.325

                li $t0, 0x7F800000   # Menos infinito
                mtc1 $t0, $f0        # Envío a $f12
                li $t1, 0x7F8003A0    # Not a Number (NaN)
                mtc1 $t1, $f20        # Envío a $f20

                .end
