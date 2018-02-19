--------------------------------------------------------------------------------
-- Universidad Nacional Autónoma de México, Facultad de Ciencias              --
-- Lógica Computacional 2017-2                                                --
-- Práctica 2: Gramáticas / Sintaxis y semántica del lenguaje PROP.           --
--                                                                            --
-- Descripción:                                                               --
-- Módulo para trabajar con árboles cuyas hojas son los únicos nodos con      --
-- información.                                                               --
--                                                                            --
--  Profesor Pilar Selene Linares Arévalo                                     --
--  Ayudante Uriel Agustín Ochoa González                                     --
--  Ayudante Diego Murillo Albarran                                           --
-- Ayud.Lab. Manuel Soto Romero                                               --
-- Ayud.Lab. Víctor Zamora Gutiérrez                                          --
--------------------------------------------------------------------------------

module ARBOLESP2 where

-- Gramática para representar a los árboles binarios
data AB a = Hoja a
          | Mkt (AB a) (AB a) deriving (Show, Eq)

-- Función que regresa el número de hojas del árbol.
nh :: AB a -> Int
nh t = if t == 1 then 1

-- Función que regresa el número de nodos internos del árbol.
nni :: AB a -> Int
nni t = error "Función no implementada"

-- Función que determina si un elemento está contenido en el árbol.
elemA :: Eq a => AB a -> a -> Bool
elemA t e = error "Función no implementada"

-- Función que toma un árbol y regresa una lista con los elementos en la forma
-- inorder.
inorderA :: AB a -> [a]
inorderA t = error "Función no implementada"

-- Función que toma un elemento y lo agrega al árbol.
agregaHoja :: AB a -> a -> AB a
agregaHoja t e = error "Función no implementada"

-- Función que dado un árbol y una función, aplica la misma a cada elemento del
-- árbol.
mapA :: AB a -> (a -> b) -> AB b
mapA t f = error "Función no implementada"

-- Función que regresa la profundidad del árbol.
profundidad :: AB a -> Int
profundidad t = error "Función no implementada"
