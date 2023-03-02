const longestLengthOfSubstring = function(s) {
  if (!s) {
    return 0;
  }

  const indexMap = new Map();

  let maxLength = 0, left = 0;
  for (let right = 0; right < s.length; right++ ) {
    const rightChar = s[right];
    if (indexMap.has(rightChar)) {
      left = Math.max(left, indexMap.get(rightChar) + 1);
    }
    indexMap.set(rightChar, right);
    maxLength = Math.max(maxLength, right - left + 1);
  }

  return maxLength;
}

console.log(longestLengthOfSubstring(""));
console.log(longestLengthOfSubstring("a"));
console.log(longestLengthOfSubstring("aa"));
console.log(longestLengthOfSubstring("aab"));
console.log(longestLengthOfSubstring("abcabcbb"));

const longestLengthOfSubstring2 = function(s) {
  if (!s) {
    return 0;
  }

  const freqMap = new Map();

  let maxLength = 0, left = 0;
  for (let right = 0; right < s.length; right++ ) {
    const rightChar = s[right];
    if (freqMap.has(rightChar)) {
      freqMap.set(rightChar, freqMap.get(rightChar) + 1);
    } else {
      freqMap.set(rightChar, 1);
    }

    while (freqMap.get(rightChar) > 1) {
      const leftChar = s[left];
      freqMap.set(leftChar, freqMap.get(leftChar) - 1);
      left ++;
    }

    maxLength = Math.max(maxLength, right - left + 1);
  }

  return maxLength;
}


console.log(longestLengthOfSubstring2(""));
console.log(longestLengthOfSubstring2("a"));
console.log(longestLengthOfSubstring2("aa"));
console.log(longestLengthOfSubstring2("aab"));
console.log(longestLengthOfSubstring2("abcabcbb"));