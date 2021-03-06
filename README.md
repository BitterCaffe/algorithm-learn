# algorithm-learn
### BitMap
#### 一、BitMap的几种使用场景
##### 1、使用更少的内存空间存储更多数据。
##### 2、使用更少的内存空间实现排序。
##### 3、使用更少的内存空间校验一组数组中是否有重复数据。

#### 二、BitMap的几点缺点
##### 1、不能存储重复数据，会有覆盖问题。
##### 2、如果你的数据从一个比较大的值开始计算则前面的很多空间都被浪费了。
##### 3、使用BitMap方式存储数据整体来说减少了内存占用但是如果数据不连续则会浪费很多空间。

#### 三、BitMap数据存储规则
##### 1、这种存储方式其实没有吧一个真实的数存储起来而是按照一定的规则计算然后用bit为来标记1存储0不存在。
##### 2、一个具体数值存储的方式是 index和offset的组合方式来标识，index表示使用数组的索引位，而offset表示具体索引位中的bit位置。
##### 3、这里使用int数组来存储则4*8bit = 32bit 所以index= value/32 而offset = value%32即一个是除的值一个是余数。其实也很好理解index来确定整除部分而offset来确定余数。具体数值计算value = index*32 +offset就反算出来了
