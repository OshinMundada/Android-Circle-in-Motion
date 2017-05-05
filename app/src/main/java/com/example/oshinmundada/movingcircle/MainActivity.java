package com.example.oshinmundada.movingcircle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String mode = "draw";
    private static final String color = "black";

    private SetClass canvasDraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.canvasDraw = new SetClass(this);
        this.canvasDraw.setModes(mode);
        this.canvasDraw.setColor(color);
        setContentView(this.canvasDraw);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mode_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.blue:
                this.canvasDraw.setColor("blue");
                item.setChecked(true);
                break;
            case R.id.green:
                this.canvasDraw.setColor("green");
                item.setChecked(true);
                break;
            case R.id.red:
                this.canvasDraw.setColor("red");
                item.setChecked(true);
                break;
            case R.id.black:
                this.canvasDraw.setColor("black");
                item.setChecked(true);
                break;

            case R.id.draw:
                this.canvasDraw.setModes("draw");
                item.setChecked(true);
                Toast.makeText(this, "Mode selected: DRAW", Toast.LENGTH_SHORT).show();
                break;
            case R.id.move:
                this.canvasDraw.setModes("move");
                item.setChecked(true);
                Toast.makeText(this, "Mode selected: MOVE", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                this.canvasDraw.setModes("delete");
                item.setChecked(true);
                Toast.makeText(this, "Mode selected: DELETE", Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }
}
