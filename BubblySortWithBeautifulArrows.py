

###############################################
#     Use monospace font when executing!!     #
###############################################

def swap(list, x, y):   
    # swap function. 
    # Print: List, swapped numbers with Arrows, 
    # Return: new list 
    #
    # x is the index of the left word, 
    # y the index of the right word, 
    # therefore always: x < y
    #
    # The printed result is achieved by an array of symbols,
    # initiated is an "empty" field with spaces,
    # thats filled step-by-step with the arrows.

    right, left = 1, 1
    for element in list[:x]:
        left += 2 + len(str(element))
    for element in list[:y]:
        right += 2 + len(str(element))
    lengthLeftWord =  len(str(list[x]))
    lengthRightWord = len(str(list[y]))
    adjust = int((lengthRightWord - lengthLeftWord)/2) 
    # adjust is for arriving in the middle of the number
    toLeft, toRight = right + int(lengthRightWord/2), left + int(lengthLeftWord/2)
    # the following is for starting in the middle of the number
    left, right = left + int(lengthLeftWord/2) + adjust , right + int(lengthRightWord/2) + adjust

    # initiating array with number of columns = position of right number + 5
    # number of rows = pos. of right word + buffer if "swap-word" is very long
    array = [" "] * (right + 5)
    for i in range(len(array)):
        array[i] = [" "] * (right + lengthRightWord + lengthRightWord + 10)

    # will be true if both arrows arrived at their goal
    breakingNextRound = False

    print(list)
    for column in range(len(array)):
        # toRight and toLeft are indices for going through the lines,
        # replacing the right spaces through slashes
        # toRight goes to the right every line,
        # toLeft goes to the left every line.
        # When either arrives in the middle of the number,
        # they will henceforth print a "|", an arrow pointing downwards

        if breakingNextRound:
            array[column][toRight] = "|"
            array[column][toLeft] = "|"
            array[column + 1][toRight] = "V"
            array[column + 1][toLeft] = "V"
            break
        
        # if the y -> x arrow has arrived at its goal
        if toLeft <= left:
            array[column + 1][toLeft] = "V"
        else:
            array[column][toLeft] = "/"
            toLeft -= 1

        # if the x -> y arrow has arrived at its goal
        if toRight >=right:
            array[column][toRight] = "|"
        else:
            array[column][toRight] = "\\"
            toRight += 1

        # proving if both arrows arrived
        if toLeft <= left and toRight >= right:
            breakingNextRound = True
        

    # printing all lines with content and skipping the rest
    for line in array:
        if "".join(line).replace(" ", "") == "":
            continue
        for element in line:
            print(element, end = "")
        print("")

    # The real part of the swap
    list[x], list[y] = list[y], list[x]
    print(list)
    print("-------------------------------------------")
    return list



# main part

###########
list = [32, 324, 234,2342, 124, 2, 16346346, 123, 31245, 69420, 1337, 42, 420, 551889, 1432018]
###########


# Bubble Sort
# Goes through every element i and proves if there is a
# element j to the right that is smaller than the
# current element i
for i in range(len(list)):
    for j in range(i + 1, len(list)):
        if list[i] > list[j]:
            list = swap(list, i, j)
            
print(list)
