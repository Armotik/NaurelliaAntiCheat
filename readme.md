# NaurelliaAntiCheat
## Anti Cheat for NaurelliaCraft

---

### Commits 
```
1- Initial commit
2- Added toString() method in the Logs.java class, added LogsBlock.java class, LogsBlockInteract.java class, LogsDropItem.java class, LogsPlayerDeath.java class. Modified LogsChat.java, LogsJoinQuit.java, LogsType.java, EventManager.java and LogsManager.java.
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

---

### <ins>Commands</ins>

- `/nlog <player>` : All player's log
- `/nlog <player> <option> <args>` : Player's log filter
    - `/nlog <player> radius <radius>` : Player's log inside a given radius
    - `/nlog <player> world <world>` : Player's log inside a given world
    - `/nlog <player> location` : Player's log where the command is executed
    - `/nlog <player> event <event name>`

---