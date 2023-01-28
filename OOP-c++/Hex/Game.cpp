#include "Game.h"


Game::Game(int sizeIn):size(sizeIn)  {
    // constructor initializing the board and each square , also restart the counter turns
    board = new Square*[size];
    for(int i = 0; i<size ; i++){
        board[i] = new Square[size];
    }
    counter = 0;
}

Game::~Game() {
    //delete the board and each square
    for (int i = 0; i < size; i++) {
        delete[] board[i];
    }
    delete[] board;

}

int isValidInput(char* input){
    // check if the input is valid return 1 if true else -1
    // [A-K] & [1-11]
    if((strlen(input) > 3) || (strlen(input)<=1)){
        cerr << "Invalid move; the game awaits a valid move.\n";
        return -1;
    }
    if((input[0] < 65) || (input[0] > 75) ){
        cerr << "Invalid move; the game awaits a valid move.\n";
        return -1;
    }
    if (strlen(input) == 3) {
        if((input[1] != '1') ||(( input[2] != '0') & (input[2] != '1' ))){
            cerr << "Invalid move; the game awaits a valid move.\n";
            return -1;
        }
    }
    else{
        if((input[1] < 49 )|| (input[1] > 57) ){
            cerr << "Invalid move; the game awaits a valid move.\n";
            return -1;
        }
    }
    return 1;
    }

int checkQuit(char* input){
    // check if the user insert QUIT
    return strcmp(input , "QUIT");
}

int Game::checkSquare( int i , int j ){
    // check if the square the ask is empty
    if(board[i][j].isSquareEmpty() == 1){
        return 1;
    }
    else {
        cerr << "Invalid move; the game awaits a valid move.\n";
        return -1;}
}

int getLine( char* n ){
    // return line by int
    int line ;
    stringstream ss;
    ss << n[1];
    ss<<n[2];
    ss >> line;
    return line-1;}

unsigned int getColumn( const char*  c  ){
    // return column by int
    char letters[] = {'A' , 'B' , 'C' ,'D' ,'E' ,'F', 'G', 'H', 'I', 'J', 'K'};
    for ( unsigned int i = 0; i <strlen(letters) ; i++) {
        if(*c == letters[i]){
            return i;
        }
    }return 0;
}

void Game:: setSquare(int i , int j){
    // set the square for the correct player
    char c = playerTurn();
    board[i][j].setValue(&c);
}

int Game :: getStartSquare(){
    // get the square from first (line / column) to  check if win
    char player = playerTurn();
    if(player == R){//CHANGE
        for( int col = 0 ; col < size ; col++){
            if (board[0][col].getValue() ==  R){//CHANGE
                if(checkWin(0 , col) ==1){return 1;}
                else{setNotVisited(); }
            }
        }
    }
    else{
        for( int row = 0 ; row < size ; row++){
            if (board[row][0].getValue() ==  B){
                if(checkWin(row , 0 ) ==1  ){return 1;}
                else{setNotVisited(); }
            }
        }
    }
    return -1;
    }

int  Game:: checkWin(int row = 0 , int col = 0  ){
    // check recursions if the player get to the end (line / column ) in a row
    int isWin = 0;
    if ((col == size-1) &  (board[row][col].getValue() == B) ){return 1;}
    if ((row == size-1) &  (board[row][col].getValue() == R) ){return 1;}
    if((board[row][col].getIsVisited() == 0) & (board[row][col].getValue() == playerTurn()) ) {
        if ( col > 0 ) {
            board[row][col].setIsVisited();
            isWin =  checkWin(row, col-1);
            if(isWin == 1){return isWin;}
        }
        if(col< size-1){
            board[row][col].setIsVisited();
            isWin =  checkWin(row , col+1 );
            if(isWin == 1){return isWin;}
        }
        if(row > 0 ){
            board[row][col].setIsVisited();
            isWin =   checkWin(row-1 , col );
            if(isWin == 1){return isWin;}
        }

        if(row < size-1){
             board[row][col].setIsVisited();
            isWin = checkWin(row+1 , col  );
            if(isWin == 1){return isWin;}
        }

        if((row >0) & (col < size-1) ){
            board[row][col].setIsVisited();
            isWin = checkWin(row-1 , col+1 );
            if(isWin == 1){return isWin;}
        }

        if((row < size-1) & (col > 0) ){
            board[row][col].setIsVisited();
            isWin = checkWin(row+1 , col-1 );
            if(isWin == 1){return isWin;}
        }
    }

    return -1;
}

void Game ::  setNotVisited(){
    // after each check restart the squares to be unvisited
    for (int i = 0 ; i < size ;i ++ ){
        for (int j = 0; j < size; j++) {
            board[i][j].notVisted();
        }}
    }

void Game:: playGame(){
    // main func to control the game
    char input[10];
    int checkInput = 0 , i, j;
    string welcome_messege = "\nWelcome to the HEX game.\n"
                             "Board lines is between A --> K\n"
                             "Rows is between 1 --> 11\n"
                             "Correct move example--> A3 , <line><row>\n";
    cout << welcome_messege;
    printBoard();
    while(checkQuit(input)!= 0 ) {
        counter+=1;
        cout << playerTurn() << ":\n";
        while(checkInput != 1 ) {
            cin >> input;
            if( checkQuit(input) == 0 ){
                if(counter !=0){
                    cout << playerTurn() <<": QUIT" <<endl;
                    counter+=1;
                    printBoard();
                    cout << playerTurn() << " wins the game." <<endl;}
                    return;
                }

            checkInput = isValidInput(input);
            j = getColumn(&input[0]);
            i = getLine(input);
            if(checkInput ==1){checkInput = checkSquare(i,j);}
        }

        setSquare(i , j);
        printBoard();

        if(counter > (size*2)-2) {
            if( getStartSquare()  == 1){
                cout << playerTurn() << " wins the game." << endl;
                return;}
        }

        setNotVisited();
        checkInput = 0;
    }
}

char Game:: playerTurn() const{
    // return the current player
    return (counter%2 == 0)?R:B;
}

void Game:: printBoard() const{
    cout << "\n";
    int space= 1;
    for(int i =0 ; i<size ; i++){
        if((i!=0) ){
            for (int k = 0; k <i ; k++) {
                cout << ' '  ;
            }}
        for(int j = 0 ; j < size ;j++){
            cout << this->board[i][j];
            if(j != size-1){cout << ' ';}
    }
        space+=1;
        cout<<endl;
    }
}


