const maxArea = function(heights) {
  if (!heights) return 0;
  let ans = 0;
  for (let i = 0; i < heights.length; i ++) {
    for (let j = i + 1; j < heights.length; j ++) {
      const width = j - i;
      const height = Math.min(heights[i], heights[j]);
      ans = Math.max(ans, width * height);
    }
  }

  return ans;
}

console.log(maxArea());
console.log(maxArea([]));
console.log(maxArea([1]));
console.log(maxArea([1, 2]));
console.log(maxArea([4, 3, 1, 2, 4]));
console.log(maxArea([1, 8, 6, 4, 3, 2, 5, 3, 7]));

const maxArea2 = function(heights) {
  if (!heights) return 0;

  let ans = 0;
  let left = 0, right = heights.length - 1;
  while (left < right) {
    const width = right - left;
    let height;
    if (heights[left] < heights[right]) {
      height = heights[left];
      left ++;
    } else {
      height = heights[right];
      right --;
    }
    ans = Math.max(ans, height * width);
  }

  return ans;
}

console.log(maxArea2([]));
console.log(maxArea2());
console.log(maxArea2([1]));
console.log(maxArea2([1, 2]));
console.log(maxArea2([4, 3, 1, 2, 4]));
console.log(maxArea2([1, 8, 6, 4, 3, 2, 5, 3, 7]));