# PopupWindow的简单使用
1. 创建布局视图
```java
View popView = getLayoutInflater().inflate(R.layout.yourLayoutId, null,false);
// todo 设置内容
```

2. 创建PopupWindow
```java
PopupWindow mPopupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
// 点击其他地方消失
mPopupWindow.setOutsideTouchable(true);
```

3. 显示
```java
//显示在基准控件下方,用于代表下拉列表
mPopupWindow.showAsDropDown(tvTitle);
 ```