
def getFactors(n):
    originalNumber = n
    factorsSet = set()
    # factorsSet.add(1)
    i = 1
    while i <= n:
        quotient = int(originalNumber/i)
        reminder = originalNumber % i
        if not reminder:
            factorsSet.add(i)
            factorsSet.add(quotient)
            n = quotient
        i = i+1

    return sum(sorted(list(factorsSet))[:-1])

def findAmicablePairs(num):
    for i in range(1, num+1):
        sum1 = getFactors(i)
        sum2 = getFactors(sum1)
        if (i< sum1 and  i == sum2):
            print("Sum1 ",sum1)
            print(" ", i , " and ",sum1," are Amicable Pairs" )


findAmicablePairs(2000)