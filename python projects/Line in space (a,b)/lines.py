# -*- coding: utf-8 -*-
"""
Created on Wed Jan 13 21:03:36 2021

student: eran helvitz
Assignment no.6
program : lines.py
"""


class Point:
    '''A point in space'''
    def __init__(self ,x = 0, y=0):
        try:    
            self.__x = float(x)
        except:
            raise ValueError("x coordinate must be number")
        try:
            self.__y =float(y)
        except:
            raise ValueError("y coordinate must be nnumber")
    def __str__(self):
        return "(%.2f" % self.x + "," + "%.2f)" % self.y
    
    @property
    def x(self):
        return self.__x
    
    @x.setter
    def x(self , value):
        if not isinstance(value, float) and not isinstance(value, int):
            raise ValueError("x coordinate must be number")
        self.__x = value
    
    @property
    def y(self):
        return self.__y
    
    @y.setter
    def y(self , value):
        if not isinstance(value, float) and not isinstance(value, int):
            raise ValueError("y coordinate must be number")
        self.__y = value



class Line:
    """Two point in space"""
    def __init__(self, p, q):
        if not isinstance(p, Point):
            raise ValueError("argument must be of type Point", p)
        if not isinstance(q, Point):
            raise ValueError("argument must be of type Point", q)
        self.__p = Point(p.x, p.y)
        self.__q = Point(q.x, q.y)

        
    def __str__(self):
        if self.is_vertical():
            return "x = %.2f" % self.p.x
        return "y = %.2fx + %.2f" % (self.slope(), self.y_intersect())

    @property
    def p(self):
        return self.__p

    @p.setter
    def p(self, p):
        self.__p = p

    @property
    def q(self):
        return self.__q

    @q.setter
    def q(self, q):
        self.__q = q
    
    
    def is_vertical(self): 
        '''If the line is vertical the function returns True'''
        if self.q.y - self.p.y != 0 and self.q.x -self.p.x == 0 :
            return True
        return False

    def slope(self):
        '''The function returns the line slope'''
        if self.is_vertical():
            return None
        return (self.q.y - self.p.y) / (self.q.x -self.p.x) 
            
    
    def y_intersect(self ):
        '''The function returns the point of intersection with an axis Y'''
        if self.is_vertical():
            return None
        return self.p.y - (self.slope() * self.p.x)
        
        
    def parallel(self, other):
        """return True if The two lines are parallel"""
        if not isinstance(other, Line):
            return False
        if self.is_vertical():
            return other.is_vertical()
        elif other.is_vertical():
            return False
        return self.slope() == other.slope()
       
        
        
        
    def equals(self, other):
        """return True if two given lines are equals."""
        if not isinstance(other, Line):
            return False
        if not self.parallel(other):
            return False
        if self.is_vertical():
            return self.__p.x == other.__p.x
        else:
            return self.y_intersect() == other.y_intersect()
        
        
    def intersection(self, other):
        """return intersection point between two lines"""
        if not isinstance(other, Line):
            return None
        if self.parallel(other):
            return None
        if self.is_vertical():
            return Point(self.__p.x, other.slope() * self.__p.x + other.y_intersect())
        if other.is_vertical():
            return Point(other.__p.x, self.slope() * other.__p.x + self.y_intersect())
        else:
            x = (other.y_intersect() - self.y_intersect()) / (self.slope() - other.slope())
            y = self.slope() * x + self.y_intersect()
            return Point(x, y)

def main():
    
    lines_list = []
    intersection_point = []
    input_file = open("./input1.txt", "r")
    lines_input = input_file.read().split("\n")
    if len(lines_input[-1]) == 0:
        del lines_input[-1]
    input_file.close()
    output_file = open("./output1.txt", "w")
    for i in range(len(lines_input)):
        lines = lines_input[i].split()
        if len(lines) != 4:
            output_file.write(
                f"Not enough data for line {i + 1}." + "\n")  
        else:
            try:
                p = Point(lines[0], lines[1])
                q = Point(lines[2], lines[3])
                l = Line(p, q)
                output_file.write(f"line {i + 1}: {l.__str__()}" + "\n")
                lines_list.append(l)
                for j in range(i):
                    if l.equals(lines_list[j]):
                        output_file.write(f"line {i + 1} is equal to line {j + 1}" + "\n")
                    if l.parallel(lines_list[j]):
                        output_file.write(f"line {i + 1} is parallel to line {j + 1}" + "\n")
                    else:
                        p_intersection = l.intersection(lines_list[j])
                        if p_intersection is not None:
                            output_file.write(f"line {i + 1} with line {j + 1}: ")
                            intersection_point.append(p_intersection)
                            output_file.write(str(p_intersection) + "\n")

            except ValueError as e:
                lines_list.append("Illegal line")
                output_file.write(f"Line {i + 1} error: " + str(e) + "\n")
        output_file.write("\n")
    output_file.close()
 
  
main()   
  
    
