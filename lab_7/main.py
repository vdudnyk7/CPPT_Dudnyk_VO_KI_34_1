import sys

def is_shaded(i, j, n):
    """
    Перевіряє, чи належить елемент [i][j] заштрихованій області.
    """
    return (i >= j and i + j <= n - 1) or (i <= j and i + j >= n - 1)

# Ввід розміру матриці
n = int(input("Введіть розмір матриці n: "))

# Ввід символу-заповнювача
filler = input("Введіть символ-заповнювач: ")

# Перевірка введення
if len(filler)==0:
    print("Не введено символ заповнювач")
    sys.exit(1)
if len(filler)>1:
    print("Введено забагато символів")
    sys.exit(1)

fillchar=filler[0]

# Створюємо зубчастий список
arr=[]
for i in range(n):
    row=[]
    for j in range(n):
        if is_shaded(i,j,n):
            row.append(fillchar)
    arr.append(row)

# Виведення матриці
for i in range(n):
    idx=0
    for j in range(n):
        if j==0 or j==n-1:
            print('@',end=" ")
        elif is_shaded(i,j,n):
            print(arr[i][idx],end=" ")
            idx+=1
        else:
            print(" ",end=" ")
    print()
