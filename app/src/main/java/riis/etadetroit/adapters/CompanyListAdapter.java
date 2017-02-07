package riis.etadetroit.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import riis.etadetroit.R;
import riis.etadetroit.model.CompanyData;
import riis.etadetroit.model.ETADetroitDatabaseHelper;

/**
 * Created by bmarshall on 1/30/17.
 */

public class CompanyListAdapter extends RecyclerView.Adapter<CompanyListAdapter.ViewHolder> {

    private final Context mContext;
    private OnItemClickListener mItemClickListener;
    private ETADetroitDatabaseHelper eTADetroitDatabaseHelper;

    // 2
    public CompanyListAdapter(Context context) {
        this.mContext = context;
        eTADetroitDatabaseHelper = new ETADetroitDatabaseHelper(mContext);
    }

    // 3
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final LinearLayout placeHolder;
        private final LinearLayout placeNameHolder;
        private final TextView placeName;
        private final ImageView placeImage;

        ViewHolder(View itemView) {
            super(itemView);
            placeHolder = (LinearLayout) itemView.findViewById(R.id.mainHolder);
            placeName = (TextView) itemView.findViewById(R.id.busName);
            placeNameHolder = (LinearLayout) itemView.findViewById(R.id.busNameHolder);
            placeImage = (ImageView) itemView.findViewById(R.id.busImage);
            placeHolder.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(itemView, getAdapterPosition());
            }
        }
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public int getItemCount() {
        return getCompanyListSize();
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

    // 2
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_company_list, parent, false);
        return new ViewHolder(view);
    }

    // 3
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.placeName.setText(getCompanyName(position));
        Picasso.with(mContext).load(getCompanyImageResourceId(mContext, position)).into(holder.placeImage);

        Bitmap photo = BitmapFactory.decodeResource(mContext.getResources(), getCompanyImageResourceId(mContext, position));
        new Palette.Builder(photo).generate(new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette palette) {
                int bgColor = palette.getMutedColor(mContext.getResources().getColor(android.R.color.black));
                holder.placeNameHolder.setBackgroundColor(bgColor);
            }
        });
    }
}