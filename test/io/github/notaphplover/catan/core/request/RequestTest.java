package io.github.notaphplover.catan.core.request;

import static org.junit.jupiter.api.Assertions.assertSame;

import io.github.notaphplover.catan.core.player.IPlayer;
import io.github.notaphplover.catan.core.player.Player;
import io.github.notaphplover.catan.core.resource.ResourceManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class RequestTest {

  @DisplayName("It must store its player")
  @Tag(value = "Request")
  @Test
  public void itMustStoreItsPlayer() {
    IPlayer player = new Player(0, new ResourceManager());

    MinimunRequest request = new MinimunRequest(player, RequestType.BUILD_CONNECTION);

    assertSame(player, request.getPlayer());
  }

  @DisplayName("It must store its request type")
  @Tag(value = "Request")
  @Test
  public void itMustStoreItsRequestType() {
    RequestType requestType = RequestType.BUILD_CONNECTION;

    MinimunRequest request = new MinimunRequest(new Player(0, new ResourceManager()), requestType);

    assertSame(requestType, request.getType());
  }
}
