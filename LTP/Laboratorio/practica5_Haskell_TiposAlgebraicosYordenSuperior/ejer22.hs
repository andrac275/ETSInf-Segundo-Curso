module Ejer22 where
  --Ejercicio 22
  data Tree a = Void | Branch a (Tree a) (Tree a) deriving Show

  arbol= Branch  5 (Branch 12 Void Void) Void

  countProperty::(a->Bool)-> (Tree a) -> Int
  countProperty x (Void) = 0
  countProperty x (Branch y left right)
    | x y == True = 1 + countProperty x (left) + countProperty x (right)
    | otherwise = countProperty x (left) + countProperty x (right)