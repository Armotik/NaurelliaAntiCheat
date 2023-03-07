# NaurelliaAntiCheat
## Anti Cheat for NaurelliaCraft

---

### Commits
```
1- Initial commit
2- Added toString() method in the Logs.java class, added LogsBlock.java class, LogsBlockInteract.java class, LogsDropItem.java class, LogsPlayerDeath.java class. Modified LogsChat.java, LogsJoinQuit.java, LogsType.java, EventManager.java and LogsManager.java.
3- Added Tests package, including Tests.java class, logs test package with TestLogsJoinQuit.java class and TestLogsPlayerDeath.jave class. Modified toString() method for classes which extends Logs.java class.
4- Modified toString() method for classes which extends Logs.java class. Modified Files.java class, added checkFile(), readLogs() and writeLogs(Logs logs) methods.
5- Finished the readLogs() method in FilesReader.java to include all current log type. Deleted the writeInFile() method to replace it with the writeLogs() method in Files.java class. Added JavaDoc to every class and method. Added new Log types : LOG__BED_ENTER and LOG__BED_LEAVE.
```

---

This plugin have a log system, for the moment, the available logs are :
- `joinLogs`
- `quitLogs`
- `chatLogs (including commands)`
- `blockLogs (including block break and block place)`
- `blockInteractLogs (including door, chest, etc...)`
- `dropItemLogs`
- `playerDeathLogs`
- `bedLogs (including bed enter and bed leave)`

---

### <ins>Commands</ins>

- `/naurellialog` : Display the logs of the player (only for staff)
- `/adminlog` : Direct operation on the logs (delete, etc...) (only for admins)
- `/testnlog` : Test the logs (only for admins)

---