package com.fuse.bootcamp.rockpaperscissors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ShapeSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_selection);
    }

    public void onShapeSelected(View view) {
        String rockTag = getString(R.string.shape_rock);
        String paperTag = getString(R.string.shape_paper);
        String scissorsTag = getString(R.string.shape_scissors);

        String shapeSelection = view.getTag().toString();
        TurnResult.Shape shape = null;

        if (rockTag.equals(shapeSelection)) {
            shape = TurnResult.Shape.ROCK;
        } else if (paperTag.equals(shapeSelection)) {
            shape = TurnResult.Shape.PAPER;
        } else if (scissorsTag.equals(shapeSelection)) {
            shape = TurnResult.Shape.SCISSORS;
        }

        TurnResult result = new TurnResult(shape);
    }
}
