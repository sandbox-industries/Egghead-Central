digits = ['', 'one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine', 'ten', 'eleven', 'twelve', 'thirteen', 'fourteen', 'fifteen', 'sixteen', 'seventeen', 'eighteen', 'nineteen']
tens = ['','','twenty', 'thirty', 'forty', 'fifty', 'sixty', 'seventy', 'eighty', 'ninety']
hundreds = 'hundred'
one_thousand = 'onethousand'

# simplify problem by handling numbers less than 20 individually
digits_sum = sum([len(x) for x in digits])

# iterate through the numbers, creating their string representation
val = 0
for i in range(20, 1000):
    word = ''
    h = i//100              # hundreds
    t = (i-i//100*100)//10  # tens
    d = i%10                # digits
    
    # handle 100's
    if h > 0:
        word = word + str(digits[h]) + hundreds
        if t > 0 or d > 0:
            word = word + 'and'
    
    # handle 10's special cases
    if t == 1:
        word = word + str(digits[t*10+d])
    # handle all other 10's
    else:
        word = word + str(tens[t])
        # handle digits
        word = word + str(digits[d])
    # count the number if letters used
    val += len(word)


print(val + digits_sum + len(one_thousand))
