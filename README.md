# PopWindow
### 参考 [https://juejin.im/post/5bde9660e51d4573506a1c76](https://juejin.im/post/5bde9660e51d4573506a1c76)
## 使用
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
