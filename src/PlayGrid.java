import java.util.ArrayList;

public class PlayGrid {
    ArrayList<ArrayList<String>> grid = new ArrayList<>();

    public PlayGrid(int size) {
        for(int row = 0; row < size; row++) {
            grid.add(new ArrayList<>());
            for(int column = 0; column < size; column++) {
                grid.get(row).add("x");
            }
        }
    }
}
