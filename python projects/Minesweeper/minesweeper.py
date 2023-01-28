# -*- coding: utf-8 -*-
"""
Created on Tue Jan 12 12:27:29 2021

student: eran helvitz
Assignment no.6
program : minesweeper.py
"""
import random
class MSSquare:
    def __init__(self , has_mine = False , hidden = True , neighbor_mines = 0 ):
        
        self.__has_mine = has_mine
        self.__hidden = hidden
        self.__neighbor_mines = neighbor_mines
        
    @property
    def has_mine(self):
        """return if Hidden or not"""
        return self.__has_mine # True if hidden
    
    @has_mine.setter
    def has_mine(self,value):
        """if chosen to become visable turns hidden to False"""
        self.__has_mine = value #now not hidden
    
    @property
    def hidden(self):
        """return if Hidden or not"""
        return self.__hidden # True if hidden
    
    @hidden.setter
    def hidden(self,bool):
        """if chosen to become visable turns hidden to False"""
        self.__hidden = bool #now not hidden
    
    @property
    def neighbor_mines(self):
        """return amount of neighbor mines"""
        return self.__neighbor_mines
    
    @neighbor_mines.setter
    def neighbor_mines(self,num):
        """when chosent to become visable will be assigned to amount of neighbour mines"""
        self.__neighbor_mines = num

def print_board(board  , list_open , over ,mines_no , test):
    "the function print the board"
    first_line = "+---"
    for i in range(len(board)):
        print("",first_line*(len(board)) , end="+\n")
        print(i+1 , end="|")            
        for j in range(len(board)):
            if over == True: # if user lose
                if board[i][j].hidden == False and board[i][j].has_mine != True:
                    print("",board[i][j].neighbor_mines ,end="")
                    print( " |",end="")
                    continue
                    
                if board[i][j].has_mine == True: # if its mine
                    print(" x",end="")
                    print( " |",end="")
                    continue
                     
            else:
                if [i , j] in list_open and board[i][j].has_mine != True: # 
                    print("",board[i][j].neighbor_mines ,end="")
                    print( " |",end="")
                    continue
            print( "   |",end="")
        print()
    print("",first_line*(len(board)) , end="+\n")
    for i in range(1,len(board)+1):
        print("  ", i, end="")
    print("\n")
    if over == False :
        if test == False:
            return list_open
        print(f"{len(board)**2-len(list_open)-mines_no } still hidden. Keep trying!" )
    return list_open

def set_mines( board , mines_no):
    '''the function sets mine in board randmoly acord the user mine selct'''
    count = 0  
    while count < mines_no:       
        r,col = random.randint(0 ,len(board)-1 ) ,random.randint(0 ,len(board)-1 )       
        # Place the mine, if it doesn't already have one
        if board[r][col].has_mine == True:
            continue           
        else: 
            board[r][col].has_mine = True
            count += 1
    return board
# Function for setting up the other grid values
def set_values(board):
    ''' Loop for counting each cell value
    if neer each cell have a mine close ,  value.neighbor_mines +1'''
    for r in range(len(board)):
        for col in range(len(board)):
            counter = 0
            # Skip, if it contains a mine
            if board[r][col].has_mine == True:
                continue
            # Check up  
            if r > 0 and board[r-1][col].has_mine == True:
                counter +=1             
            # Check down    
            if r < len(board)-1  and board[r+1][col].has_mine == True:
                counter +=1              
            # Check left
            if col > 0 and board[r][col-1].has_mine == True:
                counter +=1
            # Check right
            if col < len(board)-1 and board[r][col+1].has_mine == True:
                counter +=1
            # Check top-left    
            if r > 0 and col > 0 and board[r-1][col-1].has_mine == True:
                counter +=1
            # Check top-right
            if r > 0 and col <len(board)-1 and board[r-1][col+1].has_mine == True:
                counter +=1
            # Check below-left  
            if r < len(board)-1 and col > 0 and board[r+1][col-1].has_mine == True:
               counter +=1
            # Check below-right
            
            if r < len(board)-1 and col < len(board)-1 and board[r+1][col+1].has_mine == True:
                counter +=1
            board[r][col].neighbor_mines=counter
    return board
       

def neighbours(r , col  , board ,list_open):
    '''Recursive function to display all zero-valued neighbours  
    # If the cell already not visited'''
        
    if not [r , col] in list_open and board[r][col].has_mine !=True:
        board[r][col].hidden = False
        list_open.append([r , col]) # Display it to the user
        if board[r][col].neighbor_mines == 0:
            # Recursive calls for the neighbouring cells
            if r > 0:
                neighbours(r-1, col, board ,list_open)                
            if r < len(board)-1:
                neighbours(r+1, col, board ,list_open)               
            if col > 0:
                neighbours(r, col-1,  board ,list_open)               
            if col < len(board)-1:
                neighbours(r, col+1,  board ,list_open)                   
            if r > 0 and col > 0:
                neighbours(r-1, col-1,  board ,list_open)            
            if r > 0 and col < len(board)-1:
                neighbours(r-1, col+1, board , list_open ) 
            if r < len(board)-1 and col > 0:
                neighbours(r+1, col-1,  board  , list_open)      
            if r < len(board)-1 and col < len(board)-1:
                neighbours(r+1, col+1, board , list_open)     
        # If the cell is not zero-valued                  
    if board[r][col].has_mine != True and not [r , col] in list_open:
        board[r][col].hidden = False
        list_open.append([r, col])
    return list_open

 
def main():
    test = False
    start = True
    while start :
        start = False
        n = int(input("Enter size (between 4-->9) : "))
        if n < 4 or n > 9 :
            print("between 4-->9")
            start = True
            continue
        mines_no = int(input("Enter number of mines (no more than twice the size!): " ))
        if mines_no > 18 or mines_no < 1:
            print(f"between 1-->{2*n}")
            start = True
            continue

        
    board = [[MSSquare() for y in range(n)] for x in range(n)] 
    board =set_mines( board , mines_no )
    board = set_values(board)
    over = False
    list_open=[] # list of Visible cells
    
    while not over: # while user not selcted mine 
        list_open = print_board(board  ,list_open , over  ,mines_no , test) # board print
        if len(list_open) == (n*n)-mines_no: 
            break
        test = True
        # Input from the user
        inp = input("Enter your choice (row -space- column) : ").split()
        if len(inp) < 2 or len(inp) >2:
            print( "**need 2 numbers")
            continue
        
        inp[0] = int(inp[0])
        inp[1] = int(inp[1])
        
        if (inp[0] > len(board) or inp[0] < 0) or (inp[1] > len(board) or inp[1] < 0):
            print("wrong value")
            continue 
        
        r = inp[0]-1
        col = inp[1]-1 
        
        if board[r][col].hidden == False : # If cell already been flagged
            print("allready visible ")
            continue
            
            
        # If landing on a mine --- GAME OVER    
        if board[r][col].has_mine == True:
            over = True
            continue
        
        # If landing on a cell with 0 mines in neighboring cells
        elif board[r][col].neighbor_mines == 0:
            list_open = neighbours(r , col , board ,list_open )
            continue
        # If selecting a cell with atleast 1 mine in neighboring cells  
        else:   
            list_open.append([r , col])
            board[r][col].hidden = False
            continue
        
        # Check for if game over 
    if over == True:
        print_board(board , list_open ,over ,mines_no , test)
        print("You hit a mine. You lose...")
    if len(list_open) == (n*n)-mines_no:
        print("Congratulations you won !!!!")
main()  
       