#include "Game.h"
int main() {
    Game* G = new Game(11);
    G->playGame();
    delete  G;
    return 0;
}

