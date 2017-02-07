package riis.etadetroit.view;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import riis.etadetroit.R;
import riis.etadetroit.databinding.ActivityRouteDetailsBinding;

import riis.etadetroit.viewmodel.RouteDetailsViewModel;

public class RouteDetailsActivity extends Activity {

    public static final String EXTRA_ROUTE = "route";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RouteDetailsViewModel routeDetailsViewModel = new RouteDetailsViewModel(this, getIntent().getStringExtra(EXTRA_ROUTE));

        ActivityRouteDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_route_details);

        binding.setRouteDetailsViewModel(routeDetailsViewModel);

        binding.stopList.setAdapter(routeDetailsViewModel.showRouteStops());
    }
}
