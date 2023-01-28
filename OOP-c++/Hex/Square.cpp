
#include "Square.h"


Square::~Square() {
    delete value;

}

ostream& operator <<(ostream& out , const Square& g){
    // print each square
    out << g.value;
    return out;
}

void Square:: setValue( const char* l) {
    char c = *l;
    *value = c;
    isEmpty = 0;
}

int Square:: getIsVisited() const{
    return isVisited;
}

void Square:: setIsVisited(){
    isVisited = 1;
}

char Square:: getValue() const{
    return *value;
}

int Square::isSquareEmpty() const{
    return isEmpty;
}

void Square::notVisted()  {
    isVisited = 0;
}

