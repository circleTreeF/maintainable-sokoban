# AE2DMS-Yizirui Fang-20127091

## Refactor

### Design Pattern

| Design Pattern  | Class                            | Breif                                                        |
| --------------- | -------------------------------- | ------------------------------------------------------------ |
| **Singleton**   | [`GameLogger`]()                 | This is to make sure only one logger                         |
| **Factory**     | [`GraphicObjectFactory`]()       | This is to create object without exposing the creation logic to the client. |
| **Observer**    | [`GamePageController`]()         | Any property modified, its dependent objects are to be notified automatically. |
| **Iterator**    | [`GameMap`]()                    | Access the elements of a collection `Levels` in sequential manner. |
| **MVC pattern** | Controller, Model, View packages |                                                              |



### Devide, conquer and quick wins refactoring

| Type                           | Class                                                        |
| ------------------------------ | ------------------------------------------------------------ |
| Remove dead code               | `GameEnigine`, `GameGrid`                                    |
| Enhave identifier naming       | `input`, `code` in `GameEngine`, `raw_level` in `Level`, `obj` and `ft` in `GraphicObject` |
| Reduce method size             | Extracted methods to new class `FileOperator` for all operations about the file chooser |
| Enhave component encapsulation | Apply `private` on those methos only used in the class it-self |

## Project Class Diagram

![autoGenorV1](https://raw.githubusercontent.com/circleTreeF/myImg/master/typora/autoGenorV1.png)

## Additional Feature

### Mini Web Broswer

The homepage of this web broswer is the user guide of this game. This broswer is equipped with URL field, back, forward, and view icon for project repository redirection. The URL will automatically change according to the current page.

![image-20201207052506452](https://raw.githubusercontent.com/circleTreeF/myImg/master/typora/image-20201207052506452.png)

### High Mark Board

This is to present the **top 5 mark records** of the map selected in the combobox. 

![image-20201207054923617](https://raw.githubusercontent.com/circleTreeF/myImg/master/typora/image-20201207054923617.png)

### Load Saved Game

This feature is to load the previously saved game file and resume everything.

![image-20201207055037004](https://raw.githubusercontent.com/circleTreeF/myImg/master/typora/image-20201207055037004.png)

### Current Moves Count

This feature is to count and present the moves count in the current level dynamically. 

![](https://raw.githubusercontent.com/circleTreeF/myImg/master/typora/image-20201207052807781.png)

### Total previous move conut

This feature is to log and record the moves count you have in all previous levels of this map.

![image-20201207052951933](https://raw.githubusercontent.com/circleTreeF/myImg/master/typora/image-20201207052951933.png)

### Wall Bombs

This feature is to destory the wall by bombs. By default, a user will have 2 bombs at the start and can use by clicking the wall. Once use the number of bomb present in the scene will change accordingly.

![image-20201207053038594](https://raw.githubusercontent.com/circleTreeF/myImg/master/typora/image-20201207053038594.png)

### Load Music

This feature is to allow user load the user-specified music to the game. Please note only pure **.wav** format music file is allowed.

![image-20201207053433187](https://raw.githubusercontent.com/circleTreeF/myImg/master/typora/image-20201207053433187.png) 

### Restart Music

This feature is to restart the currently playing music.

![image](../../OneDrive/OneDrive%20-%20The%20University%20of%20Nottingham%20Ningbo%20China/%E5%9B%BE%E7%89%87/%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE/%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE(126).png)

### Save Game

This feature allow the user to save file in **.skbSaved** format at the user-specified directory.

![image-20201207053824607](https://raw.githubusercontent.com/circleTreeF/myImg/master/typora/image-20201207053824607.png)

### Undo

This feature is to undo any lastest movement of the keeper. However, as penalty, the move couts will not decreases. This could be executed either by clicking the item or pressing the hot key.

### Reset

This feature will reset the current level and the moves count of this level.

![undo](https://raw.githubusercontent.com/circleTreeF/myImg/master/typora/undo.png)