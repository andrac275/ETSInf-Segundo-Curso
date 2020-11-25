module ExercicisPL05_Sesion1 where
  import Data.Char

--La sesio un se pot fer exercicis del 1 al 10 i el 17,18,19
--Exercici 1
  decBin::Int -> [Int]
  decBin x = if x < 2 then [x]
              else (x `mod` 2) : decBin (x `div` 2)

--Exercici 2
  binDec::[Int]->Int
  binDec (x:[])=x
  binDec (x:y) = x + binDec y * 2

--Exercici 3
  divisors :: Int -> [Int]
  divisors x = [y | y <- [1..x], (mod x y) == 0]

--Exercici 4
  member :: Int -> [Int] -> Bool
  member a [] = False
  member a (x:xs)
    | a == x = True
    | otherwise = member a xs

  member' :: Int -> [Int] -> Bool
  member' a [] = False
  member' a (x:xs) = if a==x then True
                      else member a xs

--Exercici 5
  isPrime :: Int -> Bool
  isPrime x = if (length (divisors x)) > 2 then False
               else True

  primes:: Int -> [Int]
  primes x = take x (filter isPrime [1..])

--Exercici 6. Selecciona parells
  --La funcio even torna True si un nombre es parell i False si no ho es.
  --si pose even 1 torna False i si posse even 100 torna True
  selectEven :: [Int] -> [Int]
  selectEven (x:xs)
  --Si el tamany de la llista es 0 (nomes te un membre) i la pos x es parella, la afegeix.
    |  length xs == 0 && even x = [x]
  --Si el tamany de la llista es 0 (nomes te un membre) i la pos x NO es parella, ho deixa buit.
    |  length xs == 0 = []
  --Si el tamany es major a 0 (te 2 o mes elements... Recursio)
    --Tornar una llista amb el primer element que ES parell i la resta.
    |  even x = x : (selectEven xs)
    --Tornar la resta de la llista perque el primer no es parell.
    |  otherwise = selectEven xs

  --Selecciona pares
  selectEven'::[Int]->[Int]
  selectEven' [] = []
  selectEven' (x:xs) = filter even (x:xs)
  --Selecciona impares
  selectOdd'::[Int]->[Int]
  selectOdd' [] = []
  selectOdd' (x:xs) = filter odd (x:xs)

--Exercici 7. Seleccionar nombres de les posicions parelles.
  selectEvenPos::[Int]->[Int]
  selectEvenPos [] = []
  selectEvenPos (x:[]) =[x]
  selectEvenPos (x:y:xs) = x : selectEvenPos xs

--Exercici 8
  ins:: Int -> [Int] -> [Int]
  ins a [] = [a]
  ins a (x:xs) = if a < x then a : (x:xs)
                  else x : ins a xs 

  iSort::[Int]->[Int]
  iSort [] = []
  iSort (x:[]) = [x]
  iSort (x:xs) = ins x (iSort xs)

--Las funciones map y filter
--Exercici 9
  doubleAll::[Int]->[Int]
  doubleAll [] = []
  doubleAll (x:xs) = map (2*) (x:xs)

--Exercici 10
{-El siguiente map' i filter' es el que hi ha per defecte pero jo l'he
escrit de nou per practicar. No es el que se demana en l'exercici.-}
  map'::(a->b)->[a]->[b]
  map' f [] = []
  map' f (x:xs) = f x : map' f xs

  filter' :: (a->Bool) -> [a] -> [a]
  filter' f [] = []
  filter' f (x:xs) = if f x then x:filter' f xs 
                      else filter' f xs

--Exercici 17
  repeated :: Int -> [Int] -> Int
  repeated a [] = 0
  repeated a (x:xs) = if a == x then 1 + repeated a xs
                       else repeated a xs

--Exercici 18
  concat'::[[a]]->[a]
  concat' [] = []
  concat' (x:[])=(x)
  concat' (x:y:xs)  = x ++ concat' (y:xs)

--Exercici 19
  square:: Int -> Int
  square x = x*x
  {-Paso a paso... De dentro para fuera...
  Primero: filter even [1..10] devuelve una lista con los pares del 1 al 10
  es decir: [2,4,6,8,10]
  Segundo: map square (filter even  [1..10]) devulve toda la lista anterior
  elevada al cuadrado cada elemento: [4,16,36,64,100] 
  Finalmente:  sum(map square (filter even  [1..10])) devuelve la suma
  de todos los elementos de la lista anterior: 220
  -}
  --sum(map square (filter even  [1..10]))
  exe19=sum(map square (filter even  [1..10]))
--Ara si que fare el map i el filter amb llistes intencionals per practicar
  map'':: (a -> b) -> [a] -> [b]
  map'' f (x:xs) = [f a | a <-(x:xs)]

  filter''::(a->Bool)->[a]->[a]
  filter'' f (x:xs) = [ a | a <-(x:xs), f a]

{-------------------------------------------------------------------------}

{-Las siguientes funciones son pruebas mias. no forman parte de la practica-}
--Devuelve tamaño de una lista
  length'::[a]->Int
  length' [] = 0
  length' (x:xs) = 1 + length' (xs)

--Devuelve el elemento de una lista. Es como la funcion (!!) predefinida
  posLista :: [a]->Int->a
  posLista (x:xs) a = if a == 0 then x
                       else posLista (xs) (a-1)

--Concatena dos listas, es como la funcion predefinida (++)
  concLista::[a]->[a]->[a]
  concLista [] [] = []
  concLista (x:xs) [] = (x:xs)
  concLista [] (x:xs) = (x:xs)
  concLista (x:xs) (y:ys) = x:concLista (xs) (y:ys)

--square es potencia de 2. x*x
--square:: Floating a =>a->a 
--square x = x*x

--Devuelve True si un numero es par. Es como la predefinida 'even'
  esPar::Int->Bool
  esPar x = ((x `mod` 2) == 0) == True

--Devuelve el resto de la division. Es como `mod`
  resto:: Int -> Int -> Int
  resto a b 
    | a < b = a
    | a == b = 0
    | a > b = resto (a-b) b 