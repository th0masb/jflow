package xawd.jflow.geometry.impl;

import java.util.function.Consumer;

import xawd.jflow.Flow;
import xawd.jflow.geometry.Point;

@FunctionalInterface
public interface PointProcessor extends Consumer<Flow<? extends Point>> {
}
