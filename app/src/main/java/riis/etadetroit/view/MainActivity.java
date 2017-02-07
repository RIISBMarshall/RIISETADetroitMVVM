package riis.etadetroit.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.support.v4.util.Pair;
import android.widget.Toolbar;

import riis.etadetroit.adapters.CompanyListAdapter;
import riis.etadetroit.R;
import riis.etadetroit.controller.Controller;


public class MainActivity extends Activity {

    private Menu menu;
    private boolean isListView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Controller aController = (Controller) getApplicationContext();

        isListView = true;

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);
        CompanyListAdapter mAdapter = new CompanyListAdapter(this, aController);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(onItemClickListener);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setUpActionBar();
    }

    private final CompanyListAdapter.OnItemClickListener onItemClickListener = new CompanyListAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            Intent intent = new Intent(MainActivity.this, CompanyDetailsActivity.class);
            intent.putExtra(CompanyDetailsActivity.EXTRA_PARAM_ID, position);
            //startActivity(intent);
            ImageView busImage = (ImageView) v.findViewById(R.id.busImage);
            LinearLayout busNameHolder = (LinearLayout) v.findViewById(R.id.busNameHolder);


            Pair<View, String> imagePair = Pair.create((View) busImage, "tImage");
            Pair<View, String> holderPair = Pair.create((View) busNameHolder, "tNameHolder");


            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,
                    imagePair, holderPair);
            ActivityCompat.startActivity(MainActivity.this, intent, options.toBundle());
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_toggle) {
            toggle();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpActionBar() {
        if (toolbar != null) {
            setActionBar(toolbar);
            getActionBar().setDisplayHomeAsUpEnabled(false);
            getActionBar().setDisplayShowTitleEnabled(true);
            getActionBar().setElevation(7);
        }
    }

    private void toggle() {
        MenuItem item = menu.findItem(R.id.action_toggle);
        if (isListView) {
            mStaggeredLayoutManager.setSpanCount(2);
            item.setIcon(R.drawable.ic_action_list);
            item.setTitle("Show as list");
            isListView = false;
        } else {
            mStaggeredLayoutManager.setSpanCount(1);
            item.setIcon(R.drawable.ic_action_grid);
            item.setTitle("Show as grid");
            isListView = true;
        }
    }
}
