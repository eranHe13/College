# -*- coding: utf-8 -*-
"""
Created on Wed Dec  9 20:56:48 2020
student: eran helvitz
Assignment no. 4
program: grades.py
"""

def Dictionary_idname(file):
    '''The function receives a string of IDs and names and converts them to a dictionary of 
    which the ID is the key and the name is the value'''
    file=file.split("\n")
    lst_id=[]
    lst_name=[]
    for i in range(len(file)):# split id and name to lists
        j=file[i].find(" ")
        lst_id.append(file[i][0:j])
        lst_name.append(file[i][j:])
    
    if len(lst_name) != len(lst_id):
        print("The amount of names and IDs are  not the same")
        return None
    dic_id_name={lst_id[i]:lst_name[i] for i in range(len(lst_id))}
    return dic_id_name
        

def Dictionary_idgrade(file):
    '''The function receives a string of IDs and grades and converts them to a dictionary
    that the ID is the key  and the List of scores is the value'''
    file=file.split("\n")
    if file[-1]=="":
        del file[-1]
    lst_id=[]
    lst_grade=[]
    for i in range(len(file)): # adding each id to list
        j=file[i].find(" ")
        lst_id.append(file[i][0:j])
        lst=[]
        grade=""
        for p in range(j,len(file[i])): # adding each greades to list
            if file[i][p].isspace():
                lst.append(grade)
                grade=""
            else:
                grade+=file[i][p]
        del lst[0]        
        lst.append(grade)        
        lst_grade.append(lst)     
    for i in range(len(lst_grade)):
        for j in range(len(lst_grade[i])):
            if lst_grade[i][j].isnumeric():
                lst_grade[i][j]=int( lst_grade[i][j])
    lst_average = average(lst_grade , lst_id)
    dic_id_grade={lst_id[i]:lst_grade[i] for i in range(len(lst_id))}           
            
    return dic_id_grade , lst_average , lst_grade
        
        
def average(lst_grade , lst_id):
    ''' recive grades and ids and make dictionary - key= id , value = average(grades)'''
    dict1={}
    grade_average=[]
    if lst_grade[-1]=="":
        del lst_grade[-1]
    
    for i in range(len(lst_grade)):
        if lst_grade[i][-1]=="":
            del lst_grade[i][-1]
        a=(sum(lst_grade[i])/len(lst_grade[i]))
        grade_average.append(a)
        dict1[lst_id[i]]=sum(lst_grade[i])/len(lst_grade[i])
    grade_average.sort(reverse=True)
    return grade_average ,dict1
    

def print_name_average(dict_idname , *dict_idgrades ):
    ''' recive Dictionary(id:name) , and lists of grades and Dictionary(id:average)(id:grades)
    and she print name - average for each student'''
    sort_grade= dict(sorted(dict_idgrades[1][1].items() , key =lambda item:item[1], reverse=True))
    dict_name_average= {}
    for key ,value in sort_grade.items():
        if key in sort_grade and key in dict_idname:
             dict_name_average[key]=[dict_idname[key] , value ]
    for value in dict_name_average.values():
        print(value[0] ,"%0.2f" %value[1])
    most_grades=[]
    for value in dict_idgrades[0]:
        most_grades.append(dict_idgrades[0][value])
    dict_mostgrade={}
    for i in range(len(most_grades)):# check common grades
        for j in range(len(most_grades[i])):
            if most_grades[i][j] in dict_mostgrade:
                dict_mostgrade[most_grades[i][j]]+=1
            else:
                dict_mostgrade[most_grades[i][j]]=1
    list_most_grades=[]
    for value in dict_mostgrade.keys(): # print the most common grades 
        if dict_mostgrade[value]==max(dict_mostgrade.values()):
            list_most_grades.append(value)
    print("Most common grades : " ,list_most_grades )
    
def get_common_elements(lst):
    ''' get list of lists and print the common graeds'''
    all_grades1=[]
    all_grades2=[] 

    for i in range(len(lst)): # split gardes to 2 lists
        for j in range(len(lst[i])):
            if i%2==0:
                all_grades1.append(lst[i][j])  
            else:
                all_grades2.append(lst[i][j])  
    all_grades1=set(all_grades1)
    all_grades2=set(all_grades2)
    most_grades=list(all_grades1&all_grades2)
    print(most_grades)
    print("Grades that more than one student got : " )
    for i in most_grades:
        print(i ,end="," )
    
    
def main():
    with open("./students.txt", "r") as fd:
        id_name=fd.read()
    with open("./grades.txt" , "r") as fd:
        id_grade=fd.read()
    
    dict_idname=Dictionary_idname(id_name)
    dict_idgrades= Dictionary_idgrade(id_grade)
    print_name_average(dict_idname , *dict_idgrades )
    get_common_elements(dict_idgrades[2])
   
main()