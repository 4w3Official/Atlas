<div align="center">
  <h1>Atlas</h1>
  <p>just a simple funny game mode with pinatas</p>
</div>

---

### Prerequisites

Server must be running **Paper** on `1.21` or higher, and Java 21 or higher

---

### Dependencies

These dependencies are optional but recommended to unlock full functionality:

* [NuVotifier](https://github.com/NuVotifier/NuVotifier) (Required for voting features)
* [PlaceholderAPI](https://placeholderapi.com/)
* [ModelEngine](https://github.com/Ticxo/ModelEngine) / [BetterModel](https://github.com/Toxicity188/BetterModel) (For custom entity models)

---

### Commands & Permissions

| Command                                   | Permission | Description                                                           |
|-------------------------------------------|------------|-----------------------------------------------------------------------|
| `/atlas` (or `/as`)                       | `atlas.use` | Main command to access Atlas features                                 |
| `/atlas help`                             | `atlas.help` | Displays help information for Atlas commands                  |
| `/atlas reload`                           | `atlas.reload` | Reloads the Atlas configuration without restarting the server |
| `/atlas pinata start <pinata> <location>` | `atlas.pinata.start` | Start pinata countdown                                                |
| `/atlas pinata spawn <pinata> <location>` | `atlas.pinata.spawn` | Spawn pinata immediately                                              |
| `/atlas pinata killall`                   | `atlas.pinata.killall` | Remove all active pinatas                                             |
| `/atlas pinata spawnpoint add <name>`     | `atlas.pinata.spawnpoint.add` | Save current location                                                 |
| `/atlas pinata spawnpoint remove <name>`  | `atlas.pinata.spawnpoint.remove` | Remove saved location                                                 |
| `/atlas vote check <player>`              | `atlas.vote.check` | Check player votes                                                    |
| `/atlas vote add <player> <amount>`       | `atlas.vote.add` | Add votes to a player's balance                                       |
| `/atlas vote remove <player> <amount>`    | `atlas.vote.remove` | Remove votes from a player's balance                                  |
| `/atlas vote set <player> <amount>`       | `atlas.vote.set` | Set a player's vote balance                                           |
| `/atlas vote send <player> [service]`     | `atlas.vote.send` | Simulate a vote                                                       |

### Placeholders
#### Pinata Placeholders

| Placeholder | Description                                                      |
| :--- |:-----------------------------------------------------------------|
| `%atlas_pinata_count%` | Returns the number of currently active pinatas                   |
| `%atlas_pinata_any_alive%` | Returns `true` if at least one pinata is alive, otherwise `false` |
| `%atlas_pinata_nearest_alive%` | Returns `true` if a pinata is near the player, otherwise `false` |
| `%atlas_pinata_nearest_name%` | Returns the custom name of the nearest pinata                    |
| `%atlas_pinata_nearest_health%` | Returns the current health of the nearest pinata                 |
| `%atlas_pinata_nearest_max_health%` | Returns the maximum health of the nearest pinata                 |
| `%atlas_pinata_nearest_location%` | Returns the location (World, X, Y, Z) of the nearest pinata      |

#### Vote Tracking

| Placeholder | Description                                     |
| :--- |:------------------------------------------------|
| `%atlas_votes%` | Returns the player's total lifetime votes       |
| `%atlas_votes_daily%` | Returns the player's votes for the current day  |
| `%atlas_votes_weekly%` | Returns the player's votes for the current week |
| `%atlas_votes_monthly%` | Returns the player's votes for the current month |
| `%atlas_votes_yearly%` | Returns the player's votes for the current year |
| `%atlas_votes_previous_daily%` | Returns the player's votes for yesterday        |
| `%atlas_votes_previous_weekly%` | Returns the player's votes for last week        |
| `%atlas_votes_previous_monthly%` | Returns the player's votes for last month       |
| `%atlas_votes_previous_yearly%` | Returns the player's votes for last year        |

#### Community Goal

| Placeholder | Description                                                     |
| :--- |:----------------------------------------------------------------|
| `%atlas_community_goal_current%` | Returns the current progress toward the next goal (e.g., `10`)  |
| `%atlas_community_goal_required%` | Returns the votes required to trigger the goal (e.g., `50`)     |
| `%atlas_community_goal_percentage%` | Returns the percentage completion toward the goal (e.g., `20%`) |
| `%atlas_community_goal_remaining%` | Returns the number of votes needed to reach the goal            |
| `%atlas_community_goal_total%` | Returns the total number of community votes ever received       |
| `%atlas_community_goal_met_count%` | Returns the number of times the community goal has been reached |
