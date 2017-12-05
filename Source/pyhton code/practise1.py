import sys
import heapq

from graph import Graph
from vertex import Vertex


class emergency_vehcile(object):
    dict={}
    lst=[]
    lst1=[]
    dict1={}
    def __init__(self,type,zipcode,availability):
        self.type=type
        self.zipcode=zipcode
        self.availability=availability

    def get_vehcile__details(self):
        return self.availability +"no of vehciles of "+ self.type + "type is available at" + self.zipcode

    for num in range(self.availability):
        dict[zipcode]=lst.append(self.type)

    for num in range(self.availability):
        dict[zipcode]=lst1.append(self.availability)



class request(object):
    def __init__(self,type,zipcode):
        self.type=type
        self.zipcode=zipcode

"""creating dictionary to store emergency vechiles using zipcode"""


def dijkstra(aGraph, start, target):
    print '''Dijkstra's shortest path'''

    start.set_distance(0)

    unvisited_queue = [(v.get_distance(), v) for v in aGraph]
    heapq.heapify(unvisited_queue)

    while len(unvisited_queue):

        uv = heapq.heappop(unvisited_queue)
        current = uv[1]
        current.set_visited()


        for next in current.adjacent:

            if next.visited:
                continue
            new_dist = current.get_distance() + current.get_weight(next)

            if new_dist < next.get_distance():
                next.set_distance(new_dist)
                next.set_previous(current)
                print 'updated : current = %s next = %s new_dist = %s' \
                      % (current.get_id(), next.get_id(), next.get_distance())
            else:
                print 'not updated : current = %s next = %s new_dist = %s' \
                      % (current.get_id(), next.get_id(), next.get_distance())



        while len(unvisited_queue):
            heapq.heappop(unvisited_queue)

        unvisited_queue = [(v.get_distance(), v) for v in aGraph if not v.visited]
        heapq.heapify(unvisited_queue)


if __name__ == '__main__':

    g = Graph()

    g.add_vertex('a')
    g.add_vertex('b')
    g.add_vertex('c')
    g.add_vertex('d')
    g.add_vertex('e')
    g.add_vertex('f')

    g.add_edge('a', 'b', 7)
    g.add_edge('a', 'c', 9)
    g.add_edge('a', 'f', 14)
    g.add_edge('b', 'c', 10)
    g.add_edge('b', 'd', 15)
    g.add_edge('c', 'd', 11)
    g.add_edge('c', 'f', 2)
    g.add_edge('d', 'e', 6)
    g.add_edge('e', 'f', 9)
