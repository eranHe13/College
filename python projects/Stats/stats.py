# -*- coding: utf-8 -*-
"""
Created on Thu Nov 26 17:41:56 2020

student: Eran Helvitz
Assignment no. 3
program : stats.py
"""

def isfloat(x):
    ''' Checks if the number is a decimal number by conditions'''
    list_conditions=[".","-","+",0,1,2,3,4,5,6,7,8,9]
    x=str(x)
    list_x=[]
    for i in range(len(x)):
        list_x.append(x[i])
  
    for i in range(len(list_x)): #convert nubmer to int and keep str to str
        if list_x[i]== "." or list_x[i]== "-" or list_x[i]== "+":
            continue
        if list_x[i].isalpha(): # if elem is alpha =stop
            return False
        list_x[i]=int(list_x[i])
   
    if list_x.count(".")>1 or list_x.count("+")>1 or list_x.count("-")>1:   
        return False
   
    if list_x[0] == "+" or list_x[0]== "-" or type(list_x[0])==int:   
        if not type(list_x[-1]) == int :
            return False
    
    if list_x[0]=="+" or list_x[0]=="-": 
        if  type(list_x[1]) != int:
            return False
        
    for i in range(1,len(list_x)): # chech if elem in list is one of the condition list if not stop
        if i not in list_conditions:
             return False
        if list_x[i] == "+" or list_x[i] == "-":
            return False
    if list_x[0]=="0" and list_x[1]=="." and list_x[-1]=="0": 
        return False
    
    if list_x[-1] == "." or list_x[0]==".":
        return False
   
    else:
        return True

    
def string_to_list(num_space):
    '''The function receives a string of numbers and returns a list containing the numbers,
       Each number that enters the list goes to the isfloat 
       function and it returns it if the number is correct'''
    num_space=num_space.split()
    for i in range(len(num_space)):
        if isfloat(num_space[i]) == False:
            return None    
            
    for i in range(len(num_space)):
        num_space[i]=float(num_space[i])
    return num_space
    
def mean(x):
    '''The function receives a list of numbers and returns their average'''
    return (sum(x)/len(x))

def sd(list_num):
    '''The function receives a list of numbers and returns their standard deviation'''
    average=mean(list_num)
    count=0
    for i in range(len(list_num)):
        count+=(list_num[i]-average)**2
    count=(1/len(list_num)*count)**0.5
    return count
        
def median(list_num):
    '''The function receives a list of numbers and returns their median'''
    list_num.sort()
    for i in range(len(list_num)):
        list_num[i] = int(list_num[i])
    if len(list_num)%2==1:
        median1=list_num[((len(list_num)+1)//2)-1]
        return median1
    else:
        median0=int((len(list_num)/2)-1)
        median1=int(((len(list_num)+2)/2)-1)
        median2=(list_num[median0]+list_num[median1])/2
        return median2
        
def stats(file):
    '''The function receives a string of numbers and returns 
    their median, mean and standard deviation 
    if one of the numbers is incorrect the function returns illegal input'''
    with open("numbers.txt","r") as fd:
        file_check = fd.read()

    stats_file=open("./output/stats.txt","w")
    p=string_to_list(file_check)
    if p == None:
        stats_file.write("illegal input")
    else: 
        i=""
        i="mean :  " + str("{:.2f}".format(mean(p))) + "\n"
        i+="standard deviation :  " + str("{:.2f}".format(sd(p))) + "\n"
        i+="median:  " +  str("{:.2f}".format(median(p))) + "\n"
        stats_file.write(i)
        
    stats_file.close()
   
def main():
    file="./input/number1.txt"
    stats(file)
main()
