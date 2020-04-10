package io.github.notaphplover.catan.core.game;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.notaphplover.catan.core.board.CatanBoard;
import io.github.notaphplover.catan.core.board.ICatanBoard;
import io.github.notaphplover.catan.core.board.connection.BoardConnection;
import io.github.notaphplover.catan.core.board.connection.ConnectionType;
import io.github.notaphplover.catan.core.board.connection.IBoardConnection;
import io.github.notaphplover.catan.core.board.element.IBoardElement;
import io.github.notaphplover.catan.core.board.exception.InvalidBoardDimensionsException;
import io.github.notaphplover.catan.core.board.exception.InvalidBoardElementException;
import io.github.notaphplover.catan.core.board.structure.BoardStructure;
import io.github.notaphplover.catan.core.board.structure.IBoardStructure;
import io.github.notaphplover.catan.core.board.structure.StructureType;
import io.github.notaphplover.catan.core.board.terrain.BoardTerrain;
import io.github.notaphplover.catan.core.board.terrain.IBoardTerrain;
import io.github.notaphplover.catan.core.board.terrain.TerrainType;
import io.github.notaphplover.catan.core.exception.NonNullInputException;
import io.github.notaphplover.catan.core.exception.NonVoidCollectionException;
import io.github.notaphplover.catan.core.game.player.PlayerManager;
import io.github.notaphplover.catan.core.player.IPlayer;
import io.github.notaphplover.catan.core.player.Player;
import io.github.notaphplover.catan.core.resource.ResourceManager;
import io.github.notaphplover.catan.core.resource.provider.DefaultTerrainProductionProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class CatanGameTest {

  @DisplayName("It must not build a game with a collection of null players")
  @Tag(value = "CatanGame")
  @Test
  public void itMustNotBuildAGameWithACollectionOfNullPlayers()
      throws NonNullInputException, NonVoidCollectionException, InvalidBoardDimensionsException,
          InvalidBoardElementException {

    IPlayer[] players = {null};
    ICatanBoard board = buildStandardBoard();
    assertThrows(
        NonNullInputException.class,
        () -> new CatanGame(board, new PlayerManager(players, 0, false), 10, GameState.NORMAL));
  }

  @DisplayName("It must not build a game with a null board")
  @Tag(value = "CatanGame")
  @Test
  public void itMustNotBuildAGameWithANullBoard()
      throws NonNullInputException, NonVoidCollectionException {
    IPlayer[] players = {new Player(0, new ResourceManager())};
    assertThrows(
        NonNullInputException.class,
        () -> new CatanGame(null, new PlayerManager(players, 0, false), 10, GameState.NORMAL));
  }

  @DisplayName("It must not build a game with a null collection of players")
  @Tag(value = "CatanGame")
  @Test
  public void itMustNotBuildAGameWithANullCollectionOfPlayers()
      throws NonNullInputException, NonVoidCollectionException, InvalidBoardDimensionsException,
          InvalidBoardElementException {

    IPlayer[] players = null;
    ICatanBoard board = buildStandardBoard();
    assertThrows(
        NonNullInputException.class,
        () -> new CatanGame(board, new PlayerManager(players, 0, false), 10, GameState.NORMAL));
  }

  @DisplayName("It must not build a game with a void collection of players")
  @Tag(value = "CatanGame")
  @Test
  public void itMustNotBuildAGameWithAVoidCollectionOfPlayers()
      throws NonNullInputException, NonVoidCollectionException, InvalidBoardDimensionsException,
          InvalidBoardElementException {

    IPlayer[] players = {};
    ICatanBoard board = buildStandardBoard();
    assertThrows(
        NonVoidCollectionException.class,
        () -> new CatanGame(board, new PlayerManager(players, 0, false), 10, GameState.NORMAL));
  }

  @DisplayName("It must return the active player")
  @Tag(value = "CatanGame")
  @Test
  public void itMustReturnTheActivePlayer()
      throws NonNullInputException, NonVoidCollectionException, InvalidBoardDimensionsException,
          InvalidBoardElementException {

    IPlayer activePlayer = new Player(0, new ResourceManager());
    IPlayer[] players = {activePlayer};
    ICatanBoard board = buildStandardBoard();
    CatanGame game =
        new CatanGame(board, new PlayerManager(players, 0, false), 10, GameState.NORMAL);

    assertSame(activePlayer, game.getActivePlayer());
  }

  @DisplayName("It must return the board")
  @Tag(value = "CatanGame")
  @Test
  public void itMustReturnTheBoard()
      throws NonNullInputException, NonVoidCollectionException, InvalidBoardDimensionsException,
          InvalidBoardElementException {

    IPlayer[] players = {new Player(0, new ResourceManager())};
    ICatanBoard board = buildStandardBoard();
    CatanGame game =
        new CatanGame(board, new PlayerManager(players, 0, false), 10, GameState.NORMAL);

    assertSame(board, game.getBoard());
  }

  @DisplayName("It must return the stored players collection")
  @Tag(value = "CatanGame")
  @Test
  public void itMustReturnTheStoredPlayersCollection()
      throws NonNullInputException, NonVoidCollectionException, InvalidBoardDimensionsException,
          InvalidBoardElementException {

    IPlayer[] players = {new Player(0, new ResourceManager())};
    ICatanBoard board = buildStandardBoard();
    CatanGame game =
        new CatanGame(board, new PlayerManager(players, 0, false), 10, GameState.NORMAL);

    assertSame(players, game.getPlayers());
  }

  @DisplayName("It must return the turn started attribute")
  @Tag(value = "CatanGame")
  @Test
  public void itMustReturnTheTurnStartedAttribute()
      throws InvalidBoardDimensionsException, InvalidBoardElementException, NonNullInputException,
          NonVoidCollectionException {

    IPlayer[] players = {new Player(0, new ResourceManager())};
    ICatanBoard board = buildStandardBoard();
    boolean turnStarted = true;
    CatanGame game =
        new CatanGame(board, new PlayerManager(players, 0, turnStarted), 10, GameState.NORMAL);

    assertSame(turnStarted, game.isTurnStarted());
  }

  private IBoardStructure buildNoneStructure() {
    return new BoardStructure(null, new ResourceManager(), StructureType.NONE);
  }

  private IBoardTerrain buildNoneTerrain() {
    return new BoardTerrain(0, TerrainType.NONE);
  }

  private ICatanBoard buildStandardBoard()
      throws InvalidBoardDimensionsException, InvalidBoardElementException {
    IBoardElement[][] elements = {
      {
        buildNoneStructure(), buildVoidConnection(), buildNoneStructure(),
      },
      {
        buildVoidConnection(), buildNoneTerrain(), buildVoidConnection(),
      },
      {
        buildNoneStructure(), buildVoidConnection(), buildNoneStructure(),
      },
    };

    return new CatanBoard(3, 3, elements, new DefaultTerrainProductionProvider());
  }

  private IBoardConnection buildVoidConnection() {
    return new BoardConnection(null, new ResourceManager(), ConnectionType.VOID);
  }
}
