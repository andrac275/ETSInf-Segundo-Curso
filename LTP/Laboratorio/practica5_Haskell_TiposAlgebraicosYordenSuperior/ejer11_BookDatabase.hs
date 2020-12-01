module BookDatabase where
  import Data.Char

--La sesio 2 se pot fer exercicis del 11 al 16 i la ampliacion 20,21,22
--Pruebas antes de la practica...
  type Person = String
  type Book = String
  type Database = [(Person,Book)]

  exampleBase::Database
  exampleBase = [("Alicia", "El nombre de la rosa"),("Juan","La hija del canibal"),
    ("Pepe","Odesa"),("Alicia","La ciudad de las bestias")]

--obtain donat una database i una persona torna els llibres que te eixa persona
  obtain :: Database -> Person -> [Book]
  obtain dBase thisPerson
    =[book | (person,book)<-dBase, person == thisPerson]

  --Exercici 11. Completar lo anterior amb borrow i return'
  --borrow es per a fer un prestamo, es a dir... donada una database, un llibre i una persona, actualitza la database
  borrow:: Database -> Book -> Person -> Database
  borrow dBase thisBook thisPerson = dBase ++ [(thisPerson,thisBook)]

  --return es que una persona torna un llibre i torna la db actualitzada
  return':: Database -> (Person,Book) -> Database
  return' dB (per,bk) = [(person, book)|(person,book) <- dB, not(per == person && bk == book)] 

  
  --Pruebas Andreu
  data Color = Groc Int | Blau String
  prueba:: Color -> String
  prueba (Groc x) = "275"
  prueba (Blau x) = "Kide is blue"