package riis.etadetroit.viewmodel;

import android.content.Context;
import android.database.Cursor;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

import riis.etadetroit.R;
import riis.etadetroit.model.ETADetroitDatabaseHelper;

public class RouteDetailsViewModel {

    public String routeDetails;
    private String route;
    private String routeId;
    private ETADetroitDatabaseHelper eTADetroitDatabaseHelper;
    private Context context;

    public RouteDetailsViewModel(Context context, String route) {
        this.route = route;
        this.context = context;
        eTADetroitDatabaseHelper = new ETADetroitDatabaseHelper(context);
        showRouteDetails();
    }

    private void showRouteDetails() {
        Cursor routeDetailsCursor = eTADetroitDatabaseHelper.getRouteDetails(route);

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

    public CursorAdapter showRouteStops() {
        return new SimpleCursorAdapter(context, R.layout.adapter_route_stops_cursor,
                eTADetroitDatabaseHelper.getRouteStops(routeId), new String[]{"stop_name"},
                new int[]{R.id.list_content}, 0);
    }
}
