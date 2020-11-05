module Exercicis where
    import Data.Char
	--L'ordre dels exercicis es el recomanat en la presentacio.
    exe3:: Int -> Int -> Int
    exe3 a b = if a < b then b else a

    exe6:: Int -> Int -> Int 
    exe6 a b
        | a < b = a
        | a >= b = exe6 (a - b) b

    exe1:: Char -> Char -> Int
    exe1 a b = if a == b then 0
               else abs (ord a - ord b) - 1

    exe2:: Int -> Int -> Int
    exe2 a b
        | a == b = a
        | a > b = a + exe2 (a - 1) b
        | a < b = b + exe2 a (b - 1)

    exe4:: Int -> Bool
    exe4 x = ((mod x 4 == 0)&&(mod x 100 /= 0)) || (mod x 400 == 0)

    exe5:: Int -> Int -> Int
    exe5 m a    --m=mes a=any
        | m == 1 || m == 3 || m == 5 || m == 8 || m == 10 || m == 12 = 31
        | m == 2 = if exe4 a then 29
                    else 28
        | m == 4 || m == 6 || m == 7 || m == 9 || m == 11 = 30
        |otherwise = (-1)

    fact :: Int -> Int
    fact 0 = 1
    fact n = n * fact(n - 1)

    exe7:: Int -> Int
    exe7 0 = fact 0
    exe7 n = fact n + fact (n-1) 