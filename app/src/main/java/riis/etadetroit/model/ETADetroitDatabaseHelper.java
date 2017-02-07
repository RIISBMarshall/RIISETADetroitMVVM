package riis.etadetroit.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by bmarshall on 1/31/17.
 */

public class ETADetroitDatabaseHelper extends SQLiteAssetHelper {

    private static final String DB_NAME = "ETADetroitDatabase.db";
    private SQLiteDatabase db;

    public ETADetroitDatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    public Cursor getCompanyNames() {
        try {
            db = getReadableDatabase();
            return db.query(true, "routes", new String[]{"company"},
                    null, null, null, null, null, null);
        } catch (SQLiteException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public Cursor getRoutes(String company) {
        try {
            db = getReadableDatabase();
            return db.query("routes", new String[]{"_id", "route_name", "route_number"},
                    "company = ?", new String[]{company}, null, null, "cast(route_number as unsigned)");
        } catch (SQLiteException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public Cursor getRouteDetails(String route) {
        try {
            db = getReadableDatabase();
            return db.query("routes", new String[]{"_id",
                            "company", "route_number", "direction1", "direction2", "days_active", "route_id"},
                    "route_name = ?", new String[]{route}, null, null, null);
        } catch (SQLiteException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public Cursor getRouteStops(String route_id) {
        try {
            db = getReadableDatabase();
            return db.query("stop_orders", new String[]{"_id", "stop_name"},
                    "route_id = ? AND stop_day = ?", new String[]{route_id, "Weekday"}, null, null, null);
        } catch (SQLiteException e) {
            System.out.println(e.toString());
            return null;
        }
    }
}
