# GSE project: ChessGame

## USAGE:

```
chessgame [--gui] [<fen>]
  --gui       starts a javafx based gui for the chess game
  <fen>       starts the game with the given fen notated configuration
```

### CLI

- move figures by issuing moves
  ```
  xy-mn       x, m in [a-h]
              y, n in [1-8]
  ```
- to make more than one move write them in one line separated by `;`


### GUI

- to start click `Game|start`
- to load save click `File|save/load`

1. click a figure to select it
2. click it again to unselect
3. click on another field to move the selected figure

- the figure on the right shows which players turn it is

-------

GIT checkout:
git clone https://tdpe.techfak.uni-bielefeld.de/git/gse-ws-2018-ysander.git
