# Bottom Dialog
[ ![Download](https://api.bintray.com/packages/raphaelbussa/maven/bottom-dialog/images/download.svg) ](https://bintray.com/raphaelbussa/maven/bottom-dialog/_latestVersion) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-BottomDialog-green.svg?style=true)](https://android-arsenal.com/details/1/3168) [![API](https://img.shields.io/badge/API-11%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=11)

This is a custom dialog attached at the bottom

![Screen](https://raw.githubusercontent.com/raphaelbussa/BottomDialog/master/img/header.png)

### Import
At the moment the library is in my personal maven repo
```Gradle
repositories {
    maven {
        url 'http://dl.bintray.com/raphaelbussa/maven'
    }
}
```
```Gradle
dependencies {
    compile 'rebus:bottom-dialog:0.0.5'
}
```
### How to use

A sample dialog...
```java
private BottomDialog dialog;

dialog = new BottomDialog(MainActivity.this);
                dialog.title(R.string.app_name);
                dialog.canceledOnTouchOutside(true);
                dialog.cancelable(true);
                dialog.inflateMenu(R.menu.menu_main);
                dialog.setOnItemSelectedListener(new BottomDialog.OnItemSelectedListener() {
                    @Override
                    public boolean onItemSelected(int id) {
                        switch (id) {
                            case R.id.action_social_share:
                                Intent s = new Intent(android.content.Intent.ACTION_SEND);
                                s.setType("text/plain");
                                s.putExtra(android.content.Intent.EXTRA_TEXT, "https://github.com/rebus007/BottomDialog/issues");
                                startActivity(Intent.createChooser(s, getResources().getText(R.string.action_social_share)));
                                return true;
                            case R.id.action_content_add:
                                Item item = new Item();
                                item.setTitle("New element");
                                item.setIcon(getResources().getDrawable(R.drawable.ic_action_action_bug_report));
                                item.setId(100);
                                dialog.addItem(item);
                                return false;
                            case R.id.action_delete:
                                finish();
                                return true;
                            case R.id.action_bug_report:
                                Intent i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse("https://github.com/rebus007/BottomDialog/issues"));
                                startActivity(i);
                                return true;
                            case R.id.ic_github:
                                Intent g = new Intent(Intent.ACTION_VIEW);
                                g.setData(Uri.parse("https://github.com/rebus007/BottomDialog"));
                                startActivity(g);
                                return true;
                            case 100:
                                Toast.makeText(MainActivity.this, "New element clicked!!!", Toast.LENGTH_SHORT).show();
                                return false;
                            default:
                                return false;
                        }
                    }
                });
                dialog.show();
```

### Sample
Browse the sample code [here](https://github.com/raphaelbussa/BottomDialog/tree/master/app)
### App using Bottom Dialog
If you use this lib [contact me](mailto:raphaelbussa@gmail.com) and I will add it to the list below:

### Developed By
Raphaël Bussa - [raphaelbussa@gmail.com](mailto:raphaelbussa@gmail.com)

[ ![Twitter](https://raw.githubusercontent.com/raphaelbussa/BottomDialog/master/img/social/twitter-icon.png) ](https://twitter.com/raphaelbussa)[ ![Linkedin](https://raw.githubusercontent.com/raphaelbussa/BottomDialog/master/img/social/linkedin-icon.png) ](https://www.linkedin.com/in/raphaelbussa)

### License
```
The MIT License (MIT)

Copyright (c) 2016 Raphael Bussa <raphaelbussa@gmail.com>

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
