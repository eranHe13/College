

/* READ ME
 * I did not use copy constructor in this program because there is no reason to copy the square
 */
#ifndef HOMEWORK3_SQUARE_H
#define HOMEWORK3_SQUARE_H
#include <cstdio>
#include <cstring>
#include <iostream>
using namespace std;

class Square {
public:
    Square(){
        value = new char[1] ;
        *value = 'O';
        isEmpty = 1;
        isVisited = 0;

    }
    ~Square();
    int isSquareEmpty() const;
    void setValue(const char* l);
    char getValue() const;
    int getIsVisited() const;
    void setIsVisited();
    friend ostream& operator <<(ostream& out, const Square& dt);
    void notVisted() ;


private:

    char* value ;
    int isEmpty;
    int isVisited;



};
ostream& operator <<(ostream&, const Square&);


#endif //HOMEWORK3_SQUARE_H
