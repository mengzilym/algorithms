class QuickFindUF(object):

    def __init__(self, array_len):
        self.length = array_len
        self.id = list(range(array_len))
        self.count = array_len

    def count(self):
        return self.count

    def union(self, p, q):
        p_id = self.id[p]
        q_id = self.id[q]
        if p_id != q_id:
            for idx in range(self.length):
                if self.id[idx] == p_id:
                    self.id[idx] = q_id
            self.count -= 1

    def connected(self, p, q):
        return self.id[p] == self.id[q]

if __name__ == "__main__":
    array_len = int(input("Please enter a integer greater than 0: "))
    uf = QuickFindUF(array_len)
    while 1:
        input_str = input()
        if input_str == "quit":
            break
        p = int(input_str)

        input_str = input()
        if input_str == "quit":
            break
        q = int(input_str)
        if not uf.connected(p, q):
            uf.union(p, q)
            print(p, q)
