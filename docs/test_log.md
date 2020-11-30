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

### field variable `public transient IntegerProperty movesCountsProperty;

| No.  | Result        | Comparison | Test  Brief                                                  | Improvement |
| ---- | ------------- | ---------- | ------------------------------------------------------------ | ----------- |
| 1    | **Test pass** |            | This test is conducted by moving left, up, right, and down in the `gameEngine` instance, and comparing the field variable `movesCountsProperty` with expected value `4`. |             |





## GamePageController

### setMovesCountEventListener()

| No.  | Result        | Comparison      | Test  Brief                                                  | Improvement                                                  |
| ---- | ------------- | --------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 1    | **Test fail** | Compiling fails | This is a **Regression Test** on save game. When saving the game, the compiling fails, the compiler message is as follow:![](img/issue30.png) |                                                              |
| 2    | **Test pass** |                 |                                                              | This issue is also reported in [issue 30]([Bug reported in move count after loading saved file (#30) · Issues · scyyf1 / AE2DMS-CW-20127091 · GitLab (nottingham.edu.cn)](https://csprojects.nottingham.edu.cn/scyyf1/ae2dms-cw-20127091/-/issues/30)). And, this is solved by refactoring and new serializing feature. The main improvement is logged in [commit 08fc2f630acd638b5ac7fb60f9d238b49df1b599]([refactor(move count): refactor the observer pattern on the move count feature... (08fc2f63) · Commits · scyyf1 / AE2DMS-CW-20127091 · GitLab (nottingham.edu.cn)](https://csprojects.nottingham.edu.cn/scyyf1/ae2dms-cw-20127091/-/commit/08fc2f630acd638b5ac7fb60f9d238b49df1b599)),[commit 8f34339f1c610c261ef0502172932c46044bdfb2]([fix(moves count): the field variable `movesCountsProperty` is not... (8f34339f) · Commits · scyyf1 / AE2DMS-CW-20127091 · GitLab (nottingham.edu.cn)](https://csprojects.nottingham.edu.cn/scyyf1/ae2dms-cw-20127091/-/commit/8f34339f1c610c261ef0502172932c46044bdfb2)), and [commit c2ecd54765f951d4d4b9a3c9261d8145691c6433]([fix(move counts): fix the bug issued in #30. This is fixed by moving the event... (c2ecd547) · Commits · scyyf1 / AE2DMS-CW-20127091 · GitLab (nottingham.edu.cn)](https://csprojects.nottingham.edu.cn/scyyf1/ae2dms-cw-20127091/-/commit/c2ecd54765f951d4d4b9a3c9261d8145691c6433)). |



