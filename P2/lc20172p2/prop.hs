--------------------------------------------------------------------------------
-- Universidad Nacional Autónoma de México, Facultad de Ciencias              --
-- Lógica Computacional 2017-2                                                --
-- Práctica 2: Gramáticas / Sintaxis y semántica del lenguaje PROP.           --
--                                                                            --
-- Descripción:                                                               --
-- Módulo para trabajar con la sintaxis y semántica de las expresiones del    --
-- lenguaje PROP.                                                             --
--                                                                            --
--  Profesor Pilar Selene Linares Arévalo                                     --
--  Ayudante Uriel Agustín Ochoa González                                     --
--  Ayudante Diego Murillo Albarran                                           --
-- Ayud.Lab. Manuel Soto Romero                                               --
-- Ayud.Lab. Víctor Zamora Gutiérrez                                          --
--------------------------------------------------------------------------------

module PROPP2 where

-- Sinónimo para representar estados
type Estado = (VarP, Booleano)

-- Gramática para contantes lógicas
data Booleano = V | F deriving (Show,Eq)

-- Gramática para variables proposicionales
data VarP = A|B|C|D|E|G|H|I|J|K|L|M|N|O|P|Q|R|S|T|U|W|X|Y|Z deriving (Show, Eq)

-- Gramática para representar átomos
data Atomo = Var VarP | Cte Booleano deriving (Eq)

-- Gramática para representar a los operadores binarios.
data OpBin = Conj | Disy | Impl | Syss deriving(Eq)

-- Gramática para representar expresiones del lenguaje Prop.
data Prop = FA Atomo
          | Neg Prop
          | Op Prop OpBin Prop deriving(Eq)

-- Hace parte de la familia Show al tipo Atomo.
instance Show Atomo where
   show (Var v) = show v
   show (Cte b) = show b

-- Hace parte de la familia Show al tipo OpBin.
instance Show OpBin where
   show (Conj) = " ∧ "
   show (Disy) = " ∨ "
   show (Impl) = " => "
   show (Syss) = " <=> "

-- Hace parte de la familia Show al tipo Prop.
instance Show Prop where
   show (FA a) = show a
   show (Neg p) = "¬(" ++ show p ++ ")"
   show (Op p o q) = "(" ++ show p ++ show o ++ show q ++ ")"

-- Función que realiza la sustitución simultánea dada una lista con las 
-- sustituciones.
sustSimult :: Prop -> [(VarP, Prop)] -> Prop
sustSimult (FA x) l = (aux1 x l)
sustSimult (Neg x) l = (Neg (sustSimult x l))
sustSimult (Op x z y) l = (Op (sustSimult x l) z (sustSimult y l))

aux1 :: Atomo -> [(VarP, Prop)] -> Prop
aux1 (Var x) l = busca x l
aux1 (Cte x) l = (FA (Cte x))

busca :: VarP -> [(VarP, Prop)] -> Prop
busca x l
  | l == [] = (FA (Var x))
  | x == fst(head l) = snd(head l)
  | otherwise = busca x (tail l)

-- Función que regresa el valor de interpretación aplicada a una función en los
-- estados recibidos como parámetros.
interpreta :: Prop -> [Estado] -> Booleano
interpreta x l = aux3(aux4 x l)

aux4 :: Prop -> [Estado] -> Prop
aux4 (FA x) l = aux2 x l
aux4 (Neg x) l = Neg (aux4 x l)
aux4 (Op x z y) l = Op (aux4 x l) z (aux4 y l)

aux2 :: Atomo -> [Estado] -> Prop
aux2 (Var x) l = busca1 x l

busca1 :: VarP -> [Estado] -> Prop
busca1 x l
  | l == [] = error "No se encontró la interpretación para la variable"
  | x == fst(head l) = (FA (Cte (snd(head l))))
  | otherwise = busca1 x (tail l)

aux3 :: Prop -> Booleano
aux3 (Neg (FA (Cte V))) = F
aux3 (Neg (FA (Cte F))) = V
aux3 (Op (FA (Cte V)) Conj (FA (Cte V))) = V
aux3 (Op _ Conj _) = F
aux3 (Op (FA (Cte F)) Disy (FA (Cte F))) = F
aux3 (Op _ Disy _) = V
aux3 (Op (FA (Cte V)) Impl (FA (Cte F))) = F
aux3 (Op _ Impl _) = V
aux3 (Op (FA (Cte V)) Syss (FA (Cte V))) = V
aux3 (Op (FA (Cte F)) Syss (FA (Cte F))) = V
aux3 (Op _ Syss _) = F


-- Función que dada una fórmula, elimina: dobles negaciones, disyunciones o 
-- conjunciones de la misma variable y disyunciones con constantes.
simplifica :: Prop -> Prop
simplifica (FA x) = (FA x)
simplifica (Neg x) = negado(x)
simplifica (Op x z y) = auxSimplifica x z y

auxSimplifica :: Prop -> OpBin -> Prop -> Prop
auxSimplifica x z y
  | (z == Conj && x == y) = x
  | (z == Disy && x == y) = x
  | (z == Conj && x == (FA (Cte V))) = y
  | (z == Conj && y == (FA (Cte V))) = x
  | (z == Disy && x == (FA (Cte V))) = x
  | (z == Disy && x == (FA (Cte F))) = y
  | (z == Disy && y == (FA (Cte F))) = x
  | (z == Disy && y == (FA (Cte V))) = y
  | otherwise = (Op x z y)

negado :: Prop -> Prop
negado (FA x) = (Neg (FA x))
negado (Neg x) = x
negado (Op x z y) 
  | (z == Disy) = (Op (simplifica(Neg x)) Conj (simplifica(Neg y)))
  | (z == Conj) = (Op (simplifica(Neg x)) Disy (simplifica(Neg y)))
  | otherwise = (Op x z y)

-- Función que regresa la forma normal negativa de una expresión
formaNN :: Prop -> Prop
formaNN (FA x) = (FA x)
formaNN (Neg x) = simplifica (Neg (formaNN x))
formaNN (Op x z y) = simplifica (nnaux x z y)

nnaux :: Prop -> OpBin -> Prop -> Prop
nnaux x z y
  | (z == Impl) = (Op (Neg (formaNN x)) Disy (formaNN y))
  | (z == Syss) = (Op (Op (Neg (formaNN x)) Disy (formaNN y)) Conj (Op (Neg (formaNN y)) Disy (formaNN x)))
  | otherwise = (Op x z y)

-- Función que regresa la forma normal conjuntiva de una expresión
formaNC :: Prop -> Prop
formaNC x = aux5(simplifica(formaNN x))

aux5 :: Prop -> Prop
aux5 (FA x) = (FA x)
aux5 (Neg x) = simplifica (Neg x)
aux5 (Op x Conj y) = (Op (aux5 x) Conj (aux5 y))
aux5 (Op x Disy y) = (distr (aux5 x) (aux5 y))

distr :: Prop -> Prop -> Prop
distr (FA x) (FA y) = (Op (FA x) Disy (FA y))
distr (Neg x) (FA y) = (Op (Neg x) Disy (FA y))
distr (FA x) (Neg y) = (Op (FA x) Disy (Neg y))
distr (Neg x) (Neg y) = (Op (Neg x) Disy (Neg y))
distr (Op x z y) (FA r)
  | (z == Disy) = (Op (distr x y) Disy (FA r))
  | (z == Conj) = (Op (distr x (FA r)) Conj (distr y (FA r)))
distr (FA r) (Op x z y)
  | (z == Disy) = (Op (Op x z y) Disy (FA r))
  | (z == Conj) = (Op (distr x (FA r)) Conj (distr y (FA r)))
distr (Op x z y) (Neg r)
  | (z == Disy) = (Op (distr x y) Disy (Neg r))
  | (z == Conj) = (Op (distr x (Neg r)) Conj (distr y (Neg r)))
distr (Neg r) (Op x z y)
  | (z == Disy) = (Op (distr x y) Disy (Neg r))
  | (z == Conj) = (Op (distr x (Neg r)) Conj (distr y (Neg r)))
distr (Op x z y) (Op w e r)
  | (z == Disy && e == Disy) = (Op (distr x y) Disy (distr w r))
  | (z == Disy && e == Conj) = (Op (distr w (Op x Disy y)) Conj (distr r (Op x Disy y)))
  | (z == Conj && e == Disy) = (Op (distr x (Op w Disy r)) Conj (distr y (Op w Disy r)))
  | (z == Conj && e == Conj) = (Op (distr x w) Conj (Op (distr x r) Conj (Op (distr y w) Conj (distr y r))))
-- Función que verifica si una fórmula es tautología
esTautologia :: Prop -> Booleano
esTautologia (FA (Cte V)) =  V
esTautologia (FA (Cte F)) =  F
esTautologia (FA (Var x)) = V
esTautologia (Neg (FA (Cte V))) = F
esTautologia (Neg (FA (Cte F))) = V
esTautologia (Neg (FA x)) = F

aux :: Prop -> Booleano
aux (Op (FA (Var x)) Disy (FA (Var y)))
  |x == y = V
  |otherwise = F




-- Función que decide si una fórmula es satisfacible
--esSatisfacible :: Prop -> Booleano
--esSatisfacible f =  V


