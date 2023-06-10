package org.mars.rover.kata.location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mars.rover.kata.MarsRover;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
public class Grid {
    private int gridX;
    private int gridY;

    private ArrayList<ArrayList<OccupiedArea>> areasOccupiedByRovers;

    public static Grid createGrid(int width, int height) {
        var gridInstance = new Grid(width, height, new ArrayList<>());

        for (int i = 0; i < width; i++) {
            var row = new ArrayList<OccupiedArea>();

            for (int j = 0; j < height; j++) {
                row.add(OccupiedArea.newInstance(i, j));
            }

            gridInstance.getAreasOccupiedByRovers().add(row);
        }

        return gridInstance;
    }

    protected Position wrapEdges(Position newPosition) {
        int numRows = this.gridX;
        int numCols = this.gridY;

        int wrappedX = (newPosition.coordinate().x() + numRows) % numRows;
        int wrappedY = (newPosition.coordinate().y() + numCols) % numCols;

        return newPosition.withCoordinate(new Coordinate(wrappedX, wrappedY));
    }

    public void enterNewArea(MarsRover marsRover, Position newPosition) {
        var wrappedEdgesPosition = this.wrapEdges(newPosition);

        this.getAreasOccupiedByRovers()
                .get(wrappedEdgesPosition.coordinate().x())
                .get(wrappedEdgesPosition.coordinate().y())
                .enterArea(marsRover);
    }

    public void leaveOldArea(MarsRover marsRover, Position oldPosition) {
        var wrappedEdgesPosition = this.wrapEdges(oldPosition);

        this.getAreasOccupiedByRovers()
                .get(wrappedEdgesPosition.coordinate().x())
                .get(wrappedEdgesPosition.coordinate().y())
                .leaveArea(marsRover);
    }
}
