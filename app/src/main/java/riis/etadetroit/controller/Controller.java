package riis.etadetroit.controller;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;

import riis.etadetroit.model.CompanyData;
import riis.etadetroit.model.ETADetroitDatabaseHelper;

/**
 * Created by bmarshall on 1/31/17.
 */

public class Controller extends Application {
    private ETADetroitDatabaseHelper eTADetroitDatabaseHelper;

    @Override
    public void onCreate() {
        eTADetroitDatabaseHelper = new ETADetroitDatabaseHelper(this);
    }

    public int getCompanyListSize() {
        return eTADetroitDatabaseHelper.getCompanyNames().getCount();
    }

    public String getCompanyName(int position) {
        CompanyData companyData = new CompanyData(eTADetroitDatabaseHelper.getCompanyNames());
        return companyData.getCompanyName(position);
    }

    public int getCompanyImageResourceId(Context context, int position) {
        CompanyData companyData = new CompanyData(eTADetroitDatabaseHelper.getCompanyNames());
        return companyData.getCompanyImageResourceId(context, position);
    }

    public Cursor getRoutes(String company) {
        return eTADetroitDatabaseHelper.getRoutes(company);
    }

    public Cursor getRouteDetails(String route) {
        return eTADetroitDatabaseHelper.getRouteDetails(route);
    }

    public Cursor getRouteStops(String route_id) {
        return eTADetroitDatabaseHelper.getRouteStops(route_id);
    }
}
