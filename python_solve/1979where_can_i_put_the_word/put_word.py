def check_len(K, puzzle):
  pattern_count = int(0)

  for row in puzzle:
    count = 0
    for index, el in enumerate(row):
      if index == len(row)-1:
        if el == 1:
          count+=1
        if count_k(count, K):
          pattern_count+=1
      elif el == 1:
        count+=1
      elif el == 0:
        if count_k( count, K):
          pattern_count+=1
        count = 0
    #print(row, "el: ",el,"index:", index, "count:", count, "res:", res)
  print("Res: ",pattern_count)
  return pattern_count

def count_k( count, K):
    if count == K:
      #print("true in K")
      return True
    else:
       return False




def main():
  input_count = int(input())
  for i in range(input_count):
    N, K = map(int, input().split(" "))
    puzzle = []
    print(i," 번째")
    for _ in range(N):
      row = list(map(int,input().split(" ")))
      puzzle.append(row)
      print(row)
    check_len(K, puzzle)
    print("~~~~~~~~~~~~~~~~~~")



if __name__ == "__main__":
  main()
