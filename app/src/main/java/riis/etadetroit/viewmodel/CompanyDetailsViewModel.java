package riis.etadetroit.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.AdapterView;

import riis.etadetroit.R;
import riis.etadetroit.adapters.RouteCursorAdapter;
import riis.etadetroit.model.CompanyData;
import riis.etadetroit.model.ETADetroitDatabaseHelper;
import riis.etadetroit.view.RouteDetailsActivity;

public class CompanyDetailsViewModel {
    private Context context;
    private Cursor routeCursor;
    private int defaultColor;
    public String companyName;
    public Drawable busImage;
    public int titleHolderBackgroundColor;
    private Palette mPalette;
    private ETADetroitDatabaseHelper eTADetroitDatabaseHelper;

    public CompanyDetailsViewModel(Context context, int companyPosition) {
        this.context = context;

        eTADetroitDatabaseHelper = new ETADetroitDatabaseHelper(context);

        CompanyData companyData = new CompanyData(eTADetroitDatabaseHelper.getCompanyNames());

        companyName = companyData.getCompanyName(companyPosition);

        int companyImageResourceId = companyData.getCompanyImageResourceId(context, companyPosition);

        Bitmap photo = BitmapFactory.decodeResource(context.getResources(), companyImageResourceId);

        mPalette = new Palette.Builder(photo).generate();

        defaultColor = context.getResources().getColor(R.color.primary_dark);

        titleHolderBackgroundColor = mPalette.getMutedColor(defaultColor);

        busImage = context.getResources().getDrawable(companyImageResourceId, context.getTheme());
    }

    public AdapterView.OnItemClickListener getOnItemClickListener() {
        return new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (routeCursor.moveToPosition(position)) {
                    Intent intent = new Intent(context, RouteDetailsActivity.class);
                    intent.putExtra(RouteDetailsActivity.EXTRA_ROUTE, routeCursor.getString(1));
                    context.startActivity(intent);
                }
            }
        };
    }

    public RouteCursorAdapter getRouteCursorAdapter() {
        routeCursor = eTADetroitDatabaseHelper.getRoutes(companyName);
        return new RouteCursorAdapter(context, routeCursor);
    }

    public Palette getPalette() {
        return mPalette;
    }

    public int getDefaultColor() {
        return defaultColor;
    }
}
