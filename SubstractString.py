# assume both are positive
def subtract(s1, s2):
	# check which one is bigger
	if len(s1) > len(s2):
		firstBigger = True
	elif len(s1) < len(s2):
		firstBigger = False
	else:
		for i in range(len(s1)):
			if s1[i] > s2[i]:
				firstBigger = True
				break
			elif s1[i] < s2[i]:
				firstBigger = False
				break

	top, down = (s1, s2) if firstBigger else (s2, s1)
	print top, down

	# do subtract
	carry = 0
	ans = ''
	i, j = len(top) - 1, len(down) - 1
	while i >= 0:
		ni = int(top[i]) # the digit of top number
		if j >= 0:
			nj = int(down[j]) # the digit of down number
			if ni + carry >= nj:
				ans = str(ni + carry - nj) + ans
				carry = 0
			else:
				ans = str(ni + carry - nj + 10) + ans
				carry = -1
		else:
			# just output top number with carry
			ans = str(ni + carry) + ans
			carry = 0
		i -= 1
		j -= 1

	# cut off beginning zeros
	for i in range(len(ans)):
		if ans[i] != '0':
			break
	return ans[i:] if firstBigger else '-' + ans[i:]

if __name__ == '__main__':
	(a, b) = (555,1234)
	print str(a) + ' - ' + str(b) + ' = ' + subtract("555", "1234")
	# print subtract("555", "1234")