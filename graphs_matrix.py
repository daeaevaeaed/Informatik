from string import ascii_uppercase

class Vertex:
    def __init__(self,name:str="", value:int = 10000) -> None:
        self.marked = False
        self.visited = False
        self.name = name
        self.value = value
        self.incoming_edges = 0
        self.outgoing_edges = 0

class Graph:
    def __init__(self, matrix=[], vertices=[]) -> None:
        self.matrix: list[list] = matrix
        self.vertices_list = [Vertex(name=str(index)) for index, row in enumerate(self.matrix[0])]

    def vertices(self,) -> list:
        return self.vertices_list 

    def edges(self,) -> list[tuple[int]]:
        """returns list of tuples with (from, to, weight)"""
        hl = []
        for i, y in enumerate(self.matrix):
            for j, x in enumerate(y):
                if j!=i and x[0]!=0:
                    hl.append((i, j, x[1]))
        return hl
        # return [[(i, x[0]) for j, x in enumerate(y) if j!=i and x[0]!=0] for i, y in enumerate(self.matrix)]

    def degree(self, vertex:Vertex) -> int:
        degree = 0
        for y in self.matrix[int(vertex.name)]:
            if y[0] != 0:
                degree += 1
        return degree

    def weight(self, edge: tuple) -> int:
        """edge is tuple with (y, x) Koordinates"""
        return self.matrix[edge[0]][edge[1]][1]

    def insert_vertex(self,vertex:Vertex):
        vertex.name = int(self.vertices_list[-1].name) + 1
        self.vertices_list.append(vertex)
        self.matrix.append([])
        for y in self.matrix:
            (0, 0)
            self.matrix[-1].append((0,0))

    def insert_edge(self,edge:tuple[int]):
        """edge must be tuple of y, x, weight (from, to, weight)"""
        self.matrix[edge[0]][edge[1]] = (1,edge[2])

    def delete_vertex(self, vertex):
        for y in self.matrix:
            y.pop(int(vertex.name))
        self.matrix.pop(int(vertex.name))

    def delete_edge(self,edge):
        """edge must be tuple of y, x, weight (from, to, weight)"""
        for index, edge in enumerate(self.matrix[edge[0]]):
            if edge == (edge[1], edge[2]):
                self.matrix[edge[0]].pop(index)


g1_m = [
    [(0, 0), (1, 2), (0, 0)], 
    [(1, 2), (0, 0), (0, 0)], 
    [(1, 4), (1, 3), (0, 0)]
    ]

g1 = Graph(matrix=g1_m)
g1.insert_edge((0,2,3))
print(g1.degree(g1.vertices()[0]))
print(g1.matrix)
v_new = Vertex()
g1.insert_vertex(v_new)
print(g1.matrix)