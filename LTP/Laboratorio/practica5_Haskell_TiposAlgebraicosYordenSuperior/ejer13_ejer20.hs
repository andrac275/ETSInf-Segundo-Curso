module Ejer13_20 where
  import Data.Char

--Exercici 13
  data Tree a = Leaf a | Branch (Tree a) (Tree a) deriving Show

  listToTree:: [a] -> Tree a
  listToTree (x:[]) = Leaf x
  listToTree (x:xs) = Branch (Leaf x) (listToTree xs)

  treeToList:: Tree a -> [a]
  treeToList (Leaf a) = [a]
  treeToList (Branch a b) = treeToList a ++ treeToList b

--Exercici 20
  numleaves::Tree a -> Int
  numleaves (Leaf x) = 1
  numleaves (Branch x y) = numleaves x + numleaves y

  