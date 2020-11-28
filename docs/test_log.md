## GameEngineTest

### undo()

In this test, the map file debugGame.skb would be applied to test.

| No.  | Result                                          | Comparison                                     |                         Test  Brief                          | Improvement                                                  |
| ---- | ----------------------------------------------- | ---------------------------------------------- | :----------------------------------------------------------: | ------------------------------------------------------------ |
| 1    | **Test fails** while the contents are identical | ![](img/images_test_GameEngineTest_undo_1.png) | In the testing instance of `GameEngine`, move the keeper k once and undo, test the current with the new same level file |                                                              |
| 2    | **Test Pass**                                   |                                                | Compare the `movesCount` with `1`, the string of `objectsGrid` and `diamondsGrid` in the `currentLevel` of this instance of  `GameEngine` by applying function `toString` on all objects. | To test this function, we should examine if the two class is identical. This should not be done by comparing the reference of obejct but comparing field in these objects. |

### resetCurrentLevel()

| No.  | Result        | Comparison | Test  Brief                                                  | Improvement |
| ---- | ------------- | ---------- | ------------------------------------------------------------ | ----------- |
| 1    | **Test pass** |            | In one instance of `GameEngine`, move the keeper around by `handleKey` and call `resetCurrentLevel`. Compare the `movesCount` with `0`, the string of `objectsGrid` and `diamondsGrid` in the `currentLevel` of this instance of  `GameEngine`  with the new instance of `GameEnine` by applying function `toString` on all objects. |             |

### saveGame(File savedLocation)

| No.  | Result        | Comparison     | Test  Brief                                                  | Improvement                                                  |
| ---- | ------------- | -------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 1    | **Test fail** | Execution fail | When serialize `GameEngine`, the logger is also serialized. However, the logger should be maintained as **singleton**. It may occur duplicated logger after deserializing.![](img/test_save_game_exception1.png) |                                                              |
| 2    | **Test pass** |                |                                                              | Every logger field variable relevant to serialized class should be declared as  `transient` |

