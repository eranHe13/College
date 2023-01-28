# -*- coding: utf-8 -*-
"""
Created on Sat Nov 28 14:55:37 2020


student: Eran Helvitz
Assignment no. 3
program : matrix.py
"""





def matrix_scalar_mult(matrix,scalar):  #כפר מטריצה בסקלר
    #The function receives a matrix and scalar and calculates the matrix of the product
    matrix = [[scalar*matrix[i][j] for j in range(len(matrix[i]))] for i in range(len(matrix))]
    return matrix

def matrix_add(matrix1,matrix2): 
    #A function that accepts two matrices and calculates the sum matrix
    matrix = [[matrix1[i][j]+matrix2[i][j] for j in range(len(matrix1[i]))] for i in range(len(matrix1))]
    
    return matrix

def matrix_mult(matrix1,matrix2):  
    #A function that receives two matrices and calculates the matrix of the product
    matrix=[[sum(matrix1[i][k]*matrix2[k][j] for k in range(len(matrix1[0]))) for j in range(len(matrix2[0])) ] for i in range(len(matrix1))]
   
    
    return matrix

def identy_matrix(n):  
    #the function receive a natural number and makeing the identy matrix in size of the numbe
    matrix=[[1 if elem == line else 0 for elem in range(n) ] for line in range(n)]

    return matrix

def matrix_polynom(polynom,matrix):
    '''The function receives a matrix and a polynom
    and calculates the result of the placement of the matrix in a polynom'''
    ide= identy_matrix(len(matrix))
    matrix_id = matrix_scalar_mult(ide,polynom[0])  
    matrix0=matrix_scalar_mult(matrix,polynom[1])
    matrix1=matrix_add(matrix_id, matrix0)
    final_matrix=matrix

    for r in range(2,len(polynom)): #sum of the polynom
        final_matrix=matrix_mult(final_matrix, matrix)
        matrix1=matrix_add(matrix1, matrix_scalar_mult(final_matrix, polynom[r]))
    return matrix1
   
    
    

def matrix_print(matrix,loc_file):
    '''The function receives a matrix and a link to a file and
    prints it to a file by matrix view'''

    file=open(loc_file, "w")
    string=""
    for i in range(len(matrix)): #print the matrix in new file
        string+= " "
        for j in range(len(matrix[0])):
            string+=("%7.2f" %(matrix[i][j]))
            string+=" "
        string+="\n"
    file.write(string)
    file.close

def main():
    '''The program receives a matrix and a polynomial from a file
        And calculates the result of the placement of the
        matrix in the polynomial
        And prints the result matrix in a new file'''
    with open("./input/matrix_input1.txt", "r") as fd:
        metrix_work = fd.read()
    metrix1=[]
    for line in metrix_work.splitlines(): #Convert input to matrix and polynom in lists
        newRow = []
        for elem in line.split():
            newRow.append(float(elem))
        metrix1.append(newRow)
    polynom = metrix1[-1]
    metrix = metrix1[:-1]
    loc_file="./output/matrix_output.txt"
    matrix_print(matrix_polynom(polynom,metrix),loc_file)
    
main()