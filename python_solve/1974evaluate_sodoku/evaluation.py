def check_square(sodoku: list):
  check_map = {n: 0 for n in range(1, 10)}
  for start_row_index in range(0, 9, 3):
    for start_col_index  in range(0, 9, 3):
      check_map = {n: 0 for n in range(1, 10)}
      for row in sodoku[start_row_index: start_row_index + 3]:
        for el in row[start_col_index: start_col_index + 3]:
          check_map[el] += 1
      if not evaluation_map(check_map):
        return False
  return True

def check_col_direction(sodoku: list):
  check_map = {n: 0 for n in range(1, 10)}
  for fix_index in range(9):
    check_map = {n: 0 for n in range(1, 10)}
    for move_index in range(9):
      check_map[sodoku[move_index][fix_index]] += 1
    if not evaluation_map(check_map):
      return False
  return True

def check_row_direction(sodoku: list):
  check_map = {n: 0 for n in range(1, 10)}
  for row in sodoku:
    check_map = {n: 0 for n in range(1, 10)}
    for el in row:
      check_map[el] += 1
    if not evaluation_map(check_map):
      return False
  return True

def evaluation_map(map: dict):
  for k, v in map.items():
    if v == 0:
      return False
  return True

def main():
  input_count = int(input())
  for i in range(input_count):
    sodoku = []
    for _ in range(9):
      sodoku.append(list(map(int, input().split())))
    if(check_row_direction(sodoku) and check_col_direction(sodoku) and check_square(sodoku)):
      print(f'#{i + 1} {1}')
    else:
      print(f'#{i + 1} {0}')

if __name__ == "__main__":
  main()
