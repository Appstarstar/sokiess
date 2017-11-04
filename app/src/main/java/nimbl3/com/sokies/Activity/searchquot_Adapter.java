package nimbl3.com.sokies.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;

import java.util.List;

import nimbl3.com.sokies.R;

/**
 * Created by MY on 11/2/2017.
 */

public class searchquot_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<searchquot_list> listItemsList1;
    private android.content.Context Context;
    private com.android.volley.toolbox.ImageLoader ImageLoader;
    private int focusedItem = 0;


    private android.widget.Button Button;

    public searchquot_Adapter(android.content.Context context, List<searchquot_list> listItemsList1) {
        this.listItemsList1 = listItemsList1;
        this.Context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fetch_quotation, null);
        ListRowViewHolder holder = new ListRowViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        searchquot_list advisorname = listItemsList1.get(position);
        ListRowViewHolder holder2 = (ListRowViewHolder) holder;
        holder.itemView.setSelected(focusedItem == position);
        holder.getLayoutPosition();


        // Methods.glide_image_loader_fixed_size(advisorname.getImages(), holder2.thumbnail);





        holder2.quotation_no.setText(advisorname.getInvoice1());
        holder2.name_quotation.setText(advisorname.getName1());


    }



    @Override
    public int getItemCount() {

        return (null != listItemsList1 ? listItemsList1.size() : 0);
    }

    public class ListRowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected ImageView thumbnail;
        protected TextView id,name_quotation,quotation_no, HairCurling, HairStraightening, HairColouring, HairCut, HairCute;
        String c1, data;
        ImageView style_image,style_images,style_imagess;
        android.widget.Button next, previous,save;
        private RatingBar ratingBar;
        private CardView card;
        RequestQueue requestQueue;
        private CheckBox Checkbox1;

        private StringRequest stringRequest;

        SharedPreferences preferences;

        protected RelativeLayout relativeLayout;


        public ListRowViewHolder(View view) {
            super(view);
            // this.thumbnail = (ImageView) view.findViewById(R.id.iv_news_image);
            //  this.id = (TextView) view.findViewById(R.id.txt_id);
            this.name_quotation = (TextView) view.findViewById(R.id.quotation_name);
            this.quotation_no = (TextView) view.findViewById(R.id.quotation_no);
            this.card = (CardView) view.findViewById(R.id.card_view_id_qick_order);
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent_transfer(getAdapterPosition());

                }
            });
        }
        @Override
        public void onClick(View v) {

        }
    }
    public void clearAdapter() {
        listItemsList1.clear();
        notifyDataSetChanged();
    }


    public void intent_transfer(int position) {
        Intent intent = new Intent(Context, Viewinvoice.class);
        intent.putExtra("invoice_no", listItemsList1.get(position).getInvoice1());
        Context.startActivity(intent);


    }

}

