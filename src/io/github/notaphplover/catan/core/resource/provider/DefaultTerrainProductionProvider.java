package io.github.notaphplover.catan.core.resource.provider;

import io.github.notaphplover.catan.core.board.group.IStructureTerrainTypesPair;
import io.github.notaphplover.catan.core.board.group.StructureTerrainTypesPair;
import io.github.notaphplover.catan.core.board.structure.StructureType;
import io.github.notaphplover.catan.core.board.terrain.TerrainType;
import io.github.notaphplover.catan.core.resource.IResourceStorage;
import io.github.notaphplover.catan.core.resource.ResourceStorage;
import io.github.notaphplover.catan.core.resource.ResourceType;
import java.util.Map;
import java.util.TreeMap;

public class DefaultTerrainProductionProvider extends TerrainProductionProvider {

  private static final int RESOURCES_PER_CITY = 2;

  private static final int RESOURCES_PER_SETTLEMENT = 1;

  public DefaultTerrainProductionProvider() {
    super(buildProductionMap());
  }

  private static IResourceStorage buildCityProduction(TerrainType type) {
    return buildProduction(type, RESOURCES_PER_CITY);
  }

  private static IResourceStorage buildProduction(
      TerrainType terrainType, StructureType structureType) {

    switch (structureType) {
      case CITY:
        return buildCityProduction(terrainType);
      case SETTLEMENT:
        return buildSettlementProduction(terrainType);
      default:
        return new ResourceStorage();
    }
  }

  private static IResourceStorage buildProduction(TerrainType type, int production) {

    ResourceType resourceType = getResourceAssociatedTo(type);

    Map<ResourceType, Integer> resourceMap = new TreeMap<ResourceType, Integer>();

    if (resourceType != null) {
      resourceMap.put(resourceType, production);
    }

    return new ResourceStorage(resourceMap);
  }

  private static Map<IStructureTerrainTypesPair, IResourceStorage> buildProductionMap() {

    Map<IStructureTerrainTypesPair, IResourceStorage> productionMap =
        new TreeMap<IStructureTerrainTypesPair, IResourceStorage>();

    for (TerrainType terrainType : TerrainType.values()) {
      for (StructureType structureType : StructureType.values()) {
        productionMap.put(
            new StructureTerrainTypesPair(structureType, terrainType),
            buildProduction(terrainType, structureType));
      }
    }

    return productionMap;
  }

  private static IResourceStorage buildSettlementProduction(TerrainType type) {
    return buildProduction(type, RESOURCES_PER_SETTLEMENT);
  }

  private static ResourceType getResourceAssociatedTo(TerrainType type) {

    switch (type) {
      case FIELDS:
        return ResourceType.GRAIN;
      case FOREST:
        return ResourceType.LUMBER;
      case HILLS:
        return ResourceType.BRICK;
      case MOUNTAINS:
        return ResourceType.ORE;
      case PASTURE:
        return ResourceType.WOOL;
      default:
        return null;
    }
  }
}
