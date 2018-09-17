package io.annot8.common.implementations.factories;

import io.annot8.core.data.Item;
import io.annot8.core.data.ItemFactory;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class NotifyingItemFactory implements ItemFactory {

  private final Set<Consumer<Item>> listeners = new HashSet<>();
  private final ItemFactory factory;

  public NotifyingItemFactory(ItemFactory factory) {
    this.factory = factory;
  }

  public void registerListener(Consumer<Item> consumer) {
    listeners.add(consumer);
  }

  public void unregisterListener(Consumer<Item> consumer) {
    listeners.remove(consumer);
  }

  @Override
  public Item create() {
    Item item = factory.create();
    notifyListeners(item);
    return item;
  }

  @Override
  public Item create(Item parent) {
    Item item = factory.create(parent);
    notifyListeners(item);
    return item;
  }

  private void notifyListeners(Item item) {
    listeners.forEach(l -> l.accept(item));
  }
}
