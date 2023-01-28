
/* READ ME
 * I did not use copy constructor in this program because there is no reason to copy the board
 * I did not use the default constructor because i did this program to work for any size .
 */


#ifndef HOMEWORK3_GAME_H
#define HOMEWORK3_GAME_H
#include <cstdio>
#include <iostream>
#include<cstring>

#include "Square.h"
#include <sstream>
using namespace std;


class Game {
public:
    Game(int sizeIn);
    void setSquare(int i , int j);
    void printBoard() const;
    friend class Square;
    void playGame();
    char playerTurn() const;
    int checkSquare( int i , int j);
    int checkWin(int row , int col   );
    void setNotVisited();
    int getStartSquare();
    ~Game();

private:
    enum {empty = 'O' , B = 'B' , R = 'R' };
    Square** board;
    int counter;
    int size ;




};

#endif //HOMEWORK3_GAME_H
