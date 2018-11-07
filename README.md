# PopWindow
### 参考 [https://juejin.im/post/5bde9660e51d4573506a1c76](https://juejin.im/post/5bde9660e51d4573506a1c76)
<image src="https://github.com/Golabe/PopWindow/blob/master/a.png?raw=true" width="300"/>
<image src="https://github.com/Golabe/PopWindow/blob/master/b.png?raw=true" width="300"/>

## 使用
## Gradle 添加
```
implementation 'top.golabe.popwindowview:popwindowview:1.0.0'
```
## Java代码
```java
   new PopWindowView.Builder(this,view)
                .setItems(dataList)
                .setAdapter()
                .setBackgroundColor()
                .setModal()
                .setDivider()
                .setPopAnimStyle()
                .setPopupWindowHeight()
                .setPopupWindowWidth()
                .setItemClickListener(new PopWindowView.OnItemClickListener() {
                    @Override
                    public void onItemClick(PopWindowView pop, AdapterView<?> parent, View v, int position, long id) {
                        Toast.makeText(MainActivity.this,position+"",Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
```

 可以自己设置adapter(),也可以不用设置直接使用默认的
