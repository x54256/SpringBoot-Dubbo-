1、组织机构（用户组）

2、用户

3、角色

4、操作

5、资源

    5.1 用户具有的资源文件
    5.2 可以使用的操作


分配用户到机构

给用户、机构角色

资源分配到角色

资源绑定操作

查

将用户移动到其它机构

机构删除（如果有用户友好提示，但是机构挂接的角色怎么办）




### 采用先将资源绑定操作==>权限，这种方式的原因？

殷总提出了一种思路是，当创建一个角色的时候，让他绑定资源，然后选择对资源的权限；

这样很符合用户的使用习惯；但是，可能会有一种情况，文件A可以有增删改操作、但是文件B是一个只读文件，我们可以通过权限来进行控制

如果采用殷总的方式，那么运维人员要记住这个文件是否是可读的，一次可以，但是次数多了很可能就把一个只读文件变成了可以删除了。
