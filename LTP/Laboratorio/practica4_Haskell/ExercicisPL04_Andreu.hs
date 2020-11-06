module ExercicisPL04_Andreu where
  import Data.Char
  import Factorial  --Importe el modul Factorial, que te una funcio per a lexercici 7.

--L'ordre dels exercicis es el recomanat en la presentacio.

  --Exercici 3: Diu el major de dos nombres
  exe3:: Int -> Int -> Int
  exe3 a b = if a < b then b else a
  --Exercici3 alternativa amb guardes
  exe3':: Int -> Int -> Int
  exe3' a b
    | a < b = b
    | a > b = a

  --Exercici6: Torna el resto de una divisio. El "modul"
  exe6:: Int -> Int -> Int 
  exe6 a b
    | a < b = a
    | a >= b = exe6 (a - b) b

  --Exercici1: Torna el nombre de caracters que hi ha entre dos caracters
  exe1:: Char -> Char -> Int
  exe1 a b = if a == b then 0
             else abs (ord a - ord b) - 1
  --Exercici1 alternativa amb guardes
  exe1':: Char -> Char -> Int
  exe1' a b
    | a == b = 0
    | otherwise = abs(ord a - ord b) - 1

  --Exercici2: Sumatori des d'un valor enter a un altre.
  exe2:: Int -> Int -> Int
  exe2 a b
    | a == b = a
    | a > b = a + exe2 (a - 1) b
    | a < b = b + exe2 a (b - 1)

  --Exercici4: Dir si un any es transpas.
  exe4:: Int -> Bool
  exe4 x = ((mod x 4 == 0)&&(mod x 100 /= 0)) || (mod x 400 == 0)

  --Exercici5: Calcular els dies de un mes, tenint en compter el mes i l'any passat. (Per febrer, que varia)
  exe5:: Int -> Int -> Int
  exe5 m a    --m=mes a=any
    | m == 1 || m == 3 || m == 5 || m == 8 || m == 10 || m == 12 = 31
    | m == 2 = if exe4 a then 29
                else 28
    | m == 4 || m == 6 || m == 7 || m == 9 || m == 11 = 30
    |otherwise = (-1) -- Si claves un mes que no esta entre 1 i 12 torna -1 com si fora un error.

-- Exercici 7: Calcula la suma dels facotrials des de factorial 1 fins a factorial n (que es el que passem)
-- En la linia3 he importat el modul Factorial per a poder gastar la funció aci.
  exe7:: Int -> Int
  exe7 0 = fact 0
  exe7 n = fact n + fact (n-1)

-- En la linia3 he importat el modul Factorial per a poder gastar la funció aci.
-- Una altra manera de fer-ho amb guardes.
  exe7':: Int -> Int
  exe7' n
    | n == 0 = fact 0
    | otherwise = fact n + fact (n-1)