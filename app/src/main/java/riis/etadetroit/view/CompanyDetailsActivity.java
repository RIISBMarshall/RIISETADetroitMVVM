package riis.etadetroit.view;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.transition.Transition;

import riis.etadetroit.R;
import riis.etadetroit.adapters.TransitionAdapter;
import riis.etadetroit.databinding.ActivityCompanyDetailsBinding;
import riis.etadetroit.viewmodel.CompanyDetailsViewModel;

public class CompanyDetailsActivity extends Activity {

    public static final String EXTRA_PARAM_ID = "place_id";
    private CompanyDetailsViewModel companyDetailsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityCompanyDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_company_details);

        companyDetailsViewModel = new CompanyDetailsViewModel(this, getIntent().getIntExtra(EXTRA_PARAM_ID, 0));

        binding.setCompanyDetailsViewModel(companyDetailsViewModel);

        binding.list.setAdapter(companyDetailsViewModel.getRouteCursorAdapter());

        binding.list.setOnItemClickListener(companyDetailsViewModel.getOnItemClickListener());

        applyPalette(companyDetailsViewModel.getPalette());

        windowTransition();
    }

    private void applyPalette(Palette mPalette) {
        getWindow().setBackgroundDrawable(new ColorDrawable(mPalette.getDarkMutedColor(companyDetailsViewModel.getDefaultColor())));
    }

    private void windowTransition() {
        getWindow().getEnterTransition().addListener(new TransitionAdapter() {
            @Override
            public void onTransitionEnd(Transition transition) {
                getWindow().getEnterTransition().removeListener(this);
            }
        });

    }
}
