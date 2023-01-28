# -*- coding: utf-8 -*-
"""
Created on Thu Dec  3 22:21:39 2020

student: eran helvitz
Assignment no. 4
program: vigenere.py
"""

def add_letters(s1,s2): 
    
    '''The function receives two letters separately and returns letter from
    the sum of those two letters If the letter length is bigger than 1 or the function 
    received something other than the a letter, it will return None'''
    group1={i:chr(ord("a")+i) for i in range(26)}
    if len(s1)==1 and len(s2)==1 and s1.isalpha() and s2.isalpha():
        s1=s1.lower()
        s2=s2.lower()
        n_letter= (ord(s1)-ord("a")+ord(s2)-ord("a"))%26
        return group1[n_letter]
    return None


def add_strings(s1,s2):
    '''The function receives two strings consisting of Latin letters and returns
    a new string The function will send a letter from s1 and s2 to function add_letters and make a new
    string, If the input string is not composed solely 
    of Latin letters the function will return None'''
    n_str=""
    for i in range(len(s1)): #sending letter from string and from key to add_letters and recive new letter
        if s1[i].isalpha() and s2[i].isalpha():
            letters=add_letters(s1[i],s2[i])
            if letters == None :
                return None
            else:
                n_str+=letters
    return n_str


def vigenere_encryp(s,k):
    '''The function encrypts a string by a key it gets a string and a 
    key and returns the encryption result'''

    s1=""
    for i in k: # if key is not just letters
        if not i.isalpha():
            return None
    for i in s: # adding to a new string just letters
        if i.isalpha():
            s1+=i
    if len(s1)>len(k): 
        k=k*((len(s)//len(k))+1)

    return add_strings(s1,k)


def remove_letters(s1,s2):
    '''The function decodes a string by a key
    it receives two letters and returns the letter indicating the difference
    between the letters'''
    if len(s1)>1 or len(s2)>1:
        return None
    if not s1.isalpha() or  not s2.isalpha():
        return None
    s1=s1.lower()
    s2=s2.lower()
    group={chr(ord("a")+i):i for i in range(26)}
    group1={i:chr(ord("a")+i) for i in range(26)}
        
    return group1[  (group[s1] - group[s2])      %26 ]


def vigenere_decrypt(w,k):
    '''The function receives a string and key and returns the decoding result using 
    the key if the key does not consist of Latin letters only will be returned None'''
    for i in k:
        if not i.isalpha():
            return None
    k=k*((len(w)//len(k))+1)       
    new_w=""
    for i in range(len(w)):
        new_w+=remove_letters(w[i],k[i])
   
    return new_w
     

def main():
    user_input=input("")
    if user_input == "e":
        user_input1 = input("enter a File name :  ")
        user_key = input("enter a Encryption key :  ")
        try:
            f=open(user_input1,"r")
            file_in=f.read()
            f.close()
        except IOError:
            print("file not accessible")
            return
        user_input1=user_input1.replace(".txt", ".vig")
        file_open=open(user_input1,"w")
        file_open.write(vigenere_encryp(file_in,user_key))
        file_open.close()
        
    if user_input == "d":
        user_input1 = input("enter a File name :  ")
        user_key = input("enter a Encryption key :  ")
        try:
            f=open(user_input1,"r")
            file_in=f.read()
            f.close()
        except IOError:
            print("file not accessible")
            return
        print(vigenere_decrypt(file_in,user_key))

main()