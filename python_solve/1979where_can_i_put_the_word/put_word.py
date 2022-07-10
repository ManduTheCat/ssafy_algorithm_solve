def see_left(K, puzzle):
  pattern_count = 0
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
  return pattern_count

def count_k( count, K):
    if count == K:
      return True
    else:
       return False

def see_top(K, puzzle):
  pattern_count = 0
  count = 0
  N  = len(puzzle)
  for fix_index in range(N):
    count = 0
    for move_index in range(N):
      if move_index == N -1:
        if puzzle[move_index][fix_index] == 1:
          count+=1
        if count_k(count, K):
          pattern_count+=1
      elif puzzle[move_index][fix_index] == 1:
        count+=1
      elif puzzle[move_index][fix_index] == 0:
        if count_k(count, K):
          pattern_count+=1
        count = 0
  return pattern_count

def main():
  input_count = int(input())
  for i in range(input_count):
    N, K = map(int, input().split())
    puzzle = []
    for _ in range(N):
      row = list(map(int,input().split()))
      puzzle.append(row)
    left = see_left(K, puzzle)
    top = see_top(K, puzzle)
    print(f'#{i+1} {left+top}')


if __name__ == "__main__":
  main()
