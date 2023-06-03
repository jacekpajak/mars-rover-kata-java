package org.mars.rover.kata.location;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
public class Grid {
    private ArrayList<ArrayList<OccupiedArea>> areasOccupiedByRovers;

    public static Grid createGrid(int width, int height) {
        var gridInstance = new Grid(new ArrayList<>());

        for (int i = 0; i < width; i++) {
            var row = new ArrayList<OccupiedArea>();

            for (int j = 0; j < height; j++) {
                row.add(OccupiedArea.newInstance(i, j));
            }

            gridInstance.getAreasOccupiedByRovers().add(row);
        }

        return gridInstance;
    }
}
