const threeSum = function(nums) {
  const ans = new Array();
  if (!nums || nums.length < 3) {
    return ans;
  }

  nums.sort();
  for (let i = 0; i < nums.length - 2; i ++) {
    if (i > 0 && nums[i] == nums[i-1]) {
      continue;
    }

    let j = i + 1, k = nums.length - 1;
    while (j < k) {
      const sum = nums[i] + nums[j] + nums[k];
      if (sum === 0) {
        ans.push([nums[i], nums[j], nums[k]]);
        j ++;
        while (j < k && nums[j] == nums[j-1]) j ++;
      } else if (sum > 0) {
        k --;
      } else {
        j ++;
      }
    }
  }

  return ans;
}

console.log(threeSum([-1,0,1,2,-1,-4]))
console.log(threeSum([0, 1, 1]))
console.log(threeSum([0,0,0]))
