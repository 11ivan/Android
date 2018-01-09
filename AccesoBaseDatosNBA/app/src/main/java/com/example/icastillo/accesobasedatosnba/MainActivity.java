package com.example.icastillo.accesobasedatosnba;

import android.arch.persistence.room.RoomDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Equipo[] equipos={new Equipo("Boston Celtics", "TD Garden"),
            new Equipo("Los Angeles Lakers", "Staples Center"),
            new Equipo("New York Knicks", "Madison Square Garden"),
            new Equipo("Chicago Bulls", "United Center"),
            new Equipo("Miami Heat", "AmericanAirlines Arena"),
            new Equipo("Dallas Maverick", "American Airlines Center"),
            new Equipo("Denver Nugget", "Pepsi Center"),
            new Equipo("Sacramento Kings", "Golden 1 Center")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDataBase.getDataBase(this).equipoDAO().insertEquipos(equipos);
        //allowMainThreadQueries();
    }
}
