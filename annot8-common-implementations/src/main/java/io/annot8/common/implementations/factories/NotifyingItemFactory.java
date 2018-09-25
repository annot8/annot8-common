package io.annot8.common.implementations.factories;

import io.annot8.core.data.Item;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class NotifyingItemFactory extends SimpleItemFactory {

  private final Set<Consumer<Item>> listeners = new HashSet<>();

  public NotifyingItemFactory(ItemCreator creator) {
    super(creator);
  }

  public void registerListener(Consumer<Item> consumer) {
    listeners.add(consumer);
  }

  public void unregisterListener(Consumer<Item> consumer) {
    listeners.remove(consumer);
  }

  @Override
  public Item create() {
    Item item = super.create();
    notifyListeners(item);
    return item;
  }

  @Override
  public Item create(Item parent) {
    Item item = super.create(parent);
    notifyListeners(item);
    return item;
  }

  private void notifyListeners(Item item) {
    listeners.forEach(l -> l.accept(item));
  }
}
