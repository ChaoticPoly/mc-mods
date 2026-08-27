package com.example.items;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

public class TickManager {
    private static final List<BooleanSupplier> tasks = new ArrayList<>();
    private static final List<BooleanSupplier> pending = new ArrayList<>();

    public static void addTask(BooleanSupplier task) {
        pending.add(task);
    }

    public static void tick() {
        tasks.removeIf(BooleanSupplier::getAsBoolean);
        if (!pending.isEmpty()) {
            tasks.addAll(pending);
            pending.clear();
        }
    }
}
