
def check_col_direction(sodoku):
  check_map = {n:0 for n in range(1,10)}

  for fix_index in range(9):
    check_map = {n:0 for n in range(1,10)}
    for move_index in range(9):
      check_map[sodoku[move_index][fix_index]]+=1
    for k, v in check_map.items():
      if v == 0:
        return False
  return True


def main():
  input_count = int(input())
  for i in range(input_count):
    sodoku = []
    #print(i)
    for _ in range(9):
      sodoku.append(list(map(int, input().split())))
    #print(*sodoku, sep='\n')
    check_col_direction(sodoku)
if __name__ == "__main__":
  main()
