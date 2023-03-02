const threeSumClosest = function(nums, target) {
  if (!nums || nums.length < 3) {
    throw new Error('nums should have at least 3 elements.');
  }

  nums.sort();
  let ans = nums[0] + nums[1] + nums[2];
  const n = nums.length;
  for (let i = 0; i < n - 2; i ++) {
    if (i > 0 && nums[i] == nums[i-1]) {
      continue;
    }


    let j = i + 1, k = n - 1;
    while (j < k) {
      const sum = nums[i] + nums[j] + nums[k];
      if (Math.abs(sum - target) < Math.abs(ans - target)) {
        ans = sum;
      }

      if (sum === target) {
        return target;
      } else if (sum > target) {
        k --;
      } else {
        j ++;
      }
    }
  }

  return ans;
}

// console.log(threeSumClosest([1, 2], 3));
console.log(threeSumClosest([-1,2,1,-4], 1));
console.log(threeSumClosest([0,0,0], 1));