module Ejer14_15_16 where
  import Data.Char


  data BinTreeInt = Void | Node Int BinTreeInt BinTreeInt deriving Show
  tree1= Void
  tree2= (Node 5 Void Void)
  tree3= (Node 5
    (Node 3 (Node 1 Void Void) (Node 4 Void Void))
    (Node 6 Void (Node 8 Void Void))
    )

--Exercici 14
  insTree:: Int-> BinTreeInt -> BinTreeInt
  insTree x (Void) = (Node x Void Void)
  insTree x (Node y left right)
    | x < y = Node y (insTree x left) right
    | otherwise = Node y left (insTree x right)

--Exercici 15
  creaTree::[Int]-> BinTreeInt
  creaTree [] = (Void)
  creaTree (x:[]) =  insTree x Void
  creaTree (x:xs) = insTree x (creaTree xs)

--Exercici 16
  treeElem::Int->BinTreeInt->Bool
  treeElem x (Void) = False
  treeElem x (Node y left right)
    | x == y = True
    | x > y = treeElem x (right)
    | otherwise = treeElem x (left)

--Exercici 21
  dupElem:: BinTreeInt -> BinTreeInt
  dupElem (Void) = (Void)
  dupElem (Node x Void Void) = (Node (2*x) Void Void)
  dupElem (Node x y z) = (Node (2*x) (dupElem(y)) (dupElem(z)))






{-

dupTree :: BinTreeInt -> BinTreeInt
dupTree Void = Void
dupTree (Node x left right) = Node (x * 2) (dupTree left) (dupTree right)


countProperty :: (Int -> Bool) -> BinTreeInt -> Int
countProperty _ Void = 0
countProperty f (Node x left right)
  | f x = 1 + countProperty f left + countProperty f right
  | otherwise = countProperty f left + countProperty f right
  -}