package io.github.notaphplover.catan.core.request;

import io.github.notaphplover.catan.core.board.BoardElementType;
import io.github.notaphplover.catan.core.player.IPlayer;

public abstract class BuildElementRequest extends Request {

  private BoardElementType type;

  private int x;

  private int y;

  public BuildElementRequest(
      IPlayer player, RequestType requestType, BoardElementType elementType, int x, int y) {
    super(player, requestType);

    this.type = elementType;
    this.x = x;
    this.y = y;
  }

  public BoardElementType getElementType() {
    return type;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }
}
