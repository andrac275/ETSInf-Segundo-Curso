module Symetric where
  import Data.Char

--Exercici 12
  data Tree a = Leaf a | Branch (Tree a) (Tree a) deriving Show
  symetric:: Tree a -> Tree a
--Cas base, una unica fulla.
  symetric (Leaf x) = (Leaf x)
--Cas general, que tinga branques
{-Donades dos branques a i b, les torna canviades pero cridant recursivament
en cada branca... per si les branques tenen algo que ordenar per davall d'elles -}
  symetric (Branch a b) = Branch (symetric b) (symetric a)


