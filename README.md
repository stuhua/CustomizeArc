# CustomizeArc
自定义的进度弧线

# 背景
我司派我重任，做一个弧形的进度条！

# 步骤
- 编写attr.xml文件
- 在layout布局文件中引用，同时引用命名空间
- 在自定义控件中进行读取（构造方法拿到attr.xml文件值）
- 覆写onMeasure()方法
- 覆写onLayout()方法

# 运行
需求就是这么奇怪，当然你可以简单设置成圆心进度条

![效果](/img/show.png)

# 总结
1. typedArray.recycle(); 需要回收
2. 像素转换问题dp2px...
3. 复写dispatchTouchEvent、onTouchEvent处理滑动冲动问题

----

# 自定义垂直Seekbar,ProgressBar

[![Build Status](https://travis-ci.org/meolu/walle-web.svg?branch=master)](https://travis-ci.org/meolu/walle-web)

# 演示

# 特点

![效果](/img/img2.png)

# CHANGELOG

[采坑之旅](http://www.jianshu.com/p/5f9fa83fe324)

