package riis.etadetroit.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import riis.etadetroit.R;

/**
 * Created by bmarshall on 2/3/17.
 */

public class RouteCursorAdapter extends CursorAdapter {

    public RouteCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.adapter_route_cursor, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView routeNumberView = (TextView) view.findViewById(R.id.routeNumber);
        TextView routeNameView = (TextView) view.findViewById(R.id.routeName);
        // Extract properties from cursor
        int routeNumber = cursor.getInt(cursor.getColumnIndexOrThrow("route_number"));
        String routeName = cursor.getString(cursor.getColumnIndexOrThrow("route_name"));
        // Populate fields with extracted properties
        routeNumberView.setText(String.valueOf(routeNumber));
        routeNameView.setText(routeName);
    }
}
