package riis.etadetroit.model;

import android.content.Context;
import android.database.Cursor;

/**
 * Created by bmarshall on 2/6/17.
 */

public class CompanyData {

    private Cursor companyNames;

    public CompanyData(Cursor companyNames) {
        this.companyNames = companyNames;
    }

    public String getCompanyName(int position) {
        if (companyNames != null && companyNames.moveToPosition(position)) {
            return companyNames.getString(0);
        }
        return null;
    }

    public int getCompanyImageResourceId(Context context, int position) {
        if (companyNames != null && companyNames.moveToPosition(position)) {
            String imageName = companyNames.getString(0).toLowerCase();
            return context.getResources().getIdentifier(imageName, "drawable",
                    context.getPackageName());
        }
        return 0;
    }
}
