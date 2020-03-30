package com.ucm.dasi.catan.resource.provider;

import com.ucm.dasi.catan.resource.IResourceStorage;
import com.ucm.dasi.catan.resource.ResourceStorage;
import java.util.Map;
import java.util.TreeMap;

public class ResourceManagerProvider<TType extends Comparable<TType>>
    implements IResourceManagerProvider<TType> {

  protected TreeMap<TType, IResourceStorage> resourcesMap;

  public ResourceManagerProvider(Map<TType, ? extends IResourceStorage> resourcesMap) {
    this.resourcesMap = new TreeMap<TType, IResourceStorage>(resourcesMap);
  }

  @Override
  public IResourceStorage getResourceManager(TType type) {
    IResourceStorage storedCost = this.resourcesMap.get(type);

    return storedCost == null ? new ResourceStorage() : storedCost;
  }
}
