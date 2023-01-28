# -*- coding: utf-8 -*-
"""
Created on Wed Nov 18 16:53:06 2020

student: Eran Helvitz
Assignment no. 3
program : prime_density.py
"""
import random
import math
from math import e

def is_prime(num):
    '''return True if num is a prime number '''
    if(num%2 == 0):
        if (num != 2):
            return  False
    for i in range(3,int(math.sqrt(num))+1, 2):
        if (num%i == 0):
            return False
    return True


def prime_density(max):
    '''Lottery of numbers in a  range 
    if return true from def is_prime , adding the num to a list '''
    lst=[]
    i=0
    count=0
    while i < 100000: # just 100000 numbers 
        n_ran=random.randint(max/2,max)
        i+=1
        if is_prime(n_ran)==True:
            lst.append(n_ran)
    count=len(lst)/100000
    return(count)
        

def main():
    x=10**9
    print("density of primes: %.4f" %prime_density(x))
    print("expected density:  %.4f "  %(1/(math.log(x,e))))
main()