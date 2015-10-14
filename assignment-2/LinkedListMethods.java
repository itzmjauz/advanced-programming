
//aantal elementen in een singly linked list
int numberOfElements(Node l) {
    if (l == null) {
        return 0;
    }
    return 1 + numberOfElements(l.next);
}

//recursief vinden of een waarde in een lijst zit
boolean find (Node l, int x) {
   if (l == null) {
       return false;
   }
   return x == l.data || find(l.next, x);     //deze volgorde is belangrijk, de efficientste!
}

/*Onze lijst heeft een DOUBLY linked list, waarbij elke nieuwe entry gelijk op de juiste plek er gesorteerd
 *ingezet moet worden, dus niet achteraf sorteren.
 * Elke non-empty list heeft een reference naar het huidige element (current node)
 */
 
 //Nieuwe Node inserten tussen n en n.next, die dus terug verwijst naar n en vooruit verwijst
 //naar n.next
 new Node(5,n,n.next)
 
 n.next = next.prior = new Node(5,n,n.next);      //verwijzingen van zowel n als n.next updaten
 
 



