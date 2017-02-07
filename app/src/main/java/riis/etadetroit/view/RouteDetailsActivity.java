package riis.etadetroit.view;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import riis.etadetroit.R;
import riis.etadetroit.databinding.ActivityRouteDetailsBinding;
import riis.etadetroit.model.ETADetroitDatabaseHelper;

public class RouteDetailsActivity extends Activity {

    public static final String EXTRA_ROUTE = "route";
    private String routeDetails;
    private String route;
    private String routeId;
    private ETADetroitDatabaseHelper eTADetroitDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_details);
        ActivityRouteDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_route_details);

        eTADetroitDatabaseHelper = new ETADetroitDatabaseHelper(this);
        Intent intent = getIntent();
        route = intent.getStringExtra(EXTRA_ROUTE);
        showRouteDetails();
        showRouteStops();
        binding.setRouteDetails(routeDetails);
    }

    private void showRouteDetails() {
        Cursor routeDetailsCursor = getRouteDetails(route);

        if (routeDetailsCursor.moveToFirst()) {
            routeDetails = ("ROUTE DETAILS" +
                    "\n\nROUTE: " + route +
                    "\nROUTE NUMBER: " + routeDetailsCursor.getString(2) +
                    "\nDIRECTION 1: " + routeDetailsCursor.getString(3) +
                    "\nDIRECTION 2: " + routeDetailsCursor.getString(4) +
                    "\nDAYS ACTIVE: " + routeDetailsCursor.getString(5) +
                    "\n\nSTOPS");

            routeId = routeDetailsCursor.getString(6);
        }
    }

    private void showRouteStops() {
        Cursor routeStopsCursor = getRouteStops(routeId);

        CursorAdapter routeStopsCursorAdapter = new SimpleCursorAdapter(this, R.layout.adapter_route_stops_cursor, routeStopsCursor, new String[]{"stop_name"},
                new int[]{R.id.list_content}, 0);
        ListView stopList = (ListView) findViewById(R.id.stopList);
        stopList.setAdapter(routeStopsCursorAdapter);
    }

    public Cursor getRouteDetails(String route) {
        return eTADetroitDatabaseHelper.getRouteDetails(route);
    }

    public Cursor getRouteStops(String route_id) {
        return eTADetroitDatabaseHelper.getRouteStops(route_id);
    }
}
